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
			System.out.println("Here");
			model.getUser();
			System.out.println(model.getResult());
			//test update
			int i = model.update("insert into user values (10, Naoki Nakatani, naoki.nakatani@sjsu.edu)");
			//int i = model.update("delete from user where uid = 4");
			System.out.println(i);
			model.getUser();
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
