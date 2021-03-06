package command;

import java.io.Serializable;

import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;

public class AddShape implements Command , Serializable{

	transient private DrawingModel model;
	private Shape s;
	


	public AddShape(DrawingModel model, Shape s) {
		this.model = model;
		this.s = s;
	}


	public void execute() {
		//kod iscrtavanja da ne pamti da je bio seletkvovan
		s.setIsSelected(false);
		
		model.getAll().add(s);
		System.out.println(logText());

	}

	public void unexecute() {
		model.getAll().remove(s);
		System.out.println(logText());

	}

	public String logText() {
		return "Added " + s.toString();
 
	}

	
	public AddShape() {
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
	
	public void setShape(Shape s)
	{
		this.s=s;
	}
	

}
