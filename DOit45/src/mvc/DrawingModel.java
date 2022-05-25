package mvc;

import shapes.Shape;
import java.util.ArrayList;


public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	
	public Shape getLastShape() {
		if (shapes.size()>0)
		return shapes.get(shapes.size()-1);
		
		else
			return null;
	}
	public void add(Shape s) {
		shapes.add(s);
	}
	

	public ArrayList<Shape> getAll() {
		return shapes;
	}
	
	public void removeAll() {
		
		shapes=new ArrayList<Shape>();
	}
	public void setAll(ArrayList<Shape> shapes) {
		this.shapes=shapes;
	}

	public Shape get(int i) {
		return shapes.get(i);
	}


	public int getIndex(Shape shape) {
		for(int i=0;i<shapes.size();i++) {
			if(shapes.get(i).equals(shape)) {
				return i;
			}
		}

		return -1;
	}
	
	
	public void remove(Shape s) {
		shapes.remove(s);
	}

	public boolean isItLast(Shape s) {
		return shapes.get(shapes.size()-1).equals(s);

	}
	
	public boolean isItFirst(Shape s) {
		return shapes.get(0).equals(s);

	}
	
	public int IsItOneSelectedFirstOrLastShape()
	{
		if(numberOfSelected()==1)
		{
			if(isItLast(getOneSelectedShape())==true)
				return 3;
			else if(isItFirst(getOneSelectedShape())==true)
				return 1;
			else
				return 2;
			
		}
		else 
		return 0;
	}
	
	public int numberOfSelected() {
		int counter=0;
		for(Shape s : shapes)
		{
			if (s.getIsSelected()==true)
				counter++;			
		}
		return counter;
		
	}
	
	public Shape getOneSelectedShape()
	{
		for(Shape s : shapes)
		{
			if (s.getIsSelected()==true)
			return s;
		}
		return null;
		
	}
	


}