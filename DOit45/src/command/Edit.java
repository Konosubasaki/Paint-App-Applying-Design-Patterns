package command;

import java.awt.Color;
import java.io.Serializable;

import shapes.HexagonAdapter;
import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;

public class Edit implements Command, Serializable {

	transient private DrawingModel model;
	private Shape originalState;
	private Shape oldState;
	private Shape newState;


	public Edit(DrawingModel model, Shape oldState, Shape newState) {
		originalState = oldState.clone();
		this.oldState=oldState;
		this.newState=newState;
		
	}
	
	public Edit() {
		// TODO Auto-generated constructor stub
	}
	public void setModel(DrawingModel model)
	{
		this.model=model;
	}

	

	public Shape getOriginalState() {
		return originalState;
	}
	public void setOriginalState(Shape originalState) {
		this.originalState = originalState;
	}
	public Shape getOldState() {
		return oldState;
	}
	public void setOldState(Shape oldState) {
		this.oldState = oldState;
	}
	public Shape getNewState() {
		return newState;
	}
	public void setNewState(Shape newState) {
		this.newState = newState;
	}
	public void execute() {// uzmi indeks od original stanja i umetni na taj indeks editovano stanje
		


		Color color;
		//inner color
		if (oldState instanceof Point)
		{
			int x=((Point)newState).getX();
			int y=((Point)newState).getY();
			
			((Point) oldState).setX(x);
			((Point) oldState).setY(y);
			
			
			oldState.setBorder(newState.getBorder());
			oldState.setIsSelected(newState.getIsSelected());
			
		}
		
		else if (oldState instanceof Line)
		{
			Point s=((Line)newState).getStart();
			Point e=((Line)newState).getEnd();
			
			((Line) oldState).setStart(s);
			((Line) oldState).setEnd(e);
			
			
			oldState.setBorder(newState.getBorder());
			oldState.setIsSelected(newState.getIsSelected());
			
		}
		else if (oldState instanceof Circle)
		{
			int r=((Circle)newState).getR();
			Point center=((Circle)newState).getCenter();
			color= ((Circle)newState).getInner();
			
			((Circle) oldState).setR(r);
			((Circle) oldState).setCenter(center);
			((Circle) oldState).setInner(color);
			

			oldState.setBorder(newState.getBorder());
			oldState.setIsSelected(newState.getIsSelected());
		}
		else if (oldState instanceof Rectangle)
		{
			
			int a=((Rectangle)newState).getA();
			int b=((Rectangle)newState).getB();
			Point upLeft=((Rectangle)newState).getUpLeft();
			color= ((Rectangle)newState).getInner();
			
			((Rectangle) oldState).setA(a);
			((Rectangle) oldState).setB(b);
			((Rectangle) oldState).setUpLeft(upLeft);
			((Rectangle) oldState).setInner(color);

			oldState.setBorder(newState.getBorder());
			oldState.setIsSelected(newState.getIsSelected());
		}
		else if (oldState instanceof Square)
		{
			
			int a=((Square)newState).getA();
			Point upLeft=((Square)newState).getUpLeft();
			color= ((Square)newState).getInner();
			
			((Square) oldState).setA(a);
			((Square) oldState).setUpLeft(upLeft);
			((Square) oldState).setInner(color);

			oldState.setBorder(newState.getBorder());
			oldState.setIsSelected(newState.getIsSelected());
		}
		else if (oldState instanceof HexagonAdapter)
		{
			int r=((HexagonAdapter)newState).getR();
			Point upLeft=((HexagonAdapter)newState).getUpLeft();
			color= ((HexagonAdapter)newState).getInner();
			
			((HexagonAdapter) oldState).setR(r);
			((HexagonAdapter) oldState).setUpLeft(upLeft);
			((HexagonAdapter) oldState).setInner(color);

			oldState.setBorder(newState.getBorder());
			oldState.setIsSelected(newState.getIsSelected());
		}
		
		
		
		
		
		
		
		System.out.println(logText());
		
	}

	public void unexecute() {
		
		
		if (oldState instanceof Point)
		{
			int x=((Point)originalState).getX();
			int y=((Point)originalState).getY();
			
			((Point) oldState).setX(x);
			((Point) oldState).setY(y);
			
			
			oldState.setBorder(originalState.getBorder());
			oldState.setIsSelected(originalState.getIsSelected());
			
		}
		
		else if (oldState instanceof Line)
		{
			Point s=((Line)originalState).getStart();
			Point e=((Line)originalState).getEnd();
			
			((Line) oldState).setStart(s);
			((Line) oldState).setEnd(e);
			
			
			oldState.setBorder(originalState.getBorder());
			oldState.setIsSelected(originalState.getIsSelected());
			
		}
		else if (oldState instanceof Circle)
		{
			int r=((Circle)originalState).getR();
			Point center=((Circle)originalState).getCenter();
			
			((Circle) oldState).setR(r);
			((Circle) oldState).setCenter(center);

			oldState.setBorder(originalState.getBorder());
			oldState.setIsSelected(originalState.getIsSelected());
		}
		else if (oldState instanceof Rectangle)
		{
			
			int a=((Rectangle)originalState).getA();
			int b=((Rectangle)originalState).getB();
			Point upLeft=((Rectangle)originalState).getUpLeft();
			
			((Rectangle) oldState).setA(a);
			((Rectangle) oldState).setB(b);
			((Rectangle) oldState).setUpLeft(upLeft);;

			oldState.setBorder(originalState.getBorder());
			oldState.setIsSelected(originalState.getIsSelected());
		}
		else if (oldState instanceof Square)
		{
			
			int a=((Square)originalState).getA();
			Point upLeft=((Square)originalState).getUpLeft();
			
			((Square) oldState).setA(a);
			((Square) oldState).setUpLeft(upLeft);;

			oldState.setBorder(originalState.getBorder());
			oldState.setIsSelected(originalState.getIsSelected());
		}
		else if (oldState instanceof HexagonAdapter)
		{
			int r=((HexagonAdapter)originalState).getR();
			Point upLeft=((HexagonAdapter)originalState).getUpLeft();
			
			((HexagonAdapter) oldState).setR(r);
			((HexagonAdapter) oldState).setUpLeft(upLeft);

			oldState.setBorder(originalState.getBorder());
			oldState.setIsSelected(originalState.getIsSelected());
		}
		
			
		
		
		System.out.println(logText());

	}

	public String logText() {
		return "Edited " + originalState.toString() + " To " + newState.toString();

	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return originalState;
	}




 
}
