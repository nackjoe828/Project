package database;

public class Model {
	private Connector connector;
	
	public Model(){
		connector = new Connector();
	}
	
	public String select(String query) throws Exception{
		return connector.select(query);
	}
	
	public String getUser() throws Exception{
		return connector.select("select * from user");
	}
	
	public String getHistory() throws Exception{
		return connector.select("select * from history");
	}
	
	public String getVideo() throws Exception{
		return connector.select("select * from video");
	}
	
	public String getFavorites() throws Exception{
		return connector.select("select * from favorites");
	}
	
	public String getChannel() throws Exception{
		return connector.select("select * from channel");
	}
	
	/**
	 * call close method in Connector
	 */
	public void close(){
		connector.close();
	}
}
