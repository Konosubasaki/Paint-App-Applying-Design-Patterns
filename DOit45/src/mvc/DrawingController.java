package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import shapes.HexagonAdapter;
import command.AddShape;
import command.BringToBack;
import command.BringToFront;
import command.Command;
import command.Delete;
import command.Edit;
import command.Select;
import command.ToBack;
import command.ToFront;
import command.UndoRedo;
import command.Unselect;
import dialogs.CircleDialog;
import dialogs.HexagonDialog;
import dialogs.LineDialog;
import dialogs.PointDialog;
import dialogs.RectangleDialog;
import dialogs.SquareDialog;
import observer.Observer;
import observer.Subject;
import shapes.Circle;
import shapes.Point;
import shapes.Square;
import strategy.LoggingFile;
import strategy.LoggingManager;
import strategy.LoggingSerialization;
import shapes.Line;
import shapes.Rectangle;
import shapes.Shape;

public class DrawingController implements Subject {

	private DrawingModel model;
	private DrawingFrame frame;

	private Shape shape;
	private Point previous;
	private Command command;

	private Observer observer;

	private PointDialog pointdialog;
	private LineDialog linedialog;
	private CircleDialog circledialog;
	private SquareDialog squaredialog;
	private RectangleDialog rectangledialog;
	private HexagonDialog hexagondialog;

	private int flag;
	private File file;

	private ArrayList<Command> LoggingCommands;
	private ArrayList<Command> commands;
	private ArrayList<Command> undoRedoCommands;
	private ArrayList<Command> readedcommands;
	private LoggingManager lm;

