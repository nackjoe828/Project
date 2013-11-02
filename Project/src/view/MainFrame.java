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
	
	public MainFrame(Controller controller) throws Exception{
		super();
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.LIGHT_GRAY);
		this.setTitle("Video");
		this.setLayout(new BorderLayout());
		
		this.controller = controller;
		
		this.add(BorderLayout.WEST, new NavigationPanel("naoki", this));
		this.add(BorderLayout.CENTER, new TablePanel(controller));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public void sendMessage(SourceType type){
		controller.show(type);
	}
}
