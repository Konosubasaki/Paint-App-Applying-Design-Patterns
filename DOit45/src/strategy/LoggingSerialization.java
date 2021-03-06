package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import shapes.Shape;

import command.Command;

public class LoggingSerialization implements Logging {

	@Override
	public void createLog(ArrayList<Shape> shapes, String string, File file)  {
		// TODO Auto-generated method stub
		
		try {
		FileOutputStream fos= new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(shapes);
		oos.close();
		fos.close();
		
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}

}
