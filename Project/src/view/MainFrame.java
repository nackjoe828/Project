package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import view.body.BodyPanel;
import view.navigation.NavigationPanel;
import control.Controller;

public class MainFrame extends JFrame implements QueryGenerator{
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;
	
	private Controller controller;
	private NavigationPanel nPanel;
	private BodyPanel bPanel;
	private String[] username;
	private SpringLayout layout;
	private JPanel errorPanel;
	
	public MainFrame(Controller controller) throws Exception{
		super();
		this.setSize(WIDTH, HEIGHT);
		this.getContentPane().setBackground(new Color(112,138,144));
		this.setTitle("Video");
		Container container = this.getContentPane();
		layout = new SpringLayout();
		container.setLayout(layout);
		this.controller = controller;
		controller.setFrame(this);
		
		errorPanel = new JPanel();
		errorPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		layout.putConstraint(layout.NORTH, errorPanel, 50, layout.NORTH, this);
		layout.putConstraint(layout.WEST, errorPanel, 50, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, errorPanel, 100, layout.NORTH, errorPanel);
		layout.putConstraint(layout.EAST, errorPanel, 250, layout.WEST, errorPanel);
		
		nPanel = new NavigationPanel(this);
		//nPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		bPanel = new BodyPanel(this);
		//bPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		layout.putConstraint(layout.NORTH, nPanel, 5, layout.NORTH, container);
		layout.putConstraint(layout.WEST, nPanel, 5, layout.WEST, container);
		layout.putConstraint(layout.SOUTH, nPanel, -5, layout.SOUTH, container);
		layout.putConstraint(layout.EAST, nPanel, 250, layout.WEST, container);
		this.add(nPanel);
		layout.putConstraint(layout.NORTH, bPanel, 5, layout.NORTH, container);
		layout.putConstraint(layout.WEST, bPanel, 5, layout.EAST, nPanel);
		layout.putConstraint(layout.SOUTH, bPanel, -5, layout.SOUTH, container);
		layout.putConstraint(layout.EAST, bPanel, -5, layout.EAST, container);
		this.add(bPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public void showErrorMessage(){
		JOptionPane.showMessageDialog(this,
			    "vid you entered is already taken.",
			    "Inane error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void sendUpdate(String update){
		controller.update(update);
	}
	
	@Override
	public void sendQuery(String query){
		controller.select(query);
	}
	
	public void sendSelectedTuple(String[] row){
		controller.setSelectedTuple(row);
	}
	
	public void sendButtonType(ButtonSourceType type){
		controller.show(type);
	}
	
	public void setUserName(String[] user){
		//controller.getUserName contains labels which have to be omitted
		nPanel.setUserName(user);
		bPanel.setUserName(user);
	}
	
	public String[] getUserName(){
		return username;
	}
	
	public void sendAction(ButtonSourceType type){
		controller.execute(type);
	}
	
	public boolean isUser(){
		return bPanel.isUser();
	}
	
	public boolean isNewUser(){
		return bPanel.isNewUser();
	}
	
	public String getUpdate(){
		return bPanel.getUpdate();
	}
	
	public String selectUser(){
		return bPanel.sendUser();
	}
	
	/**
	 * switch pages between start, user and admin
	 * @param pageType type of the page to be desplayed
	 */
	public void switchPage(PageType pageType){
		nPanel.switchNavigation(pageType);
		bPanel.switchBody(pageType);
		this.revalidate();
	}
	
	public void showTable(String result, ButtonSourceType type){
		bPanel.showResult(result, type);
		this.revalidate();
	}
	
	public void switchSection(ButtonSourceType type){
		bPanel.switchSection(type);
		this.revalidate();
	}
	
	public String getResult(){
		return controller.getResult();
	}
}
