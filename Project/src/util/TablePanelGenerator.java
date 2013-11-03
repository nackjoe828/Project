package util;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TablePanelGenerator {
	private static JPanel container;
	private JPanel tablePanel;
	private JPanel buttonContainer;
	private JScrollPane scrollPane;
	private int column;
	private int row;
	private String[][] table;
	private ArrayList<JButton> buttons;
	private int x;
	private int source;
	
	public TablePanelGenerator(String result){
		buttons = new ArrayList<JButton>();
		String[] rows = result.split("\n");
		row = rows.length - 1;
		String[] labels = rows[0].split("\t");
		column = labels.length;
		x = 0;
		this.generateButtons(labels);
		this.generateTable(rows);
		this.generateButtonPanel();
		this.generateTablePanel();
		container.add(BorderLayout.NORTH, buttonContainer);
		container.add(BorderLayout.CENTER, scrollPane);
	}
	
	private void generateButtons(String[] labels){
		for(int i = 0; i < labels.length; i++){
			final int y = i;
			JButton button = new JButton(labels[i]);
			button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					x++;
					sort(y);
					update();
				}
				
			});
			buttons.add(button);
		}
	}
	
	private void generateTable(String[] rows){
		table = new String[row][];
		for(int i = 1; i <= row; i++){
			table[i - 1] = rows[i].split("\t");
		}
	}
	
	private void generateButtonPanel(){
		container = new JPanel();
		container.setLayout(new BorderLayout());
		buttonContainer = new JPanel();
		buttonContainer.setLayout(new GridLayout(1, column + 1));
		for(int i = 0; i < column; i++)
			buttonContainer.add(buttons.get(i));
		buttonContainer.add(new JLabel());
	}
	
	private void generateTablePanel(){
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(row, column + 1));
		for(int i = 0; i < row; i++){
			for(int j = 0; j < column; j++)
				tablePanel.add(new JLabel(table[i][j]));
			tablePanel.add(new JButton("Hello"));
	}
		scrollPane = new JScrollPane(tablePanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	private void update(){
		container.removeAll();
		this.generateTablePanel();
		container.add(BorderLayout.NORTH, buttonContainer);
		container.add(BorderLayout.CENTER, scrollPane);
		container.revalidate();
	}
	
	public JPanel getContainer(){
		return container;
	}
	/*
	public static JPanel getPanel(String result){
		TablePanelGenerator tpg = new TablePanelGenerator(result);
		return getContainer();
	}*/
	
	public void sort(final int i){
		java.util.Arrays.sort(table, new java.util.Comparator<String[]>() {
		    public int compare(String[] a, String[] b) {
		        return a[i].compareTo(b[i]);
		    }
		});
		//if desc
		if((x %= 2) == 1) reverseTable();
	}
	
	public void reverseTable(){
		String[][] tb = new String[row][];
		for(int i = 0; i < row; i++)
			tb[i] = table[row - i - 1];
		table = tb;
	}
	
	public void print(){
		for(int i = 0; i < row; i++)
			for(int j = 0; j < column; j++)
				System.out.print(table[i][j] + " - ");
	}
}
