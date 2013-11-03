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
	private String user;
	
	public MainFrame(Controller controller) throws Exception{
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.LIGHT_GRAY);
		this.setTitle("Video");
		this.setLayout(new BorderLayout());
		this.controller = controller;
		this.controller.addListener(this);
		
		nPanel = new NavigationPanel(this);
		bPanel = new BodyPanel(this);
		
		this.add(BorderLayout.WEST, nPanel);
		this.add(BorderLayout.CENTER, bPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public void sendQuery(ButtonSourceType type){
		controller.show(type);
	}
	
	public void sendMessage(ButtonSourceType type){
		switch (type){
		case ADMIN_USER:
		case ADMIN_VIDEO:
		case ADMIN_HISTORY:
		case ADMIN_FAVORITES:
		case ADMIN_CHANNEL:
			sendQuery(type);
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
		case UPLOAD_VIDEO:
			bPanel.showResult("uploaded!!");
			break;
		case LOGIN:
		case REGISTER:
			this.switchPage(PageType.USER);
			break;
		case LOGOUT:
			this.switchPage(PageType.START);
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
