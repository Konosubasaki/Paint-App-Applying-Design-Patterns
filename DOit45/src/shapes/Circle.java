package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends AreaShape implements Serializable {

	private Point center;
	private int r;

	public Circle(Point center, int r, Color border, Color inner) {
		super(border,false,inner);
		this.center = center;
		this.r = r;
	}


	public Circle() {
		super(Color.BLACK,false,Color.BLACK);
		// TODO Auto-generated constructor stub
	}

	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public void draw(Graphics g) {
		g.setColor(getBorder());
		g.drawOval(center.getX() - r, center.getY() - r, 2 * r, r * 2);

		
		g.setColor(getInner());
		g.fillOval(center.getX() - r + 1, center.getY() - r + 0, 2 * r - 1, r * 2 - 1);

		if (getIsSelected() == true)
			selected(g);
	}

	

	public boolean contains(int x, int y) {
		//maj
		Point clicked = new Point(x, y);
		return (this.center.distance(clicked) < r);
	}

	public void selected(Graphics g) {

		g.setColor(Color.BLUE);
		center.selected(g);
		new Point(center.getX() + r, center.getY()).selected(g);
		new Point(center.getX(), center.getY() + r).selected(g);
		new Point(center.getX() - r, center.getY()).selected(g);
		new Point(center.getX(), center.getY() - r).selected(g);

	}

	public String toString() {
		String log = "Circle X:" + center.getX() + " Y:" + center.getY() + " Radius: " + r + " border " + "#"
				+ Integer.toHexString(getBorder().getRGB()).substring(2) + " inner " + "#"
				+ Integer.toHexString(getInner().getRGB()).substring(2);
		// System.out.println(log);
		return log;

	}


	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		Circle c=new Circle();
		c.setBorder(this.getBorder());
		c.setInner(this.getInner());
		c.setIsSelected(this.getIsSelected());
		c.setR(this.getR());
		c.setCenter(this.getCenter());
		return c;
	}

}
