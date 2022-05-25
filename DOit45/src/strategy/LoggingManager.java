package strategy;

import java.io.File;
import java.util.ArrayList;

import command.Command;
import shapes.Shape;

public class LoggingManager implements Logging {

	private Logging logging;
	
	public LoggingManager(Logging logging)
	{
		this.logging=logging;
		
	}

	@Override
	public void createLog(ArrayList<Shape> shapes, String string, File file) {
		// TODO Auto-generated method stub
		logging.createLog(shapes, string,file);
	}




}
