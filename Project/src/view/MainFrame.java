package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.body.BodyPanel;
import view.navigation.NavigationPanel;
import control.Controller;

public class MainFrame extends JFrame implements QueryGenerator{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	
	private Controller controller;
	private NavigationPanel nPanel;
	private BodyPanel bPanel;
	private String[] username;
	
	public MainFrame(Controller controller) throws Exception{
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.LIGHT_GRAY);
		this.setTitle("Video");
		this.setLayout(new BorderLayout());
		this.controller = controller;
		
		nPanel = new NavigationPanel(this);
		bPanel = new BodyPanel(this);
		
		JScrollPane scroll = new JScrollPane(bPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(BorderLayout.WEST, nPanel);
		this.add(BorderLayout.CENTER, scroll);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	@Override
	public void sendUpdate(String update){
		controller.update(update);
	}
	
	@Override
	public void sendQuery(String query){
		controller.select(query);
	}
	
	public void sendButtonType(ButtonSourceType type){
		controller.show(type);
	}
	
	public void setUserName(String user){
		//controller.getUserName contains labels which have to be omitted
		String[] str = controller.getUserName(user).split("\n");
		username = str[1].split("\t");
		nPanel.setUserName(username);
		bPanel.setUserName(username);
	}
	
	public String[] getUserName(){
		return username;
	}
	
	public void sendMessage(ButtonSourceType type){
		switch (type){
		case LOGIN:
			if(bPanel.isUser()) {
				//get user info
				this.sendQuery(bPanel.sendUser());
				//retrieve the result
				setUserName(controller.getResult());
				//pass the name of the user
				this.switchPage(PageType.USER);
			}
			else this.switchPage(PageType.ADMIN);
			break;
		case REGISTER:
			if(bPanel.isNewUser()){
				//get user info to be inserted and send it to controller
				this.sendUpdate(bPanel.getUpdate());
				//select the user info
				this.sendQuery(bPanel.sendUser());
				setUserName(controller.getResult());
				this.switchPage(PageType.USER);
			}
			break;
		case LOGOUT:
			this.switchPage(PageType.START);
			break;
		case SEARCH:
			showTable(getResult());
			break;
		case ADMIN_USER:
		case ADMIN_VIDEO:
		case ADMIN_HISTORY:
		case ADMIN_FAVORITES:
		case ADMIN_CHANNEL:
			sendButtonType(type);
			switchSection(type);
			showTable(getResult());
			break;
		case USER_HISTORY:
			sendQuery("select * from history where uid = ?:"
					+ username[0]);
			switchSection(type);
			showTable(getResult());
			break;
		case USER_UPLOAD:
			this.switchSection(type);
			sendQuery("select * from video where uid = ?:"
					+ username[0]);
			showTable(getResult());
			break;
		case USER_FAVORITES:
			this.switchSection(type);
			break;
		case UPLOAD_VIDEO:
			sendQuery("select * from video where uid = ?:"
					+ username[0]);
			showTable(getResult());
			break;
		case SELECT:
			sendQuery("select * from history where uid = ?:"
					+ username[0]);
			showTable(getResult());
			break;
		default:
			break;
		}
		this.revalidate();
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
	
	public void switchSection(ButtonSourceType type){
		bPanel.switchSection(type);
	}
	
	public String getResult(){
		return controller.getResult();
	}
}
