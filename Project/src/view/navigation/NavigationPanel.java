package view.navigation;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import view.MainFrame;
import view.ButtonSourceType;
import view.PageType;

public class NavigationPanel extends JPanel{
	private JLabel label;
	private MainFrame mainFrame;
	private HashMap<PageType, ButtonPanel> buttonContainer;
	private ButtonPanel currentPanel;
	private String[] username;
	
	public NavigationPanel(MainFrame mainFrame){
		super();
		this.setPreferredSize(new Dimension(mainFrame.WIDTH / 5, mainFrame.HEIGHT));
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.mainFrame = mainFrame;
		buttonContainer = new HashMap<PageType, ButtonPanel>();
		this.initializePage();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel("Welcome!");
		label.setFont(new Font("Serif", Font.BOLD, 24));
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
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
	
	public void setUserName(String[] username){
		this.username = username;
		label.setText("Hi, " + username[1] + "!");
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
		default:
			break;
		}
		this.addButtonPanel(type);
		this.repaint();
	}
}
