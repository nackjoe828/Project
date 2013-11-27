package view.body;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import util.TablePanel;
import view.ButtonSourceType;
import view.QueryGenerator;

public class AdminPanel extends JPanel implements WindowPanel, QueryGenerator, TableListener{
	private JPanel panel;
	private BodyPanel bPanel;
	private SpringLayout layout;
	private SpringLayout headerLayout;
	private JPanel headerPanel;
	private TablePanel tp;
	private ButtonSourceType currentType;
	private JButton mostFamousVideo;
	private JButton recentHotVideo;
	private JButton highAveVideo;
	private JButton inactiveUser;
	private JButton aveChannel;
	private JButton latestVideoInChannel;
	private String[] selectedTuple;
	private JButton selectedButton;
	
	
	public AdminPanel(BodyPanel bPanel){
		super();
		this.setBackground(new Color(245,245,245));
		layout = new SpringLayout();
		this.setLayout(layout);
		this.bPanel = bPanel;
		this.panel = new JPanel();
		this.headerPanel = new JPanel();
		headerLayout = new SpringLayout();
		headerPanel.setLayout(headerLayout);
		EtchedBorder inborder = new EtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = 
		  new TitledBorder(inborder, "Useful Buttons", TitledBorder.LEFT,
				  TitledBorder.TOP, new Font("Arial", Font.PLAIN, 10));
		headerPanel.setBorder(border);
		this.addHeader(null);
	}

	@Override
	public void display(String result, ButtonSourceType type) {
		this.removeAll();
		tp = new TablePanel(result, this);
		this.addHeader(null);
		
		layout.putConstraint(layout.NORTH, tp, 5, layout.SOUTH, headerPanel);
		layout.putConstraint(layout.WEST, tp, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, tp, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, tp, -5, layout.EAST, this);
		this.add(tp);
		this.repaint();
	}
	
	public void addHeader(ButtonSourceType type){
		layout.putConstraint(layout.NORTH, headerPanel, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, headerPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, headerPanel, 70, layout.NORTH, this);
		layout.putConstraint(layout.EAST, headerPanel, -5, layout.EAST, this);
		this.add(headerPanel);
		this.repaint();
	}
	
