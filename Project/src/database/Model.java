package database;

public class Model {
	private Connector connector;
	
	public Model(){
		connector = new Connector();
	}
	
	/**
	 * call close method in Connector
	 */
	public void close(){
		connector.close();
	}
}
