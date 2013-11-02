import database.Model;

import java.sql.*;

import control.Controller;
import view.MainFrame;

public class Video {
	public Video() {
		try{
			Model model = new Model();
			Controller controller = new Controller(model);
			MainFrame frame = new MainFrame(controller);
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
}
