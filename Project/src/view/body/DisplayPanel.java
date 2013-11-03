package view.body;

import javax.swing.JPanel;

import view.ButtonSourceType;
import view.SectionType;

public interface DisplayPanel {
	public JPanel get();
	
	public void sendMessage(ButtonSourceType type);
	
	public void switchSection(SectionType sectionType);
	
	public void display(String result);
}
