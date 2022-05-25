package strategy;

import java.io.File;
import java.util.ArrayList;

import command.Command;
import shapes.Shape;

public interface Logging{

	public void createLog(ArrayList<Shape> shapes, String string,File file);
}
