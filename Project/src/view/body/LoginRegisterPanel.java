package view.body;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.MainFrame;

public class LoginRegisterPanel extends JPanel{
	private JLabel uidLabel;
	private JLabel nameLabel;
	private JLabel emailLabel;
	private JTextField uid;
	private JTextField name;
	private JTextField email;
	private MainFrame mainFrame;
	
	public LoginRegisterPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.createUidPanel();
		this.createNamePanel();
		this.createEmailPanel();
	}
	
	private void createUidPanel(){
		JPanel uidPanel = new JPanel();
		uidLabel = new JLabel("uid : ");
		uid = new JTextField();
		uid.setColumns(10);
		uidPanel.add(uidLabel);
		uidPanel.add(uid);
		this.add(uidPanel);
	}
	
	private void createNamePanel(){
		JPanel namePanel = new JPanel();
		nameLabel = new JLabel("name : ");
		name = new JTextField();
		name.setColumns(10);
		namePanel.add(nameLabel);
		namePanel.add(name);
		this.add(namePanel);
	}
	
	private void createEmailPanel(){
		JPanel emailPanel = new JPanel();
		emailLabel = new JLabel("email : ");
		email = new JTextField();
		email.setColumns(10);
		emailPanel.add(emailLabel);
		emailPanel.add(email);
		this.add(emailPanel);
	}
	
	/**
	 * return true if user login, false if admin
	 * @return
	 */
	public boolean isUser(){
		return getName().equals("user");
	}
	
	public String getUid(){
		return uid.getText();
	}
	
	public String getName(){
		return name.getText();
	}
	
	public String getEmail(){
		return email.getText();
	}
}
