package view.body;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import util.TablePanel;
import view.ButtonSourceType;
import view.QueryGenerator;
import view.body.user.UploadPanel;

public class UserPanel extends JPanel implements WindowPanel, QueryGenerator, TableListener{
	//private JTextArea textarea;
	private JComboBox jcBox;
	private JComboBox rateVal;
	private JTextField input;
	private JButton search;
	private JButton watch;
	private JButton rate;
	private JButton delete;
	private JPanel searchPanel;
	private JPanel resultPanel;
	private JPanel hSection;
	private JPanel fSection;
	private UploadPanel uploadSection;
	private BodyPanel bPanel;
	private SpringLayout layout;
	private SpringLayout searchlayout;
	private SpringLayout uploadlayout;
	private SpringLayout resultLayout;
	private ButtonSourceType currentType;
	private String[] selectedTuple;
	private JButton selectedButton;
	
	public UserPanel(BodyPanel bPanel){
		super();
		this.setBackground(new Color(245,245,245));
		layout = new SpringLayout();
		this.setLayout(layout);
		this.bPanel = bPanel;
		this.uploadSection = new UploadPanel(this);
		uploadSection.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		uploadlayout = new SpringLayout();
		//uploadSection.setLayout(uploadlayout);
		this.createSearchField();
	}
	
	private void createSearchField(){
		searchPanel = new JPanel();
		searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		searchlayout = new SpringLayout();
		searchPanel.setLayout(searchlayout);
		
		String[] menu = {"vid", "cid"};
		this.jcBox = new JComboBox(menu);
		this.input = new JTextField(10);
		this.resultPanel = new JPanel();
		resultPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.createSearchButton();
		//for search panel
		this.addSearchPanel();
		//for result panel
		this.addResultPanel();
		this.addSearchField(searchlayout);
	}
	
	private void addUploadPanel(){
		//for upload panel
		layout.putConstraint(layout.NORTH, uploadSection, 5, layout.SOUTH, searchPanel);
		layout.putConstraint(layout.WEST, uploadSection, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, uploadSection, 50, layout.SOUTH, searchPanel);
		layout.putConstraint(layout.EAST, uploadSection, -5, layout.EAST, this);
		this.add(uploadSection);
	}
	
	private void addSearchPanel(){
		//for search panel
		layout.putConstraint(layout.NORTH, searchPanel, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, searchPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, searchPanel, 50, layout.NORTH, this);
		layout.putConstraint(layout.EAST, searchPanel, -5, layout.EAST, this);
		this.add(searchPanel);
	}
	
	private void addResultPanel(){
		layout.putConstraint(layout.NORTH, resultPanel, 55, layout.SOUTH, searchPanel);
		layout.putConstraint(layout.WEST, resultPanel, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, resultPanel, -5, layout.SOUTH, this);
		layout.putConstraint(layout.EAST, resultPanel, -5, layout.EAST, this);
		this.add(resultPanel);
	}
	
	private void addSearchField(SpringLayout layout){
		//for jcbox
		layout.putConstraint(layout.NORTH, jcBox, 5, layout.NORTH, searchPanel);
		layout.putConstraint(layout.WEST, jcBox, 5, layout.WEST, searchPanel);
		layout.putConstraint(layout.SOUTH, jcBox, 40, layout.NORTH, searchPanel);
		layout.putConstraint(layout.EAST, jcBox, 150, layout.WEST, searchPanel);
		searchPanel.add(jcBox);
		//for search
		layout.putConstraint(layout.NORTH, search, 5, layout.NORTH, searchPanel);
		layout.putConstraint(layout.WEST, search, -150, layout.EAST, searchPanel);
		layout.putConstraint(layout.SOUTH, search, 40, layout.NORTH, searchPanel);
		layout.putConstraint(layout.EAST, search, -5, layout.EAST, searchPanel);
		searchPanel.add(search);
		//for input
		layout.putConstraint(layout.NORTH, input, 5, layout.NORTH, searchPanel);
		layout.putConstraint(layout.WEST, input, 5, layout.EAST, jcBox);
		layout.putConstraint(layout.SOUTH, input, 40, layout.NORTH, searchPanel);
		layout.putConstraint(layout.EAST, input, -5, layout.WEST, search);
		searchPanel.add(input);
	}
	
