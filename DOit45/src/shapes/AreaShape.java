package shapes;

import java.awt.Color;

public abstract class AreaShape extends Shape{
	
	private Color inner;
	
	public AreaShape(Color border, boolean b, Color inner) {
		super(border, b);
		this.setInner(inner);
		// TODO Auto-generated constructor stub
	}

	public Color getInner() {
		return inner;
	}

	public void setInner(Color inner) {
		this.inner = inner;
	}


	
	

}
