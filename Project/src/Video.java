import database.Model;

import java.sql.*;

import control.Controller;
import util.TablePanelGenerator;
import view.MainFrame;

public class Video {
	public Video() {
		try{
			Model model = new Model();
			//Controller controller = new Controller(model);
			//MainFrame frame = new MainFrame(controller);
			testTable(model);
			
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
	
	public void testTable(Model model) throws Exception{
		model.select("select * from history");
		System.out.println("Hello");
		TablePanelGenerator table = new TablePanelGenerator(model.getResult());
		table.print();
	}
}
