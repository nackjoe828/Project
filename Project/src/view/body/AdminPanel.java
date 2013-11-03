package view.body;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.TablePanelGenerator;
import view.ButtonSourceType;
import view.SectionType;

public class AdminPanel extends JPanel implements WindowPanel{
	private JPanel panel;
	private BodyPanel bPanel;
	
	public AdminPanel(BodyPanel bPanel){
		super();
		this.bPanel = bPanel;
		this.panel = new JPanel();
		this.add(panel);
	}
	
	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void display(String result) {
		this.removeAll();
		TablePanelGenerator tpg = new TablePanelGenerator(result);
		panel = tpg.getContainer();
		this.add(panel);
		this.revalidate();
	}

	@Override
	public void switchSection(SectionType sectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendQuery(String query) {
		// TODO Auto-generated method stub
		
	}

}
