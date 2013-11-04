package view.body;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.TablePanelGenerator;
import view.ButtonSourceType;
import view.QueryGenerator;
import view.body.user.UploadPanel;

public class UserPanel extends JPanel implements WindowPanel, QueryGenerator, TableListener{
	//private JTextArea textarea;
	private JComboBox jcBox;
	private JTextField input;
	private JButton search;
	private JPanel searchPanel;
	private JPanel resultPanel;
	private JPanel hSection;
	private JPanel fSection;
	private SectionPanel uploadSection;
	private BodyPanel bPanel;
	
	public UserPanel(BodyPanel bPanel){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.bPanel = bPanel;
		//this.textarea = new JTextArea("User Panel");
		//this.add(textarea);
		this.addSearchField();
	}
	
	private void addSearchField(){
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		
		String[] menu = {"Video", "Channel"};
		this.jcBox = new JComboBox(menu);
		this.input = new JTextField(10);
		this.resultPanel = new JPanel();
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
		this.add(resultPanel);
	}
	
	private void addSearchButton(){
		search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//textarea.setText(input.getText());
				//if video is selected
				if(jcBox.getSelectedIndex() == 0) 
					sendQuery("select * from video where vid = ?:"
							+ input.getText());
				//if channel is selected
				else 
					sendQuery("select * from channel where cid = ?:"
							+ input.getText());
				sendAction(ButtonSourceType.SEARCH);
			}
		});
		this.add(search);
	}
	
	public String getUserId(){
		return bPanel.getUserId();
	}
	
	public void displayHistorySection(){
		this.removeAll();
		this.add(searchPanel);
		this.repaint();
	}
	
	public void displayFavoritesSection(){
		this.removeAll();
		this.add(searchPanel);
		this.add(fSection);
		this.repaint();
	}

	public void displayUploadSection(){
		this.removeAll();
		//this.add(textarea);
		this.add(searchPanel);
		this.add(uploadSection.get());
		this.repaint();
	}
	
	public void updateUpload(){
		((UploadPanel)uploadSection).confirmMessage();
	}
	
	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void display(String result) {
		this.removeAll();
		TablePanelGenerator tpg = new TablePanelGenerator(result, this);
		this.add(searchPanel);
		this.add(uploadSection.get());
		this.add(tpg.getPanel());
		this.revalidate();
	}

	@Override
	public void switchSection(ButtonSourceType type) {
		switch (type){
		case USER_HISTORY:
			displayHistorySection();
			break;
		case USER_FAVORITES:
			displayFavoritesSection();
			break;
		case USER_UPLOAD:
			displayUploadSection();
			break;
		case UPLOAD_VIDEO:
			updateUpload();
			break;
		default:
			break;
		}
		input.setText("");
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

	@Override
	public void sendUpdate(String update) {
		bPanel.sendUpdate(update);
	}

	@Override
	public void selectRow(String row) {
		sendUpdate("insert into history (vid, uid,date) values ("
				+ row + "," + bPanel.getUserId() + "," + "now())");
		sendAction(ButtonSourceType.SELECT);
	}
}
