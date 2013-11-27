package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import view.body.TableListener;

public class TablePanel extends JPanel{
	private JPanel tablePanel;
	private JPanel labelContainer;
	private JScrollPane scrollPane;
	private int column;
	private int row;
	private int buttonWidth;
	private int buttonHeight;
	private String[][] table;
	private String[] labels;
	private int x;
	private int source;
	private TableListener listener;
	private SpringLayout layout;
	
	public TablePanel(String result, TableListener listener){
		super();
		if(result.equals("clear")){
			return;
		}
		layout = new SpringLayout();
		this.setLayout(layout);
		this.listener = listener;
		buttonWidth = listener.getWidth() / 6;
		buttonHeight = 30;
		this.update(result);
		this.addPanels();
	}
	
	private void addPanels(){
		//for label panel
		layout.putConstraint(layout.NORTH, labelContainer, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, labelContainer, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, labelContainer, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, labelContainer, -5, layout.EAST, this);
		this.add(labelContainer);
		//for table panel
		layout.putConstraint(layout.NORTH, scrollPane, 5, layout.SOUTH, labelContainer);
		layout.putConstraint(layout.WEST, scrollPane, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, scrollPane, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, scrollPane, -5, layout.EAST, this);
		this.add(scrollPane);
	}
	
	public void update(String result){
		this.generateStringArray(result);
		this.generateTablePanel();
		this.generateLabelPanel();
	}
	
	private void generateStringArray(String result){
		String[] rows = result.split("\n");
		row = rows.length - 1;
		labels = rows[0].split("\t");
		this.generateTable(rows);
		column = labels.length;
	}
	
	private void generateTable(String[] rows){
		table = new String[row][];
		for(int i = 1; i <= row; i++){
			table[i - 1] = rows[i].split("\t");
		}
	}
	
	private void generateLabelPanel(){
		labelContainer = new JPanel();
		labelContainer.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		for(int i = 0; i < column; i++){
			JPanel p = new JPanel();
			JLabel l = new JLabel(labels[i]);
			p.setLayout(new FlowLayout(FlowLayout.CENTER));
			p.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			p.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			p.add(l);
			labelContainer.add(p);
		}
		labelContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	}
	
	private void generateTablePanel(){
		tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		for(int i = 0; i < row; i++){
			JPanel rowPanel = new JPanel();
			rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			for(int j = 0; j < column; j++){
				JPanel p = new JPanel();
				p.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				p.setLayout(new FlowLayout(FlowLayout.RIGHT));
				p.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
				p.add(new JLabel(table[i][j]));
				rowPanel.add(p);
			}
			final int x = i;
			final JButton select = new JButton("");
			select.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					select.setText("X");
					listener.selectRow(table[x], select);
				}
				
			});
			select.setPreferredSize(new Dimension(buttonHeight, buttonHeight));
			rowPanel.add(select);
			tablePanel.add(rowPanel);
		}
		this.addScrollPane();
	}
	
	private void addScrollPane(){
		scrollPane = new JScrollPane(tablePanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
}
