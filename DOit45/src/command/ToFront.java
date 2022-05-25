package command;

import java.io.Serializable;

import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;

public class ToFront implements Command, Serializable {

	transient private DrawingModel model;
	private Shape s;

	public ToFront(DrawingModel model, Shape s) {
		this.model = model;
		this.s = s;
	}
	
	
	
	public ToFront() {
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

			
			model.getAll().set(model.getIndex(s), model.getAll().set(model.getIndex(s) + 1, s));
			System.out.println(logText());
			//return logText();
	}

	public void unexecute() {
		model.getAll().set(model.getIndex(s), model.getAll().set(model.getIndex(s) - 1, s));
		System.out.println(logText());
		//return logText();
	}

	public String logText() {
		return "To front " + s.toString();

	}


	public void setShape(Shape s)
	{
		this.s=s;
	}

}
