package view.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.ButtonSourceType;
import view.MainFrame;

public class UserButtonPanel extends JPanel{
	private JButton search;
	private MainFrame mainFrame;
	
	public UserButtonPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addSearchButton();
	}
	
	private void addSearchButton(){
		search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.sendMessage(ButtonSourceType.SEARCH);
			}
		});
		this.add(search);
	}
}
