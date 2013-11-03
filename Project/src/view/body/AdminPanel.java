package view.body;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import view.ButtonSourceType;
import view.SectionType;

public class AdminPanel extends JPanel implements DisplayPanel{
	private JTextArea textarea;
	private BodyPanel bPanel;
	
	public AdminPanel(BodyPanel bPanel){
		super();
		this.bPanel = bPanel;
		this.textarea = new JTextArea("Admin Page");
		this.add(textarea);
	}
	
	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void display(String result) {
		textarea.setText(result);
		this.revalidate();
	}

	@Override
	public void switchSection(SectionType sectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(ButtonSourceType type) {
		// TODO Auto-generated method stub
		
	}

}
