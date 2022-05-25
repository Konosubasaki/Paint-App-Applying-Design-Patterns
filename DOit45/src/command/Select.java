package command;

import java.io.Serializable;

import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;

public class Select implements Command, Serializable {

	transient private DrawingModel model;
	private Shape s;

	public Select(Shape s) {

		this.s = s;
	}
	
	public Select() {
		// TODO Auto-generated constructor stub
	}


	public Shape getShape()
	{
		return s;
	}
	
	
	public void execute() {
		s.setIsSelected(true);
		System.out.println(logText());
		//return logText();

	}

	public void unexecute() {
		s.setIsSelected(false);
		System.out.println(logText());
		//return logText();
	}

	public String logText() {
		return "Selected " + s.toString();

	}

	public void setShape(Shape s)
	{
		this.s=s;
	}
	

}
