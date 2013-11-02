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
	private NavigationPanel nPanel;
	
	public UserButtonPanel(NavigationPanel nPanel){
		super();
		this.nPanel = nPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addSearchButton();
	}
	
	private void addSearchButton(){
		search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				nPanel.sendMessage(ButtonSourceType.SEARCH);
			}
		});
		this.add(search);
	}
}
