package view.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import control.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MainFrame;
import view.ButtonSourceType;
import view.PageType;
import database.Model;

public class NavigationPanel extends JPanel{
	private JLabel label;
	private MainFrame mainFrame;
	private HashMap<PageType, ButtonPanel> buttonContainer;
	private ButtonPanel currentPanel;
	
	public NavigationPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		buttonContainer = new HashMap<PageType, ButtonPanel>();
		this.initializePage();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel("Welcome!");
		
		this.add(label);
		addButtonPanel(PageType.START);
	}
	
	private void initializePage() {
		buttonContainer.put(PageType.START, new StartButtonPanel(this));
		buttonContainer.put(PageType.ADMIN, new AdminButtonPanel(this));
		buttonContainer.put(PageType.USER, new UserButtonPanel(this));
	}

	public void sendMessage(ButtonSourceType buttonType){
		mainFrame.sendMessage(buttonType);
	}
	
	public void addButtonPanel(PageType pageType){
		currentPanel = buttonContainer.get(pageType);
		this.add(currentPanel.get());
	}
	
	public void setUserName(String username){
		label.setText(username);
		this.revalidate();
	}
	
	public void switchNavigation(PageType type){
		this.removeAll();
		this.add(label);
		switch (type){
		case START:
			label.setText("Welcome");
			break;
		case ADMIN:
			label.setText("Admin");
			break;
		case USER:
			label.setText(mainFrame.getUserName());
			break;
		default:
			break;
		}
		this.addButtonPanel(type);
		this.revalidate();
	}
}