	private int selShape = 0;
	private int i = 0;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		setObserver(frame);
		commands = new ArrayList<Command>();
		undoRedoCommands = new ArrayList<Command>();
		LoggingCommands = new ArrayList<Command>();
		readedcommands = new ArrayList<Command>();
		flag = 0;
	}

	public void setUndoRedoCommandsToNULL() {
		undoRedoCommands = new ArrayList<Command>();

	}

	public Command getLastCommand() {
		return commands.get(commands.size() - 1);

	}

	public Command getLastUndoRedoCommand() {
		return undoRedoCommands.get(undoRedoCommands.size() - 1);

	}

	public void addUndoRedoCommands(Command c) {
		undoRedoCommands.add(c);
	}

	public void addCommands(Command c) {
		commands.add(c);
		LoggingCommands.add(c);
		// moras jos staviti da u logging dodaje undo i redo(
	}

	public void deleteLastUndoRedoCommands() {
		undoRedoCommands.remove(undoRedoCommands.size() - 1);
	}

	public void deleteLastCommand() {
		commands.remove(commands.size() - 1);
	}

	public void mouseClicked(MouseEvent arg0) {

		Point clicked = new Point(arg0.getX(), arg0.getY(), Color.BLACK);
		if (selShape == 0) {

			for (Shape s : model.getAll()) {
				if (s.contains(clicked.getX(), clicked.getY())) {
					if (s.getIsSelected() == false) {
						command = new Select(s);

						command.execute();
						frame.addTextFrameLog(command.logText());
						frame.getView().repaint();

						addCommands(command);
						setUndoRedoCommandsToNULL();
						notifyObserver();

					} else {
						command = new Unselect(s);
						command.execute();
						frame.addTextFrameLog(command.logText());
						frame.getView().repaint();

						addCommands(command);
						setUndoRedoCommandsToNULL();
						notifyObserver();

					}
					break;
				}

			}

		}

		else if (selShape == 1) {

			shape = new Point(clicked.getX(), clicked.getY(), frame.getBtnBorderColor().getBackground());
			command = new AddShape(model, shape);
			command.execute();
			frame.addTextFrameLog(command.logText());
			frame.getView().repaint();

			addCommands(command);
			setUndoRedoCommandsToNULL();
			notifyObserver();

		} else if (selShape == 2) {

			if (flag == 0 || flag == 2) {
				previous = new Point(arg0.getX(), arg0.getY(), Color.BLACK);
				flag = 1;
			}

			else {

				shape = new Line(clicked, previous, frame.getBtnBorderColor().getBackground());
				command = new AddShape(model, shape);

				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				flag = 2;
				setUndoRedoCommandsToNULL();
				notifyObserver();
			}

		} else if (selShape == 3) {

			squaredialog = new SquareDialog();
			squaredialog.getBtnBorderColor().setBackground(frame.getBtnBorderColor().getBackground());
			squaredialog.getBtnInnerColor().setBackground(frame.getBtnInnerColor().getBackground());
			squaredialog.getTextFieldX().setText(String.valueOf(clicked.getX()));
			squaredialog.getTextFieldY().setText(String.valueOf(clicked.getY()));

			squaredialog.setVisible(true);

			if (squaredialog.isOk()) {
				shape = squaredialog.getSquare();
				command = new AddShape(model, shape);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
				notifyObserver();

			}

		}

		else if (selShape == 4) {

			rectangledialog = new RectangleDialog();
			rectangledialog.getBtnBorderColor().setBackground(frame.getBtnBorderColor().getBackground());
			rectangledialog.getBtnInnerColor().setBackground(frame.getBtnInnerColor().getBackground());

			rectangledialog.getTextFieldX().setText(String.valueOf(clicked.getX()));
			rectangledialog.getTextFieldY().setText(String.valueOf(clicked.getY()));

			rectangledialog.setVisible(true);

			if (rectangledialog.isOk()) {
				shape = rectangledialog.getRectangle();
				command = new AddShape(model, shape);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
				notifyObserver();

			}

		}

		else if (selShape == 5) {
			circledialog = new CircleDialog();
			circledialog.getBtnBorderColor().setBackground(frame.getBtnBorderColor().getBackground());
			circledialog.getBtnInnerColor().setBackground(frame.getBtnInnerColor().getBackground());

			circledialog.getTextFieldX().setText(String.valueOf(clicked.getX()));
			circledialog.getTextFieldY().setText(String.valueOf(clicked.getY()));

			circledialog.setVisible(true);

			if (circledialog.isOk()) {
				shape = circledialog.getCircle();
				command = new AddShape(model, shape);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
				notifyObserver();

			}

		}

		else if (selShape == 6) {
	
//				shape =new HexagonAdapter(clicked,55,Color.BLACK,Color.PINK);
//				command = new AddShape(model, shape);
//				command.execute();
//				frame.addTextFrameLog(command.logText());
//				frame.getView().repaint();
//
//				addCommands(command);
				setUndoRedoCommandsToNULL();
				notifyObserver();

			}

	}

	public void tglbtnPointPressed(ActionEvent e) {
		selShape = 1;
	}

	public void tglbtnLinePressed(ActionEvent e) {
		selShape = 2;

	}

	public void tglbtnSquarePressed(ActionEvent e) {
		selShape = 3;

	}

	public void tglbtnRectanglePressed(ActionEvent e) {
		selShape = 4;
	}

	public void tglbtnCirclePressed(ActionEvent e) {
		selShape = 5;
	}

	public void tglbtnHexagonPressed(ActionEvent e) {
		selShape = 6;
	}

	public void tglbtnSelectPressed(ActionEvent e) {
		selShape = 0;
	}

	public void btnModifyShape(ActionEvent e) {

		shape = model.getOneSelectedShape();

		if (shape instanceof Point) {
			Point point = (Point) shape;

			pointdialog = new PointDialog();
			pointdialog.getTextFieldX().setText(String.valueOf(point.getX()));
			pointdialog.getTextFieldY().setText(String.valueOf(point.getY()));
			pointdialog.getBtnBorderColor().setBackground(point.getBorder());
			pointdialog.getBtnInnerColor().setBackground(Color.WHITE);
			pointdialog.setVisible(true);

			if (pointdialog.isOk()) {
				Point edited = pointdialog.getPoint();
				edited.setIsSelected(true);
				command = new Edit(model, shape, edited);
				// frame.getTglbtnSelect().setSelected(true);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
			}
		}

		else if (shape instanceof Line) {
			Line line = (Line) shape;

			linedialog = new LineDialog();
			linedialog.getTextFieldX().setText(String.valueOf(line.getStart().getX()));
			linedialog.getTextFieldY().setText(String.valueOf(line.getStart().getY()));
			linedialog.getTextField_Xe().setText(String.valueOf(line.getEnd().getX()));
			linedialog.getTextField_Ye().setText(String.valueOf(line.getEnd().getY()));
			linedialog.getBtnBorderColor().setBackground(line.getBorder());
			linedialog.getBtnInnerColor().setBackground(Color.WHITE);
			linedialog.setVisible(true);

			if (linedialog.isOk()) {
				Line edited = linedialog.getLine();
				edited.setIsSelected(true);
				command = new Edit(model, shape, edited);
				// frame.getTglbtnSelect().setSelected(true);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
			}

		}

		else if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;

			rectangledialog = new RectangleDialog();
			rectangledialog.getTextFieldX().setText(String.valueOf(rectangle.getUpLeft().getX()));
			rectangledialog.getTextFieldY().setText(String.valueOf(rectangle.getUpLeft().getY()));
			rectangledialog.getTextField_a().setText(String.valueOf(rectangle.getA()));
			rectangledialog.getTextField_b().setText(String.valueOf(rectangle.getB()));
			rectangledialog.getBtnBorderColor().setBackground(rectangle.getBorder());
			rectangledialog.getBtnInnerColor().setBackground(rectangle.getInner());
			rectangledialog.setVisible(true);

			if (rectangledialog.isOk()) {
				Rectangle edited = rectangledialog.getRectangle();
				edited.setIsSelected(true);
				command = new Edit(model, shape, edited);
				// frame.getTglbtnSelect().setSelected(true);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
			}
		}

		else if (shape instanceof Square) {
			Square square = (Square) shape;

			squaredialog = new SquareDialog();
			squaredialog.getTextFieldX().setText(String.valueOf(square.getUpLeft().getX()));
			squaredialog.getTextFieldY().setText(String.valueOf(square.getUpLeft().getY()));
			squaredialog.getTextField_a().setText(String.valueOf(square.getA()));
			squaredialog.getBtnBorderColor().setBackground(square.getBorder());
			squaredialog.getBtnInnerColor().setBackground(square.getInner());
			squaredialog.setVisible(true);

			if (squaredialog.isOk()) {
				Square edited = squaredialog.getSquare();
				edited.setIsSelected(true);
				command = new Edit(model, shape, edited);
				// frame.getTglbtnSelect().setSelected(true);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
			}
		}

		else if (shape instanceof Circle) {
			Circle circle = (Circle) shape;

			circledialog = new CircleDialog();
			circledialog.getTextFieldX().setText(String.valueOf(circle.getCenter().getX()));
			circledialog.getTextFieldY().setText(String.valueOf(circle.getCenter().getY()));
			circledialog.getTextField_a().setText(String.valueOf(circle.getR()));
			circledialog.getBtnBorderColor().setBackground(circle.getBorder());
			circledialog.getBtnInnerColor().setBackground(circle.getInner());
			circledialog.setVisible(true);

			if (circledialog.isOk()) {
				Circle edited = circledialog.getCircle();
				edited.setIsSelected(true);
				command = new Edit(model, shape, edited);
				// frame.getTglbtnSelect().setSelected(true);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
			}
		}

		else if (shape instanceof HexagonAdapter) {
			HexagonAdapter hexagonAdapter = (HexagonAdapter) shape;

			hexagondialog = new HexagonDialog();
			hexagondialog.getTextFieldX().setText(String.valueOf(hexagonAdapter.getUpLeft().getX()));
			hexagondialog.getTextFieldY().setText(String.valueOf(hexagonAdapter.getUpLeft().getY()));
			hexagondialog.getTextField_a().setText(String.valueOf(hexagonAdapter.getR()));
			hexagondialog.getBtnBorderColor().setBackground(hexagonAdapter.getBorder());
			hexagondialog.getBtnInnerColor().setBackground(hexagonAdapter.getInner());
			hexagondialog.setVisible(true);

			if (hexagondialog.isOk()) {
				HexagonAdapter edited = hexagondialog.getHexagon();
				edited.setIsSelected(true);
				command = new Edit(model, shape, edited);
				// frame.getTglbtnSelect().setSelected(true);
				command.execute();
				frame.addTextFrameLog(command.logText());
				frame.getView().repaint();

				addCommands(command);
				setUndoRedoCommandsToNULL();
			}
		}

	}

	public void btnDeletePressed(ActionEvent e) {

		// ovde bi mogao i onaj klon mozda se uvede>>???
		// prolazi kroz listu i one koji nisu selektovani dodaje u templistu koju
		// kasnije pridamo glavnoj listi moja metoda
		ArrayList<Shape> forDelete = new ArrayList<Shape>();

		for (int i = 0; i < model.getAll().size(); i++) {
			if (model.get(i).getIsSelected() == true) {
				forDelete.add(model.get(i));
			}
		}

		for (Shape s : forDelete) {

			command = new Delete(model, s);
			command.execute();
			frame.addTextFrameLog(command.logText());
			frame.getView().repaint();

			addCommands(command);
			setUndoRedoCommandsToNULL();
			frame.getView().repaint();

		}
		notifyObserver();

//		ArrayList<Shape> tempList = new ArrayList<Shape>();
//		for (int i = 0; i < model.getAll().size(); i++) {
//			if (model.get(i).getIsSelected() == false)
//				tempList.add(model.get(i));
//
//			else {
//				command = new Delete(model, model.get(i));
//				command.execute();
//				addCommands(command);
//				setUndoRedoCommandsToNULL();
//			}
//		}
//		model.setAll(tempList);
//		notifyObserver();

//		for (int i = 0; i < model.getAll().size(); i++) {
//			if (model.get(i).getIsSelected() == true) 
//			{
//				command=new Delete(model,shape);
//				command.execute();
				//frame.addTextFrameLog(command.logText());
//				addCommands(command);
//			}
//		}

	}

	public void bringToBack() {

		command = new BringToBack(model, model.getOneSelectedShape());
		command.execute();
		frame.addTextFrameLog(command.logText());
		frame.getView().repaint();

		addCommands(command);
		setUndoRedoCommandsToNULL();
		notifyObserver();

	}

	// Treba napraviti funkciju koja vraca Shape S koji je selektovan-samo ako je
	// jedan selektovan oblik i onda poziva funkciju bring to back tj komandu
	// Posle svih ovih funkcija treba dodati te komande u listu komandi
	// i jos treba za sve funkcije proveriti da li je vec zadnji ili prvi na lisiti

	public void bringToFront() {

		command = new BringToFront(model, model.getOneSelectedShape());
		command.execute();
		frame.addTextFrameLog(command.logText());
		frame.getView().repaint();

		addCommands(command);
		setUndoRedoCommandsToNULL();
		notifyObserver();

	}

	// ako je vec zadnji onemogucite ove dve opcije ili prvi
	public void toFront() {

		command = new ToFront(model, model.getOneSelectedShape());
		command.execute();
		frame.addTextFrameLog(command.logText());
		frame.getView().repaint();

		addCommands(command);
		setUndoRedoCommandsToNULL();
		notifyObserver();

	}

	public void toBack() {

		command = new ToBack(model, model.getOneSelectedShape());
		command.execute();
		frame.addTextFrameLog(command.logText());
		frame.getView().repaint();

		addCommands(command);
		setUndoRedoCommandsToNULL();
		notifyObserver();

	}

	public void undo() {
		if (commands.isEmpty() == false) {
			command = new UndoRedo(shape, this);
			command.execute();
			String s="UNDO " + command.logText();
			frame.getView().repaint();

;

			frame.addTextFrameLog(s);
			LoggingCommands.add(command);
			notifyObserver();

		}

	}

	public void redo() {
		if (undoRedoCommands.isEmpty() == false) {
			command = new UndoRedo(shape, this);
			
			command.unexecute();
			frame.addTextFrameLog("REDO " + command.logText());
			frame.getView().repaint();

			LoggingCommands.add(command);
			notifyObserver();

		}
	}

	@Override
	public void setObserver(Observer observer) {
		this.observer = observer;

	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
//		System.out.println(model.getAll().size());
//		System.out.println(model.numberOfSelected());

		observer.updateButtonsStatus(model.getAll().size(), model.numberOfSelected(),
				model.IsItOneSelectedFirstOrLastShape(), undoRedoCommands.size());

	}

	public void save() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filterSer = new FileNameExtensionFilter("ser files", "ser");
		FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("txt files", "txt");
		fileChooser.setFileFilter(filterSer);
		fileChooser.addChoosableFileFilter(filterTxt);

		int result = fileChooser.showSaveDialog(frame.getView());

		if (result == JFileChooser.APPROVE_OPTION) {

			if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".ser")) {
				file = new File(fileChooser.getSelectedFile().toString());
				System.out.println(fileChooser.getSelectedFile().toString());
				lm = new LoggingManager(new LoggingSerialization());
				lm.createLog(frame.getView().getModel().getAll(), frame.getTextFrameLog(), file);
			} else if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".txt")) {
				file = new File(fileChooser.getSelectedFile().toString());
				System.out.println(fileChooser.getSelectedFile().toString());
				lm = new LoggingManager(new LoggingFile());
				lm.createLog(frame.getView().getModel().getAll(), frame.getTextFrameLog(), file);
			}
		}
	}

	public void open() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filterSer = new FileNameExtensionFilter("ser files", "ser");
		fileChooser.setFileFilter(filterSer);

		int result = fileChooser.showOpenDialog(frame.getView());

		file = fileChooser.getSelectedFile();

		if (result == JFileChooser.APPROVE_OPTION) {

			commands = new ArrayList<Command>();
			setUndoRedoCommandsToNULL();
			frame.getTextBox().setText("");
			LoggingCommands = new ArrayList<Command>();
			model.removeAll();
			readedcommands = new ArrayList<Command>();
			i = 0;
			notifyObserver();

			if (file.getAbsolutePath().endsWith(".ser")) {
				try {

					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream in = new ObjectInputStream(fis);

					readedcommands = (ArrayList<Command>) in.readObject();
					notifyObserver();
					in.close();
					fis.close();

					frame.getBtnLoad().setEnabled(true);

				}

				catch (IOException ex) {
					System.out.println("IOException is caught");
				}

				catch (ClassNotFoundException ex) {
					System.out.println("ClassNotFoundException" + " is caught");
				}

			}
		}

	}

	public void load() 
	{// todo

//		if (i < readedcommands.size()) {
//
//			readedcommands.get(i).setModel(model);
//			if (readedcommands.get(i) instanceof UndoRedo)
//				readedcommands.get(i).setController(this);
//			else {
//				commands.add(readedcommands.get(i));
//				setUndoRedoCommandsToNULL();
//			}
//			readedcommands.get(i).execute();
//			frame.addTextFrameLog(readedcommands.get(i).logText());
//
//			notifyObserver();
//
//			i++;
//
//			frame.getView().repaint();
//		}
//
//		if (i == readedcommands.size() || readedcommands.isEmpty()) {
//			frame.getBtnLoad().setEnabled(false);
//			readedcommands = new ArrayList<Command>();
//			i = 0;
//			// System.out.println("uso");
//
//		}

	}

//	public String textCommand(Command c)
//	{
//		if (c instanceof AddShape)
//			return c.logText();
//				
//		else if (c instanceof BringToBack)
//			return "Brought to the back " + selected.toString();
//		else if (c instanceof BringToFront)
//			
//			else if (c instanceof Delete)
//				
//				else if (c instanceof ToBack)
//					
//					else if (c instanceof ToFront)
//						
//						else if (c instanceof UndoRedo)
//						
//		
//	}

}