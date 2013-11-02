package view.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MainFrame;
import view.ButtonSourceType;
import database.Model;

public class NavigationPanel extends JPanel{
	private JLabel label;
	private JButton register;
	private JButton login;
	private JButton logout;
	private MainFrame mainFrame;
	private AdminButtonPanel abPanel;
	private UserButtonPanel ubPanel;
	
	public NavigationPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel("Welcome!");
		this.createRegisterButton();
		this.createLoginButton();
		this.createLogoutButton();
		abPanel = new AdminButtonPanel(this);
		ubPanel = new UserButtonPanel(this);
		
		this.add(label);
		this.add(login);
		this.add(register);
	}
	
	public void sendMessage(ButtonSourceType type){
		mainFrame.sendMessage(type);
	}
	
	private void createRegisterButton(){
		register = new JButton("Register");
		register.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPage(ButtonSourceType.REGISTER);
			}
		});
	}
	
	private void createLoginButton(){
		login = new JButton("Log In");
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPage(ButtonSourceType.LOGIN);
			}
		});
	}
	
	private void createLogoutButton(){
		logout = new JButton("Log Out");
		logout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPage(ButtonSourceType.LOGOUT);
			}
		});
	}
	
	public void switchNavigation(ButtonSourceType type){
		this.removeAll();
		this.add(label);
		switch (type){
		case LOGIN:
		case REGISTER:
			if(mainFrame.isUser()) {
				this.add(ubPanel);
				mainFrame.setUser();
				label.setText(mainFrame.getUser());
			}
			else this.add(abPanel);
			this.add(logout);
			break;
		case LOGOUT:
			this.add(login);
			this.add(register);
			break;
		default:
			break;
		}
		this.revalidate();
	}
}
