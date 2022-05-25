package strategy;

import java.io.BufferedWriter;
import java.io.File;
import shapes.Shape;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import command.Command;


public class LoggingFile implements Logging {

	@Override
	public void createLog(ArrayList<Shape> shapes, String string,File file) {
		// TODO Auto-generated method stub
		//path = "\"C:\\\\Users\\\\h\\\\Desktop\\\\abc.log\" + name + ".txt";

		try {

		    if (!file.exists()) {

		        file.createNewFile();
		    }

		    FileWriter fw = new FileWriter(file, true);
		    BufferedWriter bw = new BufferedWriter(fw);

		    bw.write(string);
		    bw.close();
		    fw.close();
		    
		    
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		  
	        
	        

//		    FileReader fr = new FileReader(file);
//
//		    BufferedReader br = new BufferedReader(fr);
//
//		    fw = new FileWriter(file, true);
//
//		    bw = new BufferedWriter(fw);
//
//		    while (br.ready()) {
//
//		        String line = br.readLine();
//		        System.out.println(line);
//
//		        bw.write(line);
//		        bw.newLine();
//
//		    }
//		    br.close();
//		    fr.close();
//
//		} catch (IOException ex) {
//		    ex.printStackTrace();
//		}
//
	}
	
	
}
