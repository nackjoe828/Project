package view;

import control.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Model;

public class NavigationPanel extends JPanel{
	private JLabel name;
	private JButton logout;
	private MainFrame mainFrame;
	
	public NavigationPanel(String username, MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addUsername(username);
		
		this.add(new AdminButtonPanel(mainFrame));
		
		this.addLogoutButton();
	}
	
	private void addUsername(String username){
		name = new JLabel(username);
		this.add(name);
	}
	
	private void addLogoutButton(){
		logout = new JButton("Log Out");
		this.add(logout);
	}
}
