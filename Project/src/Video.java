import database.Model;
import java.sql.*;

public class Video {
	public Video() {
		Model model = new Model();
		model.close();
	}
	
	public static void main(String[] args) {
		Video v = new Video();
	}
}
