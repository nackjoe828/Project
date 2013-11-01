package database;

public class Model {
	private Connector connector;
	
	public Model(){
		connector = new Connector();
	}
	
	public String select(String query) throws Exception{
		return connector.select(query);
	}
	
	/**
	 * call close method in Connector
	 */
	public void close(){
		connector.close();
	}
}
