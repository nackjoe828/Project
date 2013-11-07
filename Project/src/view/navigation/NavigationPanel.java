package view.navigation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import view.MainFrame;
import view.ButtonSourceType;
import view.PageType;

public class NavigationPanel extends JPanel{
	private JLabel label;
	private JPanel labelPanel;
	private MainFrame mainFrame;
	private StartButtonPanel sbPanel;
	private AdminButtonPanel abPanel;
	private UserButtonPanel ubPanel;
	private String[] username;
	private SpringLayout layout;
	
	public NavigationPanel(MainFrame mainFrame){
		super();
		this.setBackground(new Color(112,138,144));
		this.mainFrame = mainFrame;
		layout = new SpringLayout();
		this.setLayout(layout);
		this.initializePage();
		label = new JLabel("Welcome!");
		label.setFont(new Font("Serif", Font.BOLD, 24));
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		labelPanel = new JPanel();
		labelPanel.setBackground(new Color(245,245,245));
		labelPanel.setLayout(new BorderLayout());
		labelPanel.add(BorderLayout.CENTER, label);
		this.putConstraints(labelPanel);
		this.add(labelPanel);
		addButtonPanel(PageType.START);
	}
	
	private void putConstraints(JPanel p){
		//for Label
		layout.putConstraint(layout.NORTH, p, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, p, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, p, 100, layout.NORTH, this);
		layout.putConstraint(layout.EAST, p, -5, layout.EAST, this);
		//for start buttons
		layout.putConstraint(layout.NORTH, sbPanel, 5, layout.SOUTH, p);
		layout.putConstraint(layout.WEST, sbPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, sbPanel, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, sbPanel, -5, layout.EAST, this);
		//for admin buttons
		layout.putConstraint(layout.NORTH, abPanel, 5, layout.SOUTH, p);
		layout.putConstraint(layout.WEST, abPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, abPanel, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, abPanel, -5, layout.EAST, this);
		//for user buttons
		layout.putConstraint(layout.NORTH, ubPanel, 5, layout.SOUTH, p);
		layout.putConstraint(layout.WEST, ubPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, ubPanel, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, ubPanel, -5, layout.EAST, this);
	}
	
	private void initializePage() {
		sbPanel = new StartButtonPanel(this);
		sbPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		abPanel = new AdminButtonPanel(this);
		abPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		ubPanel = new UserButtonPanel(this);
		ubPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}

	public void sendMessage(ButtonSourceType buttonType){
		mainFrame.sendMessage(buttonType);
	}
	
	public void addButtonPanel(PageType pageType){
		switch (pageType){
		case START:
			this.add(sbPanel);
			break;
		case ADMIN:
			this.add(abPanel);
			break;
		case USER:
			this.add(ubPanel);
			break;
		}
		this.repaint();
	}
	
	public void setUserName(String[] username){
		this.username = username;
		label.setText("Hi, " + username[1] + "!");
		this.revalidate();
	}
	
	public void switchNavigation(PageType type){
		this.removeAll();
		this.putConstraints(labelPanel);
		this.add(labelPanel);
		this.repaint();
		switch (type){
		case START:
			label.setText("Welcome");
			break;
		case ADMIN:
			label.setText("Admin");
			break;
		default:
			break;
		}
		this.addButtonPanel(type);
		this.repaint();
	}
}
