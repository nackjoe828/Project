package view.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.ButtonSourceType;

public class AdminButtonPanel extends JPanel implements ButtonPanel{
	private JButton user;
	private JButton history;
	private JButton video;
	private JButton favorites;
	private JButton channel;
	private JButton logout;
	private NavigationPanel nPanel;
	
	public AdminButtonPanel(NavigationPanel nPanel){
		super();
		this.nPanel = nPanel;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addUserButton();
		this.addHistoryButton();
		this.addVideo();
		this.addFavorites();
		this.addChannel();
		this.addLogout();
	}
	
	private void addUserButton(){
		user = new JButton("User");
		user.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.ADMIN_USER);
			}
		});
		this.add(user);
	}
	
	private void addHistoryButton(){
		history = new JButton("History");
		history.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.ADMIN_HISTORY);
			}
		});
		this.add(history);
	}
	
	private void addVideo(){
		video = new JButton("Video");
		video.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.ADMIN_VIDEO);
			}
		});
		this.add(video);
	}
	
	private void addFavorites(){
		favorites = new JButton("Favorites");
		favorites.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.ADMIN_FAVORITES);
			}
		});
		this.add(favorites);
	}
	
	private void addChannel(){
		channel = new JButton("Channel");
		channel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.ADMIN_CHANNEL);
			}
		});
		this.add(channel);
	}
	
	private void addLogout(){
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.LOGOUT);
			}
		});
		this.add(logout);
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		nPanel.sendMessage(type);
	}

	@Override
	public JPanel get() {
		return this;
	}
}