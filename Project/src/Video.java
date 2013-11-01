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
			System.out.println(model.select("select * from history where uid = ? and date = ?:1:2013-05-10"));
			model.close();
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
}
