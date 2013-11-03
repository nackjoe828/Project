package view.navigation;

import javax.swing.JPanel;

import view.ButtonSourceType;

public interface ButtonPanel {
	public void sendAction(ButtonSourceType type);
	
	//public void addButtonPanel();
	
	public JPanel get();
}
