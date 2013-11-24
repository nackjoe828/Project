package database;

public class Model {
	private Connector connector;
	private String result;
	
	public Model(){
		connector = new Connector();
		result = "";
	}
	
	public int update(String query){
		try{
			return connector.update(query);
		}catch(Exception e){
			return 0;
		}
	}
	
	public void select(String query) throws Exception{
		result = connector.select(query);
	}
	
	public void getUser() throws Exception{
		result = connector.select("select * from user");
	}
	
	public void getHistory() throws Exception{
		result = connector.select("select * from history");
		System.out.println("Pass");
	}
	
	public void getVideo() throws Exception{
		result = connector.select("select * from video");
	}
	
	public void getFavorites() throws Exception{
		result = connector.select("select * from favorites");
	}
	
	public void getChannel() throws Exception{
		result = connector.select("select * from channel");
	}
	
	public void getUserHistory(int uID) throws Exception
	{
		String quary = "SELECT h1.vID, h1.date	FROM History h1	WHERE h1.uID =" + Integer.toString(uID);
		result = connector.select(quary);
	}
	
	public void getLargestView() throws Exception
	{
		result = connector.select("SELECT vid FROM Video WHERE count >= all (SELECT count FROM Video)");
	}
	
	public void getRecentHot() throws Exception
	{
		result = connector.select("SELECT vid, title FROM Video where count >= all(select count from video where date > now() - interval 7 day and date < now() ) and date > now() - interval 7 day and date < now() ");
	}
	
	
	public String getResult(){
		return result.trim();
	}
	
	/**
	 * call close method in Connector
	 */
	public void close(){
		connector.close();
	}
}
