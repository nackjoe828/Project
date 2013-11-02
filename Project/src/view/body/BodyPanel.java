package view.body;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.MainFrame;
import view.ButtonSourceType;
import control.Controller;
import database.Model;

public class BodyPanel extends JPanel{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	
	private JTextArea textarea1;
	private JTextArea textarea2;
	private MainFrame mainFrame;
	private LoginRegisterPanel lrPanel;
	
	
	public BodyPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		textarea1 = new JTextArea("Table will be printed here. t1");
		textarea2 = new JTextArea("Table will be printed here. t2");
		lrPanel = new LoginRegisterPanel(this);
		this.add(lrPanel);
	}
	
	public void switchBody(ButtonSourceType type){
		this.removeAll();
		switch (type){
		case LOGIN:
		case REGISTER:
			this.add(textarea1);
			break;
		case LOGOUT:
			this.add(lrPanel);
			break;
		default:
			break;
		}
		this.revalidate();
	}
	
	public boolean isUser(){
		return lrPanel.isUser();
	}
	
	public String getQuery(){
		return lrPanel.generateQuery();
	}
	
	public void showResult(String result){
		textarea1.setText(result);
		this.repaint();
	}
}
