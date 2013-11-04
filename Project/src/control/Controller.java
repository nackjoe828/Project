package control;

import view.ButtonSourceType;
import view.MainFrame;
import database.Model;

public class Controller {
	private Model model;
	private MainFrame mFrame;
	private String result;
	
	public Controller(Model model){
		this.model = model;
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
		try{
			int i = model.update(update);
		}catch(Exception e){}
	}
	
	public void select(String query){
		try{
			model.select(query);
		}catch(Exception e){}
		result = model.getResult();
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
}
