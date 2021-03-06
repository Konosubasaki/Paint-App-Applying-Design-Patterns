package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Square extends AreaShape  implements Serializable {

	private Point upLeft;
	private int a;

	public Square(Point upLeft, int a, Color border, Color inner) {
		super(border,false,inner);
		this.upLeft = upLeft;
		this.a = a;
	
	}


	public Square() {
		// TODO Auto-generated constructor stub
		super(Color.BLACK,false,Color.BLACK);
	}

	
	public Point getUpLeft() {
		return upLeft;
	}

	public void setUpLeft(Point upLeft) {
		this.upLeft = upLeft;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void draw(Graphics g) {
		g.setColor(getBorder());
		g.drawRect(upLeft.getX(), upLeft.getY(), a, a);

		g.setColor(getInner());
		g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, a - 1, a - 1);

		if (getIsSelected() == true)
			selected(g);
	}

	public boolean contains(int x, int y) {
		return (upLeft.getX() <= x && x <= (upLeft.getX() + a) && upLeft.getY() <= y && y <= (upLeft.getY() + a));
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		upLeft.selected(g);
		new Point(upLeft.getX() + a, upLeft.getY() + a).selected(g);
		new Line(new Point(upLeft.getX() + a, upLeft.getY()), new Point(upLeft.getX(), upLeft.getY() + a)).selected(g);

	}

	public String toString() {
		String log = "Square X:" + upLeft.getX() + " Y:" + upLeft.getY() + " A:" + a + " border " + "#"
				+ Integer.toHexString(getBorder().getRGB()).substring(2) + " inner " + "#"
				+ Integer.toHexString(getInner().getRGB()).substring(2);
		// System.out.println(log);
		return log;

	}


	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		Square c=new Square();
		c.setBorder(this.getBorder());
		c.setInner(this.getInner());
		c.setIsSelected(this.getIsSelected());
		c.setUpLeft(this.getUpLeft());
		c.setA(this.getA());
		return c;
	}

}
