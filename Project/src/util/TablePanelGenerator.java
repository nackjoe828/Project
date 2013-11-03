package util;

import java.util.ArrayList;

import javax.swing.JButton;

public class TablePanelGenerator {
	private int column;
	private int row;
	private String[][] table;
	private ArrayList<JButton> buttons;
	
	public TablePanelGenerator(String result){
		buttons = new ArrayList<JButton>();
		String[] rows = result.split("\n");
		row = rows.length - 1;
		String[] labels = rows[0].split("\t");
		column = labels.length;
		//this.generateButtons(labels);
		this.generateTable(rows);
	}
	
	private void generateButtons(String[] labels){
		for(int i = 0; i < labels.length; i++){
			buttons.add(new JButton(labels[i]));
		}
	}
	
	private void generateTable(String[] rows){
		table = new String[row][];
		for(int i = 1; i <= row; i++){
			table[i - 1] = rows[i].split("\t");
		}
	}
	
	public void print(){
		for(int i = 0; i < row; i++)
			for(int j = 0; j < column; j++)
				System.out.print(table[i][j] + " - ");
	}
}
