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
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private Controller controller;
	private NavigationPanel nPanel;
	private BodyPanel bPanel;
	
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
	
	public void sendMessage(ButtonSourceType type){
		controller.show(type);
	}
	
	public void switchPage(ButtonSourceType type){
		nPanel.switchNavigation(type);
		bPanel.switchBody(type);
	}
	
	public boolean isUser(){
		return bPanel.isUser();
	}
	
	public String getResult(){
		return controller.getResult();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		bPanel.showResult(getResult());
	}
}
