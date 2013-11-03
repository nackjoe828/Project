package view.body;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.ButtonSourceType;
import view.MainFrame;
import view.QueryGenerator;
import view.SectionType;

public class StartPanel extends JPanel implements WindowPanel, QueryGenerator{
	private JLabel uidLabel;
	private JLabel nameLabel;
	private JLabel emailLabel;
	private JTextField uid;
	private JTextField name;
	private JTextField email;
	private BodyPanel bPanel;
	
	public StartPanel(BodyPanel bPanel){
		super();
		this.bPanel = bPanel;
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
		return !getUid().equals("admin");
	}
	
	public void setUserName(){
		bPanel.setUserName(generateQuery());
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

	@Override
	public String generateQuery() {
		String query = "select * from user where ";
		if(!getUid().isEmpty()) query += "uid = ?:" + getUid();
		else if(!getUName().isEmpty()) query += "name = ?:" + getUName();
		else query += "email = ?:" + getEmail();
		return query;
	}

	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void display(String result) {}

	@Override
	public void switchSection(SectionType sectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendQuery(String query) {
		bPanel.sendQuery(query);
	}
}
