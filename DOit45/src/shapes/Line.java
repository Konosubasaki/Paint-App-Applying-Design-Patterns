package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Line extends Shape implements Serializable {

	private Point start;
	private Point end;
//	private Color border;
//	private boolean isSelected = false;


	public Line(Point start, Point end) {
		super(Color.BLACK,false);
		this.start = start;
		this.end = end;

	}

	public Line(Point start, Point end, Color border) {
		super(border,false);
		this.start = start;
		this.end = end;
		
	}

	public Line() {
		// TODO Auto-generated constructor stub
		super(Color.BLACK,false);
	}

	public Point getStart() {
		return start;
	}

	//n
	public Point midpoint() {

		return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}


	public void draw(Graphics g) {
		g.setColor(super.getBorder());
		g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());

		if (super.getIsSelected() == true)
			selected(g);
	}

	//n
	public boolean contains(int x, int y) {
		Point clicked = new Point(x, y);
		return clicked.distance(start) + clicked.distance(end) < 0.3 + start.distance(end);

	}


	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		start.selected(g);
		end.selected(g);
		midpoint().selected(g);
	}

	public String toString() {
		String log = "Line X:" + start.getX() + " Y:" + start.getY() + " End point: " + end.getX() + " Y:" + end.getY()
				+ " border " + "#" + Integer.toHexString(getBorder().getRGB()).substring(2);
		// System.out.println(log);
		return log;

	}

	@Override
	public Shape clone() {
		Line c= new Line();
		c.setBorder(this.getBorder());
		c.setStart(this.getStart());
		c.setEnd(this.getEnd());
		return c;
		
	}
}
