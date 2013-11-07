package view.body;

import javax.swing.JPanel;

import view.ButtonSourceType;

public interface SectionPanel {
	public JPanel get();
	
	public void sendAction(ButtonSourceType type);
	
	public void display(String result, ButtonSourceType type);
}
