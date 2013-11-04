package view.body;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.TablePanelGenerator;
import view.ButtonSourceType;

public class AdminPanel extends JPanel implements WindowPanel, TableListener{
	private JPanel panel;
	private BodyPanel bPanel;
	private JScrollPane scroll;
	
	public AdminPanel(BodyPanel bPanel){
		super();
		this.setLayout(new FlowLayout());
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
		TablePanelGenerator tpg = new TablePanelGenerator(result, this);
		panel = tpg.getPanel();
		scroll = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		this.add(BorderLayout.CENTER, scroll);
		this.revalidate();
	}

	@Override
	public void switchSection(ButtonSourceType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectRow(String row) {
		// TODO Auto-generated method stub
		
	}
}
