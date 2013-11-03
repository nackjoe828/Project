import database.Model;

import java.sql.*;

import javax.swing.JFrame;

import control.Controller;
import util.TablePanelGenerator;
import view.MainFrame;

public class Video {
	public Video() {
		try{
			Model model = new Model();
			Controller controller = new Controller(model);
			MainFrame frame = new MainFrame(controller);
			//testTable(model);
			
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
	
	public void testTable(Model model) throws Exception{
		model.select("select * from history");
		String str = model.getResult() + "\n" + model.getResult() + "\n"+ model.getResult();
		System.out.println(str);
		
		JFrame frame = new JFrame();
		frame.setSize(600, 300);
		frame.add(TablePanelGenerator.getPanel(str));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
