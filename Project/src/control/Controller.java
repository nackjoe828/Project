package control;

import view.ButtonSourceType;
import view.MainFrame;
import view.PageType;
import database.Model;

public class Controller {
	private Model model;
	private MainFrame mFrame;
	private String result;
	private int updateStatus;
	private String[] user;
	private String[] selectedTuple;
	
	public Controller(Model model){
		this.model = model;
	}
	
	public void setFrame(MainFrame mFrame){
		this.mFrame = mFrame;
	}
	
	public void execute(ButtonSourceType type){
		try{
			switch(type){
			case REGISTER:
				if(mFrame.isNewUser()){
					update(mFrame.getUpdate());
					if(updateStatus == 1){
						select(mFrame.selectUser());
						setUserName();
						mFrame.switchPage(PageType.USER);
					}
					else{
						mFrame.showErrorMessage();
					}
				}
				break;
			case LOGIN:
				if(mFrame.isUser()){
					select(mFrame.selectUser());
					setUserName();
					mFrame.switchPage(PageType.USER);
				}
				else mFrame.switchPage(PageType.ADMIN);
				break;
			case LOGOUT:
				mFrame.switchPage(PageType.START);
				mFrame.showTable("clear", null);
				break;
			case ADMIN_USER:
			case ADMIN_VIDEO:
			case ADMIN_HISTORY:
			case ADMIN_FAVORITES:
			case ADMIN_CHANNEL:
				show(type);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case ARCHIVE:
			case USER_SEARCH:
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_UPLOAD:
				select("select * from video where uid = ?:" + user[0]);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_HISTORY:
				select("select * from history where uid = ?:" + user[0]);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_FAVORITES:
				select("select * from favorites where uid = ?:" + user[0]);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case UPLOAD_VIDEO:
				select("select * from video where uid = ?:" + user[0]);
				mFrame.showTable(result, null);
				break;
			case SELECT:
				select("select * from history where uid = ?:" + user[0]);
				mFrame.showTable(result, null);
				break;
			default:
				break;
			}
		}catch(Exception e){System.out.println("error");}
	}
	
	public void setUserName(){
		//controller.getUserName contains labels which have to be omitted
		String[] str = result.split("\n");
		user = str[1].split("\t");
		mFrame.setUserName(user);
	}
	
	public void show(ButtonSourceType type){
		try{
			switch (type){
			case ADMIN_USER:
				model.getUser();
				break;
			case ADMIN_VIDEO:
				model.getVideo();
				break;
			case ADMIN_HISTORY:
				model.getHistory();
				break;
			case ADMIN_FAVORITES:
				model.getFavorites();
				break;
			case ADMIN_CHANNEL:
				model.getChannel();
				break;
			default:
				break;
			}
		}catch(Exception e){}
		result = model.getResult();
	}
	
	public void update(String update){
		updateStatus = model.update(update);
	}
	
	public void select(String query){
		try{
			model.select(query);
		}catch(Exception e){}
		result = model.getResult();
	}
	
	public void setSelectedTuple(String[] row){
		this.selectedTuple = row;
	}
	
	public String getUserName(String query){
		try{
			model.select(query);
		}catch(Exception e){}
		return getResult();
	}
	
	public String getResult(){
		return result;
	}
	
	public int getUpdateStatus(){
		return updateStatus;
	}
}
