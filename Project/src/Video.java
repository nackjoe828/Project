import database.Model;

import java.sql.*;

import control.Controller;
import view.MainFrame;

public class Video {
	public Video() {
		try{
			Model model = new Model();
			Controller controller = new Controller(model);
			/*
			model.getUser();
			System.out.println(model.getResult());
			model.getHistory();
			System.out.println(model.getResult());
			model.getVideo();
			System.out.println(model.getResult());
			model.getFavorites();
			System.out.println(model.getResult());
			model.getChannel();
			System.out.println(model.getResult());
			*/
			//System.out.println(model.select("select * from history where rating = ?:5"));
			MainFrame frame = new MainFrame(controller);
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
}