	private void addRateField(){
		String[] val = {"1","2","3","4","5"};
		rateVal = new JComboBox(val);
		rate = new JButton("Rate");
		rate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				int rating = rateVal.getSelectedIndex() + 1;
				if (currentType == ButtonSourceType.USER_SEARCH)
					sendUpdate("insert into history values (" + bPanel.getUserId()
							+ "," + selectedTuple[0] + "," + "now(),"+rating+")");
				else
					sendUpdate("insert into history values (" + bPanel.getUserId()
						+ "," + selectedTuple[1] + "," + "now(),"+rating+")");
				searchPanel.removeAll();
				searchPanel.repaint();
				addSearchField(searchlayout);
				searchPanel.revalidate();
			}
		});
		searchlayout.putConstraint(searchlayout.NORTH, rateVal, 1, layout.NORTH, searchPanel);
		searchlayout.putConstraint(searchlayout.WEST, rateVal, 1, layout.WEST, searchPanel);
		searchlayout.putConstraint(searchlayout.SOUTH, rateVal, -1, layout.SOUTH, searchPanel);
		searchlayout.putConstraint(searchlayout.EAST, rateVal, 100, layout.WEST, rateVal);
		searchPanel.add(rateVal);
		searchlayout.putConstraint(searchlayout.NORTH, rate, 1, layout.NORTH, searchPanel);
		searchlayout.putConstraint(searchlayout.WEST, rate, 1, layout.EAST, rateVal);
		searchlayout.putConstraint(searchlayout.SOUTH, rate, -1, layout.SOUTH, searchPanel);
		searchlayout.putConstraint(searchlayout.EAST, rate, 200, layout.WEST, rateVal);
		searchPanel.add(rate);
	}
	
	private void createSearchButton(){
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
					sendQuery("select * from video where cid = ?:"
							+ input.getText());
				sendAction(ButtonSourceType.USER_SEARCH);
			}
		});
	}
	
	public void addWatchButton(){
		watch = new JButton("Watch");
		watch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				searchPanel.removeAll();
				searchPanel.repaint();
				addRateField();
				searchPanel.revalidate();
			}
		});
		searchlayout.putConstraint(searchlayout.NORTH, watch, 1, searchlayout.NORTH, searchPanel);
		searchlayout.putConstraint(searchlayout.WEST, watch, 1, searchlayout.WEST, searchPanel);
		searchlayout.putConstraint(searchlayout.SOUTH, watch, -1, searchlayout.SOUTH, searchPanel);
		searchlayout.putConstraint(searchlayout.EAST, watch, 200, searchlayout.WEST, watch);
		searchPanel.add(watch);
	}
	
	public void addDeleteButton(){
		delete = new JButton("Delete");
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (currentType){
				case USER_HISTORY:
					sendUpdate("delete from history where uid = " 
							+ selectedTuple[0] + " and vid = " + selectedTuple[1]);
					break;
				case USER_FAVORITES:
					sendUpdate("delete from favorites where uid = " 
							+ selectedTuple[0] + " and vid = " + selectedTuple[1]);
					break;
				default:
					break;
				}
				searchPanel.removeAll();
				searchPanel.repaint();
				addSearchField(searchlayout);
			}
		});
		
		searchlayout.putConstraint(searchlayout.NORTH, delete, 1, searchlayout.NORTH, searchPanel);
		searchlayout.putConstraint(searchlayout.WEST, delete, 1, searchlayout.EAST, watch);
		searchlayout.putConstraint(searchlayout.SOUTH, delete, -1, searchlayout.SOUTH, searchPanel);
		searchlayout.putConstraint(searchlayout.EAST, delete, 200, searchlayout.WEST, delete);
		searchPanel.add(delete);
	}
	
	public String getUserId(){
		return bPanel.getUserId();
	}
	
	public void displaySearchResult(){
		this.removeAll();
		this.addSearchPanel();
		this.addResultPanel();
		this.repaint();
	}
	
	public void displayHistorySection(){
		this.removeAll();
		this.addSearchPanel();
		this.addResultPanel();
		this.repaint();
	}
	
	public void displayFavoritesSection(){
		this.removeAll();
		this.addSearchPanel();
		this.addResultPanel();
		this.repaint();
	}

	public void displayUploadSection(){
		this.removeAll();
		//this.add(textarea);
		this.addSearchPanel();
		this.addUploadPanel();
		this.addResultPanel();
		//this.add(uploadSection.get());
		//this.addSearchField(searchlayout);
		this.repaint();
	}
	
	public void updateUpload(){
		((UploadPanel)uploadSection).confirmMessage();
	}
	
	@Override
	public JPanel get() {
		this.removeAll();
		this.add(searchPanel);
		return this;
	}

	@Override
	public void display(String result, ButtonSourceType type) {
		this.remove(resultPanel);
		resultPanel = new TablePanel(result, this);
		this.addResultPanel();
		this.repaint();
	}

	@Override
	public void switchSection(ButtonSourceType type) {
		this.currentType = type;
		switch (type){
		case USER_SEARCH:
			displaySearchResult();
			break;
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
	public void selectRow(String[] row, JButton button) {
		this.selectedTuple = row;
		this.selectedButton = button;
		switch (currentType){
		case USER_SEARCH:
			searchPanel.removeAll();
			this.repaint();
			this.addWatchButton();
			this.revalidate();
			break;
		case USER_UPLOAD:
		case USER_HISTORY:
		case USER_FAVORITES:
			searchPanel.removeAll();
			this.repaint();
			this.addWatchButton();
			this.addDeleteButton();
			this.revalidate();
			break;
		default:
			break;
		}
		//sendUpdate("insert into history (uid,vid,date) values (" + bPanel.getUserId() + "," + row[1] + "," + "now())");
		//sendAction(ButtonSourceType.SELECT);
	}

	@Override
	public void sendSelectedTuple(String[] row) {
		// TODO Auto-generated method stub
		
	}
}
