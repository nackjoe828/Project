package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import control.Controller;
import database.Model;

public class TablePanel extends JPanel implements ChangeListener{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	
	private JTextArea textarea;
	private Controller controller;
	
	public TablePanel(Controller controller){
		super();
		this.controller = controller;
		controller.addListener(this);
		//this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.DARK_GRAY);
		textarea = new JTextArea("Table will be printed here.");
		this.add(textarea);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		textarea.setText(controller.getResult());
		this.repaint();
	}
}
