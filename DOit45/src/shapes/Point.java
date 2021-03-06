package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Serializable {
	private int x;
	private int y;

	public Point(int x, int y, Color border) {
		super(border,false);
		this.x = x;
		this.y = y;

	}

	public Point(int x, int y) {
		super(Color.BLACK,false);
		this.x = x;
		this.y = y;

	}

	public Point() {
		// TODO Auto-generated constructor stub
		super(Color.BLACK,false);
	}
 

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}



	public void draw(Graphics g) {
		g.setColor(getBorder());
		g.drawLine(x + 1, y + 1, x - 1, y - 1);
		g.drawLine(x - 1, y + 1, x + 1, y - 1);

		if (super.getIsSelected() == true)
			selected(g);
	}

	public boolean contains(int x, int y) {

		
		Point clicked = new Point(x, y);
		return (this.distance(clicked) < 5);

	}

	
	public double distance(Point p2) {
		//da bi proverila klik
		double dx = x - p2.getX();
		double dy = y - p2.y;
		return Math.sqrt(dx * dx + dy * dy);

	}


	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - 3, y - 3, 6, 6);
	}

	public String toString() {
		String log = "Point X:" + getX() + " Y:" + getY() + " border " + "#"
				+ Integer.toHexString(getBorder().getRGB()).substring(2);
		// System.out.println(log);
		return log;

	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		Point c=new Point();
		c.setBorder(this.getBorder());
		c.setX(this.getX());
		c.setY(this.getY());
		c.setIsSelected(this.getIsSelected());
		return c;
	}

}