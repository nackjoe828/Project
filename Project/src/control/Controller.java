package control;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.ButtonSourceType;
import database.Model;

public class Controller {
	private Model model;
	private ArrayList<ChangeListener> listener;
	private String result;
	
	public Controller(Model model){
		this.model = model;
		listener = new ArrayList<ChangeListener>();
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
		notifyListener();
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
		result = getResult();
	}
	
	public String getUserName(String query){
		try{
			model.select(query);
		}catch(Exception e){}
		return getResult();
	}
	
	public String getResult(){
		return model.getResult();
	}
	
	public void addListener(ChangeListener listener){
		this.listener.add(listener);
	}
	
	private void notifyListener(){
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener c : listener){
			c.stateChanged(event);
		}
	}
}
