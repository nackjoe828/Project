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
		result = connector.select("SELECT vid, title, date, count FROM Video where count >= all(select count from video where date > now() - interval 7 day and date < now() ) and date > now() - interval 7 day and date < now() ");
	}
	
	public void getMostFamous() throws Exception //work
	{
		result = connector.select("SELECT vid, count FROM Video	WHERE count >= all (SELECT count FROM Video) ");
	}
	
	public void getInactiveUser() throws Exception//work
	{
		result = connector.select("select name, uID, email from User where uID not in (select distinct uID from History where Date >= now() - interval 270 day);");
	}
	
	public void channelavgrating(int cID) throws Exception//work
	{
		String query = "select avg(h1.rat) from(select vid, avg(rating) as rat from history where vid in (select vid from video where cID = ";
		query = query + Integer.toString(cID) + ")group by vid)h1";
		System.out.println(query);
		result = connector.select(query);
	}
	
	public void latestVideo(int cID) throws Exception //
	{
		String query = "select vid, title, date from video where date in (select max(date) from video where cid =";
		query = query + Integer.toString(cID) + ")";
		//System.out.println(query);
		result = connector.select(query);
	}
	
	public void recommendation(int uID) throws Exception//work
	{
		String query = "select distinct vid, title, date from video where viD in (select distinct f3.vID from favorites f1, favorites f2, favorites f3 where f1.uID =";
		query = query + Integer.toString(uID) + " and f1.uID <> f2.uID and f1.vID = f2.vID and f2.uID = f3.uID and f3.vID <> f1.vID )limit 5";
		System.out.println(query);
		result = connector.select(query);
	}
	
	public void archievetable(String date) throws Exception//idk
	{
		String query = "CALL ArchievTableUser(";
		query = query + date + "); ";
		System.out.println(query);
		result = connector.select(query);

	}
	
	public void getChannel(String uID) throws Exception
	{
		String query = "select cID from channel where uID =" + uID;
		result = connector.select(query);
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
