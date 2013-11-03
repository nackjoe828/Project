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
import control.Controller;
import database.Model;

public class BodyPanel extends JPanel{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	
	private MainFrame mainFrame;
	private HashMap<PageType, DisplayPanel> displayContainer;
	private DisplayPanel currentPanel;
	
	public BodyPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		displayContainer = new HashMap<PageType, DisplayPanel>();
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
}
