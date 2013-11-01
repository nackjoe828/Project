import database.Model;
import java.sql.*;

public class Video {
	public Video() {
		try{
			Model model = new Model();
			model.close();
		}catch(Exception e){}
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
}
