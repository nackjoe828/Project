package view.body;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import view.MainFrame;
import view.ButtonSourceType;
import view.PageType;
import view.QueryGenerator;

public class BodyPanel extends JPanel implements QueryGenerator{
	
	private MainFrame mainFrame;
	private StartPanel sPanel;
	private AdminPanel aPanel;
	private UserPanel uPanel;
	private String[] username;
	private SpringLayout layout;
	
	public BodyPanel(MainFrame mainFrame){
		super();
		this.setBackground(new Color(112,138,144));
		this.mainFrame = mainFrame;
		layout = new SpringLayout();
		this.setLayout(layout);
		this.initializePage();
		this.putConstraints();
		this.addDisplayPanel(PageType.START);
	}
	
	private void putConstraints(){
		//for start page
		layout.putConstraint(layout.NORTH, sPanel, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, sPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, sPanel, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, sPanel, -5, layout.EAST, this);
		//for user page
		layout.putConstraint(layout.NORTH, aPanel, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, aPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, aPanel, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, aPanel, -5, layout.EAST, this);
		//for user page
		layout.putConstraint(layout.NORTH, uPanel, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, uPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, uPanel, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, uPanel, -5, layout.EAST, this);
	}
	
	private void initializePage(){
		sPanel = new StartPanel(this);
		sPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		aPanel = new AdminPanel(this);
		aPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		uPanel = new UserPanel(this);
		uPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
	public void addDisplayPanel(PageType pageType){
		switch (pageType){
		case START:
			this.add(sPanel);
			break;
		case ADMIN:
			this.add(aPanel);
			break;
		case USER:
			this.add(uPanel);
			break;
		}
		this.repaint();
	}
	
	public void showResult(String result, ButtonSourceType type){
		sPanel.display(result, type);
		aPanel.display(result, type);
		uPanel.display(result, type);
	}
	
	public void switchBody(PageType pageType){
		this.removeAll();
		this.putConstraints();
		this.addDisplayPanel(pageType);
		this.repaint();
	}
	
	public boolean isNewUser(){
		return sPanel.isNewUser();
	}
	
	public boolean isUser(){
		return sPanel.isUser();
	}
	
	public void sendMessage(ButtonSourceType type){
		mainFrame.sendAction(type);
	}
	
	public String getUpdate(){
		return sPanel.registerNewUser();
	}
	
	public String sendUser(){
		return sPanel.sendUser();
	}
	
	public void setUserName(String[] username){
		this.username = username;
	}
	
	public String getUserId(){
		return username[0];
	}
	
	@Override
	public void sendUpdate(String update){
		mainFrame.sendUpdate(update);
	}
	
	@Override
	public void sendQuery(String query){
		mainFrame.sendQuery(query);
	}
	
	public void switchSection(ButtonSourceType type){
		sPanel.switchSection(type);
		aPanel.switchSection(type);
		uPanel.switchSection(type);
	}
}
