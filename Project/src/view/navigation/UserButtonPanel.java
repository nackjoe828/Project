package view.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.ButtonSourceType;
import view.MainFrame;

public class UserButtonPanel extends JPanel implements ButtonPanel{
	private JButton upload;
	private JButton history;
	private JButton favorites;
	private JButton logout;
	private NavigationPanel nPanel;
	
	public UserButtonPanel(NavigationPanel nPanel){
		super();
		this.nPanel = nPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addUploadButton();
		this.addHistoryButton();
		this.addFavoritesButton();
		this.addLogout();
	}
	
	private void addUploadButton(){
		upload = new JButton("Upload");
		upload.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(ButtonSourceType.USER_UPLOAD);
			}
		});
		this.add(upload);
	}
	
	private void addHistoryButton(){
		history = new JButton("History");
		history.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(ButtonSourceType.USER_HISTORY);
			}
		});
		this.add(history);
	}
	
	private void addFavoritesButton(){
		favorites = new JButton("Favorites");
		favorites.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(ButtonSourceType.USER_FAVORITES);
			}
		});
		this.add(favorites);
	}
	
	private void addLogout(){
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(ButtonSourceType.LOGOUT);
			}
		});
		this.add(logout);
	}

	@Override
	public void sendMessage(ButtonSourceType type) {
		nPanel.sendMessage(type);
	}

	@Override
	public JPanel get() {
		return this;
	}
}
