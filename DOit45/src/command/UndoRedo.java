package command;

import java.io.Serializable;

import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;

public class UndoRedo implements Command, Serializable {

	transient private DrawingController controller;
	private Shape s;
	private boolean isItRedo;

	public Shape getS() {
		return s;
	}

	public void setS(Shape s) {
		this.s = s;
	}

	public void setItRedo(boolean isItRedo) {
		this.isItRedo = isItRedo;
	}

	public UndoRedo(Shape s, DrawingController controller) {

		this.s = s;
		this.controller = controller;
		isItRedo=false;
	}
	
	public UndoRedo() {
		// TODO Auto-generated constructor stub
	}


	// Undo command
	// ovo sto ispisuje undo i redo treba narediti da loguje u glavnu listu
	// stringova
	public void execute() {
		
		if (isItRedo==true)
			unexecute();
		else {
		isItRedo=false;
		String ret= "UNDO " +controller.getLastCommand().logText();
		System.out.println(ret);
		controller.getLastCommand().unexecute();
		controller.addUndoRedoCommands(controller.getLastCommand());
		controller.deleteLastCommand();
		//return ret;
		}
		// prebaci u drugu listu i obrisi u command listi

	}

	// Redo command
	public void unexecute() {


		String ret= "REDO " + controller.getLastUndoRedoCommand().logText();
		System.out.println(ret);
		controller.getLastCommand().execute();

		controller.addCommands(controller.getLastUndoRedoCommand());
		controller.deleteLastUndoRedoCommands();
		isItRedo=true;

		// sve dok nije prazna lista

	}
	
	public Shape getShape()
	{
		return s;
	}

	@Override
	public String logText() {
		// TODO Auto-generated method stub
		return null;
	}




}
