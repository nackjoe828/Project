package view.body;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import view.ButtonSourceType;

public class StartPanel extends JPanel implements WindowPanel{
	private JLabel uidLabel;
	private JLabel nameLabel;
	private JLabel emailLabel;
	private JTextField uid;
	private JTextField name;
	private JTextField email;
	private JPanel uidPanel;
	private JPanel namePanel;
	private JPanel emailPanel;
	private BodyPanel bPanel;
	private SpringLayout layout;
	
	public StartPanel(BodyPanel bPanel){
		super();
		this.setBackground(new Color(245,245,245));
		this.bPanel = bPanel;
		layout = new SpringLayout();
		this.setLayout(layout);
		this.createUidPanel();
		this.createNamePanel();
		this.createEmailPanel();
		this.putConstraints();
		this.add(uidPanel);
		this.add(namePanel);
		this.add(emailPanel);
	}
	
	private void putConstraints(){
		//for uidPanel
		layout.putConstraint(layout.NORTH, uidPanel, 100, layout.NORTH, this);
		layout.putConstraint(layout.WEST, uidPanel, 100, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, uidPanel, 50, layout.NORTH, uidPanel);
		layout.putConstraint(layout.EAST, uidPanel, 200, layout.WEST, uidPanel);
		//for namePanel
		layout.putConstraint(layout.NORTH, namePanel, 5, layout.SOUTH, uidPanel);
		layout.putConstraint(layout.WEST, namePanel, 100, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, namePanel, 50, layout.NORTH, namePanel);
		layout.putConstraint(layout.EAST, namePanel, 200, layout.WEST, namePanel);
		//for emailPanel
		layout.putConstraint(layout.NORTH, emailPanel, 5, layout.SOUTH, namePanel);
		layout.putConstraint(layout.WEST, emailPanel, 100, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, emailPanel, 50, layout.NORTH, emailPanel);
		layout.putConstraint(layout.EAST, emailPanel, 200, layout.WEST, emailPanel);
	}
	
	private void createUidPanel(){
		uidPanel = new JPanel();
		uidPanel.setOpaque(false);
		uidPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		uidLabel = new JLabel("uid : ");
		uid = new JTextField();
		uid.setColumns(10);
		uidPanel.add(uidLabel);
		uidPanel.add(uid);
	}
	
	private void createNamePanel(){
		namePanel = new JPanel();
		namePanel.setOpaque(false);
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		nameLabel = new JLabel("name : ");
		name = new JTextField();
		name.setColumns(10);
		namePanel.add(nameLabel);
		namePanel.add(name);
	}
	
	private void createEmailPanel(){
		emailPanel = new JPanel();
		emailPanel.setOpaque(false);
		emailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		emailLabel = new JLabel("email : ");
		email = new JTextField();
		email.setColumns(10);
		emailPanel.add(emailLabel);
		emailPanel.add(email);
	}
	
	/**
	 * return true if user login, false if admin
	 * @return
	 */
	public boolean isUser(){
		if(!getUid().equals("admin")) return true;
		else{
			uid.setText("");
			return false;
		}
	}
	
	public boolean isNewUser(){
		return !getUid().isEmpty() && !getUName().isEmpty() && !getEmail().isEmpty();
	}
	
	private String getUid(){
		return uid.getText();
	}
	
	private String getUName(){
		return name.getText();
	}
	
	private String getEmail(){
		return email.getText();
	}
	
	public String registerNewUser(){
		return "insert into user values(?,?,?):"
				+ getUid() + ":" + getUName() + ":" + getEmail();
	}

	public String sendUser() {
		String query = "select * from user where ";
		if(!getUid().isEmpty()) query += "uid = ?:" + getUid();
		else if(!getUName().isEmpty()) query += "name = ?:" + getUName();
		else query += "email = ?:" + getEmail();
		reset();
		return query;
	}
	
	public void reset(){
		uid.setText("");
		name.setText("");
		email.setText("");
	}

	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void display(String result, ButtonSourceType type) {}

	@Override
	public void switchSection(ButtonSourceType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		// TODO Auto-generated method stub
		
	}
}
