package command;

import java.io.Serializable;

import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;

public class Delete implements Command, Serializable {

	transient private DrawingModel model;
	private Shape s;
	int indexOfDeleted;

	public Delete(DrawingModel model, Shape s) {
		this.model = model;
		this.s = s;
		indexOfDeleted=model.getIndex(s);
	}
	public Delete() {
		// TODO Auto-generated constructor stub
	}
	public void setModel(DrawingModel model)
	{
		this.model=model;
	}

	public Shape getShape()
	{
		return s;
	}
	
	public void execute() {
		model.getAll().remove(s);
		System.out.println(logText());
		//return logText();

	}

	public void unexecute() {
		model.getAll().add(indexOfDeleted, s);;
		System.out.println(logText());
		//return logText();

	}

	public String logText() {
		return "Deleted " + s.toString();

	}

	
	public void setShape(Shape s)
	{
		this.s=s;
	}
	


}
