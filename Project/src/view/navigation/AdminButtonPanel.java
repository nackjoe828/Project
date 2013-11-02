package view.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.MainFrame;
import view.ButtonSourceType;
import control.Controller;
import database.Model;
import static view.ButtonSourceType.*;

public class AdminButtonPanel extends JPanel{
	private JButton user;
	private JButton history;
	private JButton video;
	private JButton favorites;
	private JButton channel;
	private MainFrame mainFrame;
	
	public AdminButtonPanel(MainFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.addUserButton();
		this.addHistoryButton();
		this.addVideo();
		this.addFavorites();
		this.addChannel();
	}
	
	private void addUserButton(){
		user = new JButton("User");
		user.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.sendMessage(ButtonSourceType.USER);
			}
		});
		this.add(user);
	}
	
	private void addHistoryButton(){
		history = new JButton("History");
		history.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.sendMessage(ButtonSourceType.HISTORY);
			}
		});
		this.add(history);
	}
	
	private void addVideo(){
		video = new JButton("Video");
		video.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.sendMessage(ButtonSourceType.VIDEO);
			}
		});
		this.add(video);
	}
	
	private void addFavorites(){
		favorites = new JButton("Favorites");
		favorites.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.sendMessage(ButtonSourceType.FAVORITES);
			}
		});
		this.add(favorites);
	}
	
	private void addChannel(){
		channel = new JButton("Channel");
		channel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.sendMessage(ButtonSourceType.CHANNEL);
			}
		});
		this.add(channel);
	}
}