	public void addInactiveUser(){
		inactiveUser = new JButton("Inactive User");
		inactiveUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.INACTIVE_USER);
			}
		});
		headerLayout.putConstraint(headerLayout.NORTH, inactiveUser, 1, headerLayout.NORTH, headerPanel);
		headerLayout.putConstraint(headerLayout.WEST, inactiveUser, 1, headerLayout.WEST, headerPanel);
		headerLayout.putConstraint(headerLayout.SOUTH, inactiveUser, -1, headerLayout.SOUTH, headerPanel);
		headerLayout.putConstraint(headerLayout.EAST, inactiveUser, 200, headerLayout.WEST, inactiveUser);
		headerPanel.add(inactiveUser);
	}
	
	public void addMostFamousVideo(){
		mostFamousVideo = new JButton("Most Famous");
		mostFamousVideo.addActionListener(new ActionListener(){
			String input;
			JTextField textfield = new JTextField();
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.MOST_FAMOUS);
			}
		});
		headerLayout.putConstraint(headerLayout.NORTH, mostFamousVideo, 1, headerLayout.NORTH, headerPanel);
		headerLayout.putConstraint(headerLayout.WEST, mostFamousVideo, 1, headerLayout.WEST, headerPanel);
		headerLayout.putConstraint(headerLayout.SOUTH, mostFamousVideo, -1, headerLayout.SOUTH, headerPanel);
		headerLayout.putConstraint(headerLayout.EAST, mostFamousVideo, 200, headerLayout.WEST, mostFamousVideo);
		headerPanel.add(mostFamousVideo);
	}
	
	public void addRecentHotVideo(){
		recentHotVideo = new JButton("Recent Hot");
		recentHotVideo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.RECENT_HOT);
			}
		});
		headerLayout.putConstraint(headerLayout.NORTH, recentHotVideo, 1, headerLayout.NORTH, headerPanel);
		headerLayout.putConstraint(headerLayout.WEST, recentHotVideo, 1, headerLayout.EAST, mostFamousVideo);
		headerLayout.putConstraint(headerLayout.SOUTH, recentHotVideo, -1, headerLayout.SOUTH, headerPanel);
		headerLayout.putConstraint(headerLayout.EAST, recentHotVideo, 200, headerLayout.WEST, recentHotVideo);
		headerPanel.add(recentHotVideo);
	}
	
	public void addHighAveVideo(){
		highAveVideo = new JButton("Highest Average");
		highAveVideo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendAction(ButtonSourceType.HIGH_AVE);
			}
		});
		headerLayout.putConstraint(headerLayout.NORTH, highAveVideo, 1, headerLayout.NORTH, headerPanel);
		headerLayout.putConstraint(headerLayout.WEST, highAveVideo, 1, headerLayout.EAST, recentHotVideo);
		headerLayout.putConstraint(headerLayout.SOUTH, highAveVideo, -1, headerLayout.SOUTH, headerPanel);
		headerLayout.putConstraint(headerLayout.EAST, highAveVideo, 200, headerLayout.WEST, highAveVideo);
		headerPanel.add(highAveVideo);
	}
	
	public void addChannelAverage(){
		aveChannel = new JButton("Average Rating");
		aveChannel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				sendSelectedTuple(selectedTuple);
				sendAction(ButtonSourceType.CHANNEL_AVE);
				switchSection(ButtonSourceType.ADMIN_CHANNEL);
				selectedButton.setText("");
			}
		});
		headerLayout.putConstraint(headerLayout.NORTH, aveChannel, 1, headerLayout.NORTH, headerPanel);
		headerLayout.putConstraint(headerLayout.WEST, aveChannel, 1, headerLayout.WEST, headerPanel);
		headerLayout.putConstraint(headerLayout.SOUTH, aveChannel, -1, headerLayout.SOUTH, headerPanel);
		headerLayout.putConstraint(headerLayout.EAST, aveChannel, 200, headerLayout.WEST, aveChannel);
		headerPanel.add(aveChannel);
	}
	
	public void addChannelLatest(){
		latestVideoInChannel = new JButton("Latest In Channel");
		latestVideoInChannel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendSelectedTuple(selectedTuple);
				sendAction(ButtonSourceType.CHANNEL_LATEST);
				switchSection(ButtonSourceType.ADMIN_CHANNEL);
				selectedButton.setText("");
			}
		});
		headerLayout.putConstraint(headerLayout.NORTH, latestVideoInChannel, 1, headerLayout.NORTH, headerPanel);
		headerLayout.putConstraint(headerLayout.WEST, latestVideoInChannel, 1, headerLayout.EAST, aveChannel);
		headerLayout.putConstraint(headerLayout.SOUTH, latestVideoInChannel, -1, headerLayout.SOUTH, headerPanel);
		headerLayout.putConstraint(headerLayout.EAST, latestVideoInChannel, 200, headerLayout.WEST, latestVideoInChannel);
		headerPanel.add(latestVideoInChannel);
	}

	@Override
	public void switchSection(ButtonSourceType type) {
		this.currentType = type;
		headerPanel.removeAll();
		switch (type){
		case ADMIN_USER:
			this.addInactiveUser();
			break;
		case ADMIN_HISTORY:
			break;
		case ADMIN_VIDEO:
			this.addMostFamousVideo();
			this.addRecentHotVideo();
			this.addHighAveVideo();
			break;
		case ADMIN_CHANNEL:
			//this.addChannelAverage();
			//this.addChannelLatest();
			headerPanel.add(new JLabel("Select an item to see options "));
			break;
		default:
			break;
		}
		this.repaint();
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		
		bPanel.sendMessage(type);
	}

	@Override
	public void selectRow(String[] row, JButton button) {
		this.selectedTuple = row;
		this.selectedButton = button;
		switch (currentType){
		case ADMIN_USER:
		case ADMIN_VIDEO:
		case ADMIN_HISTORY:
		case ADMIN_FAVORITES:
			break;
		case ADMIN_CHANNEL:
			headerPanel.removeAll();
			this.addChannelAverage();
			this.addChannelLatest();
			this.revalidate();
			break;
		default:
			break;
		}
	}

	@Override
	public JPanel get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendUpdate(String update) {
		// TODO Auto-generated method stub
		bPanel.sendUpdate(update);
	}

	@Override
	public void sendQuery(String query) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendSelectedTuple(String[] row) {
		// TODO Auto-generated method stub
		bPanel.sendSelectedTuple(row);
	}
	

}
