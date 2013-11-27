package view.body;

import javax.swing.JButton;

public interface TableListener {
	/**
	 * do tasks whenever the button is pressed
	 * @param row key value of the row
	 */
	public void selectRow(String[] row, JButton button);
	
	public void sendSelectedTuple(String[] row);
	public int getWidth();
	public int getHeight();
}
