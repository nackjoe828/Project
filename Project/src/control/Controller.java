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
			case USER:
				model.getUser();
				break;
			case VIDEO:
				model.getVideo();
				break;
			case HISTORY:
				model.getHistory();
				break;
			case FAVORITES:
				model.getFavorites();
				break;
			case CHANNEL:
				model.getChannel();
				break;
			default:
				break;
			}
		}catch(Exception e){}
		notifyListener();
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
