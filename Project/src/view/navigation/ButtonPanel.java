package view.navigation;

import javax.swing.JPanel;

import view.ButtonSourceType;

public interface ButtonPanel {
	public void sendMessage(ButtonSourceType type);
	
	//public void addButtonPanel();
	
	public JPanel get();
}
