package view.navigation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import view.ButtonSourceType;

public class AdminButtonPanel extends JPanel implements ButtonPanel{
	private JButton user;
	private JButton history;
	private JButton video;
	private JButton favorites;
	private JButton channel;
	private JButton archive;
	private JButton logout;
	private NavigationPanel nPanel;
	private SpringLayout layout;
	
	public AdminButtonPanel(NavigationPanel nPanel){
		super();
		this.setBackground(new Color(245,245,245));
		this.nPanel = nPanel;
		layout = new SpringLayout();
		this.setLayout(layout);
		this.addUserButton();
		this.addHistoryButton();
		this.addVideo();
		this.addFavorites();
		this.addChannel();
		this.addLogout();
		this.addArchive();
	}
	
	private void addUserButton(){
		user = new JButton("User");
		user.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.ADMIN_USER);
			}
		});
		layout.putConstraint(layout.NORTH, user, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, user, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, user, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, user, -5, layout.EAST, this);
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
		layout.putConstraint(layout.NORTH, history, 5, layout.SOUTH, user);
		layout.putConstraint(layout.WEST, history, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, history, 40, layout.SOUTH, user);
		layout.putConstraint(layout.EAST, history, -5, layout.EAST, this);
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
		layout.putConstraint(layout.NORTH, video, 5, layout.SOUTH, history);
		layout.putConstraint(layout.WEST, video, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, video, 40, layout.SOUTH, history);
		layout.putConstraint(layout.EAST, video, -5, layout.EAST, this);
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
		layout.putConstraint(layout.NORTH, favorites, 5, layout.SOUTH, video);
		layout.putConstraint(layout.WEST, favorites, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, favorites, 40, layout.SOUTH, video);
		layout.putConstraint(layout.EAST, favorites, -5, layout.EAST, this);
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
		layout.putConstraint(layout.NORTH, channel, 5, layout.SOUTH, favorites);
		layout.putConstraint(layout.WEST, channel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, channel, 40, layout.SOUTH, favorites);
		layout.putConstraint(layout.EAST, channel, -5, layout.EAST, this);
		this.add(channel);
	}
	
	private void addArchive(){
		archive = new JButton("Archive");
		archive.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.ARCHIVE);
			}
		});
		layout.putConstraint(layout.NORTH, archive, -40, layout.NORTH, logout);
		layout.putConstraint(layout.WEST, archive, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, archive, -5, layout.NORTH, logout);
		layout.putConstraint(layout.EAST, archive, -5, layout.EAST, this);
		this.add(archive);
	}
	
	private void addLogout(){
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.LOGOUT);
			}
		});
		layout.putConstraint(layout.NORTH, logout, -40, layout.SOUTH, this);
		layout.putConstraint(layout.WEST, logout, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, logout, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, logout, -5, layout.EAST, this);
		this.add(logout);
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		nPanel.sendAction(type);
	}

	@Override
	public JPanel get() {
		return this;
	}
}
