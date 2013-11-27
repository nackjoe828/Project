package view.navigation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import view.ButtonSourceType;

public class UserButtonPanel extends JPanel implements ButtonPanel{
	private JButton upload;
	private JButton history;
	private JButton favorites;
	private JButton recommend;
	private JButton logout;
	private NavigationPanel nPanel;
	private SpringLayout layout;
	
	public UserButtonPanel(NavigationPanel nPanel){
		super();
		this.setBackground(new Color(245,245,245));
		this.nPanel = nPanel;
		layout = new SpringLayout();
		this.setLayout(layout);
		this.addUploadButton();
		this.addHistoryButton();
		this.addFavoritesButton();
		this.addRecommend();
		this.addLogout();
	}
	
	private void addUploadButton(){
		upload = new JButton("Upload");
		upload.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.USER_UPLOAD);
			}
		});
		layout.putConstraint(layout.NORTH, upload, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, upload, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, upload, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, upload, -5, layout.EAST, this);
		this.add(upload);
	}
	
	private void addHistoryButton(){
		history = new JButton("History");
		history.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.USER_HISTORY);
			}
		});
		layout.putConstraint(layout.NORTH, history, 5, layout.SOUTH, upload);
		layout.putConstraint(layout.WEST, history, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, history, 40, layout.SOUTH, upload);
		layout.putConstraint(layout.EAST, history, -5, layout.EAST, this);
		this.add(history);
	}
	
	private void addFavoritesButton(){
		favorites = new JButton("Favorites");
		favorites.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.USER_FAVORITES);
			}
		});
		layout.putConstraint(layout.NORTH, favorites, 5, layout.SOUTH, history);
		layout.putConstraint(layout.WEST, favorites, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, favorites, 40, layout.SOUTH, history);
		layout.putConstraint(layout.EAST, favorites, -5, layout.EAST, this);
		this.add(favorites);
	}
	
	private void addRecommend(){
		recommend = new JButton("Recommended for you!");
		recommend.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.USER_RECOMMEND);
			}
		});
		layout.putConstraint(layout.NORTH, recommend, 5, layout.SOUTH, favorites);
		layout.putConstraint(layout.WEST, recommend, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, recommend, 40, layout.SOUTH, favorites);
		layout.putConstraint(layout.EAST, recommend, -5, layout.EAST, this);
		this.add(recommend);
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
