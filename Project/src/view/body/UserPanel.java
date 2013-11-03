package view.body;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.ButtonSourceType;
import view.SectionType;
import view.body.user.UploadPanel;

public class UserPanel extends JPanel implements WindowPanel{
	private JTextArea textarea;
	private JComboBox jcBox;
	private JTextField input;
	private JButton search;
	private JPanel searchPanel;
	private JPanel hSection;
	private JPanel fSection;
	private SectionPanel uploadSection;
	private BodyPanel bPanel;
	
	public UserPanel(BodyPanel bPanel){
		super();
		this.bPanel = bPanel;
		this.textarea = new JTextArea("User Panel");
		this.add(textarea);
		this.addSearchField();
	}
	
	private void addSearchField(){
		searchPanel = new JPanel();
		String[] menu = {"Video", "Channel"};
		this.jcBox = new JComboBox(menu);
		this.input = new JTextField(10);
		this.hSection = new JPanel();
		this.hSection.add(new JLabel("History"));
		this.fSection = new JPanel();
		this.fSection.add(new JLabel("Favorites"));
		this.uploadSection = new UploadPanel(this);
		this.addSearchButton();
		searchPanel.add(jcBox);
		searchPanel.add(input);
		searchPanel.add(search);
		this.add(searchPanel);
	}
	
	private void addSearchButton(){
		search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				textarea.setText(input.getText());
				//if video is selected
				if(jcBox.getSelectedIndex() == 0) 
					sendQuery("select * from video where vid = ?:"
							+ input.getText());
				//if channel is selected
				else 
					sendQuery("select * from channel where cid = ?:"
							+ input.getText());
			}
		});
		this.add(search);
	}
	
	public void displayHistorySection(){
		this.removeAll();
		this.add(searchPanel);
		this.add(hSection);
	}
	
	public void displayFavoritesSection(){
		this.removeAll();
		this.add(searchPanel);
		this.add(fSection);
	}

	public void displayUploadSection(){
		this.removeAll();
		this.add(textarea);
		this.add(searchPanel);
		this.add(uploadSection.get());
	}
	
	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void display(String result) {
		textarea.setText(result);
		this.revalidate();
	}

	@Override
	public void switchSection(SectionType sectionType) {
		switch (sectionType){
		case USER_HISTORY:
			displayHistorySection();
			break;
		case USER_FAVORITES:
			displayFavoritesSection();
			break;
		case USER_UPLOAD:
			displayUploadSection();
			break;
		default:
			break;
		}
		this.revalidate();
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		bPanel.sendMessage(type);
	}

	@Override
	public void sendQuery(String query) {
		bPanel.sendQuery(query);
	}
	
	
}
