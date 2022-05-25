package command;
import mvc.DrawingController;
import mvc.DrawingModel;
import shapes.Shape;
 

public interface Command {

	
	public void execute();
	
	public void unexecute();

	public Shape getShape();
	
	
	public String logText();
	
}
