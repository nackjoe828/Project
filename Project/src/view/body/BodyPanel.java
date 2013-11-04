package view.body;

import java.util.HashMap;

import javax.swing.JPanel;

import view.MainFrame;
import view.ButtonSourceType;
import view.PageType;
import view.QueryGenerator;
import view.SectionType;

public class BodyPanel extends JPanel implements QueryGenerator{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	
	private MainFrame mainFrame;
	private HashMap<PageType, WindowPanel> displayContainer;
	private WindowPanel currentPanel;
	private String[] username;
	
	public BodyPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		displayContainer = new HashMap<PageType, WindowPanel>();
		this.initializePage();
		this.addDisplayPanel(PageType.START);
	}
	
	private void initializePage(){
		displayContainer.put(PageType.START, new StartPanel(this));
		displayContainer.put(PageType.ADMIN, new AdminPanel(this));
		displayContainer.put(PageType.USER, new UserPanel(this));
	}
	
	public void addDisplayPanel(PageType pageType){
		currentPanel = displayContainer.get(pageType);
		this.add(currentPanel.get());
	}
	
	public void showResult(String result){
		currentPanel.display(result);
	}
	
	public void switchBody(PageType pageType){
		this.removeAll();
		this.addDisplayPanel(pageType);
		this.repaint();
	}
	
	public boolean isNewUser(){
		StartPanel panel = (StartPanel)currentPanel;
		return panel.isNewUser();
	}
	
	public boolean isUser(){
		StartPanel panel = (StartPanel)currentPanel;
		return panel.isUser();
	}
	
	public void sendMessage(ButtonSourceType type){
		mainFrame.sendMessage(type);
	}
	
	public String getUpdate(){
		StartPanel panel = (StartPanel)currentPanel;
		return panel.registerNewUser();
	}
	
	public String sendUser(){
		StartPanel panel = (StartPanel)currentPanel;
		return panel.sendUser();
	}
	
	public void setUserName(String[] username){
		this.username = username;
	}
	
	public String getUserId(){
		return username[0];
	}
	
	@Override
	public void sendUpdate(String update){
		mainFrame.sendUpdate(update);
	}
	
	@Override
	public void sendQuery(String query){
		mainFrame.sendQuery(query);
	}
	
	public void setUserName(String query){
		mainFrame.setUserName(query);
	}
	
	public void switchSection(ButtonSourceType type){
		currentPanel.switchSection(type);
	}
}
