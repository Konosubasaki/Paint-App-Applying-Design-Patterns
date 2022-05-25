package mvc;
import javax.swing.JFrame;

public class DrawingApp {
	
	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);

		frame.setSize(1200,700 );
		frame.setVisible(true);
		frame.setTitle("DO aplikacija IT45-2016");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
		


	}

}
