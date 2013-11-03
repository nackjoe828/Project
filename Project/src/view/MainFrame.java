package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.body.BodyPanel;
import view.navigation.NavigationPanel;
import control.Controller;
import database.Model;

public class MainFrame extends JFrame implements ChangeListener{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	
	private Controller controller;
	private NavigationPanel nPanel;
	private BodyPanel bPanel;
	private String username;
	
	public MainFrame(Controller controller) throws Exception{
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.LIGHT_GRAY);
		this.setTitle("Video");
		this.setLayout(new BorderLayout());
		this.controller = controller;
		
		nPanel = new NavigationPanel(this);
		bPanel = new BodyPanel(this);
		
		this.add(BorderLayout.WEST, nPanel);
		this.add(BorderLayout.CENTER, bPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public void sendUpdate(String update){
		controller.update(update);
	}
	
	public void sendQuery(String query){
		controller.select(query);
	}
	
	public void sendButtonType(ButtonSourceType type){
		controller.show(type);
	}
	
	public void setUserName(String query){
		username = controller.getUserName(query);
	}
	
	public String getUserName(){
		return username;
	}
	
	public void sendMessage(ButtonSourceType type){
		switch (type){
		case LOGIN:
			if(bPanel.isUser()) {
				//get user info
				this.sendQuery(bPanel.getQuery());
				//retrieve the result
				username = controller.getResult();
				nPanel.setUserName(username);
				this.switchPage(PageType.USER);
			}
			else this.switchPage(PageType.ADMIN);
			break;
		case REGISTER:
			if(bPanel.isNewUser()){
				//get user info to be inserted and send it to controller
				this.sendUpdate(bPanel.getUpdate());
				//select the user info
				this.sendQuery(bPanel.getQuery());
				username = controller.getResult();
				nPanel.setUserName(username);
				this.switchPage(PageType.USER);
			}
			break;
		case LOGOUT:
			this.switchPage(PageType.START);
			break;
		case ADMIN_USER:
		case ADMIN_VIDEO:
		case ADMIN_HISTORY:
		case ADMIN_FAVORITES:
		case ADMIN_CHANNEL:
			sendButtonType(type);
			showTable(getResult());
			break;
		case USER_UPLOAD:
			this.switchSection(SectionType.USER_UPLOAD);
			break;
		case USER_HISTORY:
			this.switchSection(SectionType.USER_HISTORY);
			break;
		case USER_FAVORITES:
			this.switchSection(SectionType.USER_FAVORITES);
			break;
		default:
			break;
		}
	}
	
	/**
	 * switch pages between start, user and admin
	 * @param pageType type of the page to be desplayed
	 */
	public void switchPage(PageType pageType){
		nPanel.switchNavigation(pageType);
		bPanel.switchBody(pageType);
	}
	
	public void showTable(String result){
		bPanel.showResult(result);
	}
	
	public void switchSection(SectionType sectionType){
		bPanel.switchSection(sectionType);
	}
	
	public String getResult(){
		return controller.getResult();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		bPanel.showResult(getResult());
	}
}
