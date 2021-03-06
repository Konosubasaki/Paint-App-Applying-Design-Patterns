package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable {
	

	private Color border;
	private boolean isSelected = false;
	
	public Shape(Color color, boolean b)
	{
		setBorder(color);
		isSelected=b;
		
	}

	abstract public void draw(Graphics g);

	abstract public boolean contains(int x, int y);

	abstract public void selected(Graphics g);

	abstract public String toString();
	
	abstract public Shape clone();
	

	public boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color border) {
		this.border = border;
	}
	
	
	

}