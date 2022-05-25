package command;

import java.io.Serializable;

import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;

public class Unselect implements Command, Serializable {

	private Shape s;

	public Unselect(Shape s) {
		this.s = s;
	}
	
	public Unselect() {
		// TODO Auto-generated constructor stub
	}

	public Shape getShape()
	{
		return s;
	}
	
	public void setShape(Shape s)
	{
		this.s=s;
	}
	

	public void execute() {
		s.setIsSelected(false);
		System.out.println(logText());
		//return logText();

	}

	public void unexecute() {
		s.setIsSelected(true);
		System.out.println(logText());
		//return logText();

	}

	public String logText() {
		return "Deselected " + s.toString();

	}


}
