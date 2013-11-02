package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import control.Controller;
import control.SourceType;
import database.Model;

public class BodyPanel extends JPanel implements ChangeListener{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	
	private JTextArea textarea1;
	private JTextArea textarea2;
	private Controller controller;
	
	public BodyPanel(Controller controller){
		super();
		this.controller = controller;
		controller.addListener(this);
		//this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.DARK_GRAY);
		textarea1 = new JTextArea("Table will be printed here. t1");
		textarea2 = new JTextArea("Table will be printed here. t2");
	}
	
	public void switchBody(SourceType type){
		switch (type){
		case LOGIN:
			this.removeAll();
			this.add(textarea2);
			break;
		case LOGOUT:
			this.removeAll();
			this.add(textarea1);
			break;
		default:
			break;
		}
		this.revalidate();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		textarea1.setText(controller.getResult());
		this.repaint();
	}
}
