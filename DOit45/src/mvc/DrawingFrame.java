package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import observer.Observer;

import java.awt.Dimension;

public class DrawingFrame extends JFrame implements Observer {

	private DrawingView view = new DrawingView();
	private ShapesView sView = new ShapesView();
	private DrawingController controller;

	private JPanel panel = new JPanel();
	private JButton btnModify, btnDelete, btnUndo, btnRedo, btnBorderColor, btnInnerColor;
	private JButton btntoBack;
	private JButton btntoFront;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JButton btnLoad;
	private JButton btnSave;
	private JButton btnOpen;
	private JToggleButton tglbtnSelect;
	private JTextArea _resultArea=new JTextArea(6,20);

	private Color color;

	// observer set metode

	public DrawingFrame() {

		getContentPane().add(view, BorderLayout.CENTER);
		getContentPane().add(sView, BorderLayout.EAST);

		// ***************************************************************************************
		btnModify = new JButton("");
		btnModify.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/modify.png")));
		btnModify.setEnabled(false);
		btnModify.setBorder(null);
		btnModify.setBorderPainted(false);

		getBtnModify().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.btnModifyShape(e);
			}
		});

		// ***************************************************************************************

		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/delete.png")));
		btnDelete.setEnabled(false);
		btnDelete.setBorder(null);
		btnDelete.setBorderPainted(false);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnDeletePressed(e);
			}
		});

		// ***************************************************************************************

		btnUndo = new JButton("");
		btnUndo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/undo.png")));
		btnUndo.setBorder(null);
		btnUndo.setBorderPainted(false);
		btnUndo.setEnabled(false);

		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});

		// ***************************************************************************************

		btnRedo = new JButton("");
		btnRedo.setBorder(null);
		btnRedo.setBorderPainted(false);
		btnRedo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/redo.png")));
		btnRedo.setEnabled(false);

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});

		// ***************************************************************************************

		btntoBack = new JButton("To Back");
		btntoBack.setEnabled(false);
		btntoBack.setBackground(Color.WHITE);

		btntoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});

		// ***************************************************************************************

		btntoFront = new JButton("To Front");
		btntoFront.setBackground(Color.WHITE);
		btntoFront.setEnabled(false);

		btntoFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});

		// ***************************************************************************************

		btnBringToFront = new JButton("Bring to Front");
		btnBringToFront.setBackground(Color.WHITE);
		btnBringToFront.setEnabled(false);

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}

		});

		// ***************************************************************************************

		btnBringToBack = new JButton("Bring to Back");
		btnBringToBack.setBackground(Color.WHITE);
		btnBringToBack.setEnabled(false);

		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});

		// ***************************************************************************************

		tglbtnSelect = new JToggleButton("");
		tglbtnSelect.setBorder(null);
		tglbtnSelect.setBorderPainted(false);
		tglbtnSelect.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/select.png")));
		tglbtnSelect.setEnabled(false);

		tglbtnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.tglbtnSelectPressed(e);
			}
		});

		// ***************************************************************************************

		//za odesel
		sView.getShapes().add(tglbtnSelect);

		
		
		JScrollPane scrollPane = new JScrollPane(_resultArea);
		
		getContentPane().add(scrollPane, BorderLayout.SOUTH);


		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblBorderColor = new JLabel("Border:");

		JLabel lblInnerColor = new JLabel("Inner:");

		btnBorderColor = new JButton("");
		btnBorderColor.setBackground(Color.BLACK);
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Choose color", color);
				if (color != null) {
					btnBorderColor.setBackground(color);
				}

			}
		});

		btnInnerColor = new JButton("");
		btnInnerColor.setBackground(Color.WHITE);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Choose color", color);
				if (color != null) {
					btnInnerColor.setBackground(color);
				}
			}
		});

		// ***************************************************************************************

		btnOpen = new JButton("");
		btnOpen.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/open.png")));
		btnOpen.setVerticalAlignment(SwingConstants.BOTTOM);
		btnOpen.setHorizontalAlignment(SwingConstants.RIGHT);
		btnOpen.setBorder(null);
		btnOpen.setBorderPainted(false);

		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.open();
			}
		});

		// ***************************************************************************************

		btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/save.png")));
		btnSave.setEnabled(false);
		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSave.setBorder(null);
		btnSave.setBorderPainted(false);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.save();
			}
		});

		// ***************************************************************************************

		btnLoad = new JButton("");
		btnLoad.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/load.png")));
		btnLoad.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLoad.setBorder(null);
		btnLoad.setBorderPainted(false);
		btnLoad.setEnabled(false);

		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.load();
			}
		});

		// ***************************************************************************************

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(70)
					.addComponent(btnOpen)
					.addGap(136)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoad)
							.addGap(5))
						.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDelete)
							.addGap(18)
							.addComponent(btnUndo)
							.addGap(18)
							.addComponent(btnRedo))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(268)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBringToBack)
								.addComponent(btntoBack))))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btntoFront)
								.addComponent(btnBringToFront)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(126)
							.addComponent(lblBorderColor)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblInnerColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(139))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(btnInnerColor))
						.addComponent(lblInnerColor))
					.addContainerGap(14, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addComponent(btnOpen)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);


		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.mouseClicked(arg0);

			}
		});

		sView.getTglbtnPoint().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.tglbtnPointPressed(e);
			}
		});

		sView.getTglbtnLine().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.tglbtnLinePressed(e);
			}
		});

		sView.getTglbtnSquare().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.tglbtnSquarePressed(e);
			}
		});
		sView.getTglbtnRectangle().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.tglbtnRectanglePressed(e);
			}
		});
		sView.getTglbtnCircle().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.tglbtnCirclePressed(e);
			}
		});
		sView.getTglbtnHexagon().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.tglbtnHexagonPressed(e);
			}
		});
	}

	public void setStatusModify(boolean b) {
		btnModify.setEnabled(b);
	}

	public void setStatusDelete(boolean b) {
		btnDelete.setEnabled(b);
	}

	public void setStatusUndo(boolean b) {
		btnUndo.setEnabled(b);
	}

	public void setStatusRedo(boolean b) {
		btnRedo.setEnabled(b);
	}

	public void setStatustoBack(boolean b) {
		btntoBack.setEnabled(b);
	}

	public void setStatustoFront(boolean b) {
		btntoFront.setEnabled(b);
	}

	public void setStatusBringToBack(boolean b) {
		btnBringToBack.setEnabled(b);
	}

	public void setStatusBringToFront(boolean b) {
		btnBringToFront.setEnabled(b);
	}

	public void setStatusSave(boolean b) {
		btnSave.setEnabled(b);
	}

	public void setStatusSelect(boolean b) {
		tglbtnSelect.setEnabled(b);
	}

	// geteri i seteri
	public ShapesView getsView() {
		return sView;
	}

	public void setsView(ShapesView sView) {
		this.sView = sView;
	}

	public DrawingController getController() {
		return controller;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtntoBack() {
		return btntoBack;
	}

	public JButton getBtntoFront() {
		return btntoFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JButton getBtnLoad() {
		return btnLoad;
	}

	public void setBtnLoad(JButton btnLoad) {
		this.btnLoad = btnLoad;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnOpen() {
		return btnOpen;
	}

	public void setBtnOpen(JButton btnOpen) {
		this.btnOpen = btnOpen;
	}

	// observer metoda
	@Override
	public void updateButtonsStatus(int numberOfShapes, int numberOfSelectedShapes, int IsItOneSelectedFirstOrLastShape,
			int numberOfCommandsInRedoList) {
		if (numberOfShapes > 0) {
			setStatusSelect(true);
			setStatusSave(true);
			setStatusUndo(true);
			// omoguci selekt ,save i undo
		}

		else if (numberOfShapes == 1) {
			setStatustoBack(false);
			setStatusBringToBack(false);
			setStatustoFront(false);
			setStatusBringToFront(false);

		} else {
			// onemoguce selekt, save i undo delete
			setStatusSelect(false);
			setStatusSave(false);
			// ako nema ni jednog
			setStatusUndo(false);
		}

		if (numberOfSelectedShapes == 1) {
			// omoguci edit,delete, send i bring.....+
			setStatusModify(true);
			setStatusDelete(true);

			if (IsItOneSelectedFirstOrLastShape == 1 && numberOfShapes != 1) {
				setStatustoBack(false);
				setStatusBringToBack(false);
				setStatustoFront(true);
				setStatusBringToFront(true);

			}

			else if (IsItOneSelectedFirstOrLastShape == 3 && numberOfShapes != 1) {
				setStatustoBack(true);
				setStatusBringToBack(true);
				setStatustoFront(false);
				setStatusBringToFront(false);

			}

			else if (IsItOneSelectedFirstOrLastShape == 2) {
				setStatustoBack(true);
				setStatusBringToBack(true);
				setStatustoFront(true);
				setStatusBringToFront(true);

			}

		}

		else if (numberOfSelectedShapes > 1) {
			// omoguci delete
			// onemoguci edit bring i send metode
			setStatusDelete(true);
			setStatusModify(false);
			setStatustoBack(false);
			setStatusBringToBack(false);
			setStatustoFront(false);
			setStatusBringToFront(false);

		}

		else if (numberOfSelectedShapes == 0) {

			// onemoguci edit delete bring i send metode
			setStatusModify(false);
			setStatusDelete(false);
			setStatustoBack(false);
			setStatusBringToBack(false);
			setStatustoFront(false);
			setStatusBringToFront(false);

		}

		if (numberOfCommandsInRedoList > 0) {
			// omoguci redo
			setStatusRedo(true);

		}

		else {
			// onemoguci redo
			setStatusRedo(false);

		}

	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;

	}
	
	public void addTextFrameLog(String s)
	{
		s+="\n";
		_resultArea.append(s);
	}

	public String getTextFrameLog() {
		// TODO Auto-generated method stub
		return _resultArea.getText();
	}

	public JTextArea getTextBox()
	{
		return _resultArea;
	}
}
