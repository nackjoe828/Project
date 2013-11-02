package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Model;

public class NavigationPanel extends JPanel{
	private JLabel label;
	private JButton login;
	private JButton logout;
	private MainFrame mainFrame;
	
	public NavigationPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel("Welcome!");
		this.createLoginButton();
		this.createLogoutButton();
		
		this.add(label);
		this.add(login);
	}
	
	private void setUsername(String username){
		label.setText(username);
	}
	
	private void createLoginButton(){
		login = new JButton("Log In");
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPage(SourceType.LOGIN);
			}
		});
	}
	
	private void createLogoutButton(){
		logout = new JButton("Log Out");
		logout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPage(SourceType.LOGOUT);
			}
		});
	}
	
	public void switchNavigation(SourceType type){
		switch (type){
		case LOGIN:
			this.removeAll();
			this.add(logout);
			break;
		case LOGOUT:
			this.removeAll();
			this.add(login);
			break;
		default:
			break;
		}
		this.revalidate();
	}
}
