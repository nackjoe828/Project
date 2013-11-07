package view.navigation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import view.ButtonSourceType;

public class StartButtonPanel extends JPanel implements ButtonPanel{
	private JButton register;
	private JButton login;
	private NavigationPanel nPanel;
	private SpringLayout layout;
	
	public StartButtonPanel(NavigationPanel nPanel){
		super();
		this.setBackground(new Color(245,245,245));
		this.nPanel = nPanel;
		layout = new SpringLayout();
		this.setLayout(layout);
		this.createRegisterButton();
		this.createLoginButton();
		
	}
	
	private void createRegisterButton(){
		register = new JButton("Register");
		register.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.REGISTER);
			}
		});
		layout.putConstraint(layout.NORTH, register, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, register, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, register, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, register, -5, layout.EAST, this);
		this.add(register);
	}
	
	private void createLoginButton(){
		login = new JButton("Log In");
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.LOGIN);
			}
		});
		layout.putConstraint(layout.NORTH, login, 5, layout.SOUTH, register);
		layout.putConstraint(layout.WEST, login, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, login, 40, layout.SOUTH, register);
		layout.putConstraint(layout.EAST, login, -5, layout.EAST, this);
		this.add(login);
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		nPanel.sendMessage(type);
	}

	@Override
	public JPanel get() {
		return this;
	}
}
