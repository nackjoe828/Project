import database.Model;
import java.sql.*;

public class Video {
	public Video() {
		try{
			Model model = new Model();
			System.out.println(model.getUser());
			System.out.println(model.getHistory());
			System.out.println(model.getVideo());
			System.out.println(model.getFavorites());
			System.out.println(model.getChannel());
			model.close();
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
}
