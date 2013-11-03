package view.body;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.MainFrame;
import view.ButtonSourceType;
import view.PageType;
import view.SectionType;
import control.Controller;
import database.Model;

public class BodyPanel extends JPanel{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	
	private MainFrame mainFrame;
	private HashMap<PageType, WindowPanel> displayContainer;
	private WindowPanel currentPanel;
	
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
		this.revalidate();
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
	
	public String getQuery(){
		StartPanel panel = (StartPanel)currentPanel;
		return panel.generateQuery();
	}
	
	public void sendQuery(String query){
		mainFrame.sendQuery(query);
	}
	
	public void setUserName(String query){
		mainFrame.setUserName(query);
	}
	
	public void switchSection(SectionType sectionType){
		currentPanel.switchSection(sectionType);
	}
}
