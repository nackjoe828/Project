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
