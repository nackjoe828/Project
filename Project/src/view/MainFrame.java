package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import control.Controller;
import control.SourceType;
import database.Model;

public class MainFrame extends JFrame{
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
		
		nPanel = new NavigationPanel(this);
		bPanel = new BodyPanel(controller);
		
		this.add(BorderLayout.WEST, nPanel);
		this.add(BorderLayout.CENTER, bPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public void sendMessage(SourceType type){
		controller.show(type);
	}
	
	public void switchPage(SourceType type){
		nPanel.switchNavigation(type);
		bPanel.switchBody(type);
	}
}
