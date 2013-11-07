package view.body;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import util.TablePanel;
import view.ButtonSourceType;

public class AdminPanel extends JPanel implements WindowPanel, TableListener{
	private JPanel panel;
	private BodyPanel bPanel;
	private JScrollPane scroll;
	private SpringLayout layout;
	private JPanel buttonContainer;
	private JPanel tablePanel;
	private JScrollPane scrollPane;
	private TablePanel tp;
	
	public AdminPanel(BodyPanel bPanel){
		super();
		this.setBackground(new Color(245,245,245));
		layout = new SpringLayout();
		this.setLayout(layout);
		this.bPanel = bPanel;
		this.panel = new JPanel();
	}

	@Override
	public void display(String result, ButtonSourceType type) {
		this.removeAll();
		tp = new TablePanel(result, this);
		layout.putConstraint(layout.NORTH, tp, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, tp, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, tp, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, tp, -5, layout.EAST, this);
		this.add(tp);
		this.repaint();
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

	@Override
	public JPanel get() {
		// TODO Auto-generated method stub
		return null;
	}
}
