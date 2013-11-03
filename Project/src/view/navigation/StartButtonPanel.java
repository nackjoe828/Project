package view.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.ButtonSourceType;

public class StartButtonPanel extends JPanel implements ButtonPanel{
	private JButton register;
	private JButton login;
	private NavigationPanel nPanel;
	
	public StartButtonPanel(NavigationPanel nPanel){
		super();
		this.nPanel = nPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.createRegisterButton();
		this.createLoginButton();
		
	}
	
	private void createRegisterButton(){
		register = new JButton("Register");
		register.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(ButtonSourceType.REGISTER);
			}
		});
		this.add(register);
	}
	
	private void createLoginButton(){
		login = new JButton("Log In");
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(ButtonSourceType.LOGIN);
			}
		});
		this.add(login);
	}

	@Override
	public void sendMessage(ButtonSourceType type) {
		nPanel.sendMessage(type);
	}

	@Override
	public JPanel get() {
		return this;
	}
}
