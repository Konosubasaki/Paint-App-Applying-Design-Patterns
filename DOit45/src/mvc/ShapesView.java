package mvc;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

public class ShapesView extends JPanel {
	private ButtonGroup shapes = new ButtonGroup();
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnSquare;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnHexagon;


	public ShapesView() {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.disabledForeground"));
		add(panel, BorderLayout.WEST);

		tglbtnPoint = new JToggleButton("");
		tglbtnPoint.setIcon(new ImageIcon(ShapesView.class.getResource("/icons/point.png")));
		tglbtnPoint.setBackground((Color.WHITE));
		shapes.add(tglbtnPoint);

		tglbtnLine = new JToggleButton("");
		tglbtnLine.setIcon(new ImageIcon(ShapesView.class.getResource("/icons/line.png")));
		tglbtnLine.setBackground((Color.WHITE));
		shapes.add(tglbtnLine);

		tglbtnSquare = new JToggleButton("");
		tglbtnSquare.setIcon(new ImageIcon(ShapesView.class.getResource("/icons/square.png")));
		shapes.add(tglbtnSquare);
		tglbtnSquare.setBackground((Color.WHITE));

		tglbtnRectangle = new JToggleButton("");
		tglbtnRectangle.setIcon(new ImageIcon(ShapesView.class.getResource("/icons/rectangle.png")));
		shapes.add(tglbtnRectangle);
		tglbtnRectangle.setBackground((Color.WHITE));

		tglbtnCircle = new JToggleButton("");
		tglbtnCircle.setIcon(new ImageIcon(ShapesView.class.getResource("/icons/circle.png")));
		shapes.add(tglbtnCircle);
		tglbtnCircle.setBackground((Color.WHITE));

		tglbtnHexagon = new JToggleButton("");
		tglbtnHexagon.setIcon(new ImageIcon(ShapesView.class.getResource("/icons/Hexagon.png")));
		shapes.add(tglbtnHexagon);
		tglbtnHexagon.setBackground((Color.WHITE));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(tglbtnSquare, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(tglbtnPoint, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
						.addComponent(tglbtnHexagon, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnPoint)
					.addGap(18)
					.addComponent(tglbtnLine)
					.addGap(14)
					.addComponent(tglbtnSquare)
					.addGap(18)
					.addComponent(tglbtnRectangle)
					.addGap(18)
					.addComponent(tglbtnCircle)
					.addGap(18)
					.addComponent(tglbtnHexagon)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);


	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnSquare() {
		return tglbtnSquare;
	}

	public void setTglbtnSquare(JToggleButton tglbtnSquare) {
		this.tglbtnSquare = tglbtnSquare;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public ButtonGroup getShapes() {
		return shapes;
	}

	public void setShapes(ButtonGroup shapes) {
		this.shapes = shapes;
	}
}
