package command;

import java.io.Serializable;

import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;

public class BringToBack implements Command , Serializable{

	transient private DrawingModel model;
	private int previousIndex;
	private Shape selectedShape;

	public BringToBack(DrawingModel model, Shape selectedShape) {
		this.model = model;
		this.selectedShape = selectedShape;
	}
	
	public BringToBack() {
		// TODO Auto-generated constructor stub
	}

	public void setModel(DrawingModel model)
	{
		this.model=model;
	}
	
	public Shape getShape()
	{
		return selectedShape;
	}
	
	public void execute() {
			Shape temp = selectedShape;
			previousIndex = model.getIndex(selectedShape);

			model.getAll().remove(selectedShape);
			model.getAll().add(0, temp);
			System.out.println(logText());
			//return logText();	}
	}
	public void unexecute() {
		model.getAll().remove(selectedShape);
		model.getAll().add(previousIndex, selectedShape);
		System.out.println(logText());
		//return logText();

	}

	public String logText() {
		return "Brought to the back " + selectedShape.toString();

	}

	
	public void setShape(Shape s)
	{
		this.selectedShape=s;
	}
	


}
