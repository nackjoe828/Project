package view.body.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import view.ButtonSourceType;
import view.QueryGenerator;
import view.body.UserPanel;
import view.body.WindowPanel;
import view.body.SectionPanel;

public class UploadPanel extends JPanel implements SectionPanel, QueryGenerator{
	private JLabel vid;
	private JTextField vidfield;
	private JLabel channel;
	private JComboBox jcBox;
	private JLabel message;
	private WindowPanel dPanel;
	private JButton upload;
	private SpringLayout layout;
	
	private static final String intro = "title to upload :";
	
	public UploadPanel(WindowPanel dPanel){
		super();
		this.dPanel = dPanel;
		this.layout = new SpringLayout();
		this.setLayout(layout);
		vid = new JLabel(intro);
		vidfield = new JTextField(10);
		channel = new JLabel("Channel :");
		String[] menu = {"C1", "C2"};
		jcBox = new JComboBox(menu);
		this.addUploadButton();
		this.addLabelInput();
	}
	
	private void addLabelInput(){
		//for vid
		layout.putConstraint(layout.NORTH, vid, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, vid, 5, layout.WEST, this);
		layout.putConstraint(layout.SOUTH, vid, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, vid, 100, layout.WEST, this);
		this.add(vid);
		//for input
		layout.putConstraint(layout.NORTH, vidfield, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, vidfield, 5, layout.EAST, vid);
		layout.putConstraint(layout.SOUTH, vidfield, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, vidfield, 100, layout.WEST, vidfield);
		this.add(vidfield);
		
		layout.putConstraint(layout.NORTH, channel, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, channel, 20, layout.EAST, vidfield);
		layout.putConstraint(layout.SOUTH, channel, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, channel, 80, layout.WEST, channel);
		this.add(channel);
		
		layout.putConstraint(layout.NORTH, jcBox, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, jcBox, 5, layout.EAST, channel);
		layout.putConstraint(layout.SOUTH, jcBox, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, jcBox, -5, layout.WEST, upload);
		this.add(jcBox);
		
	}
	
	
	// change it to title
	
	private void addUploadButton(){
		upload = new JButton("Upload");
		upload.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendUpdate("insert into video (vid, uid) values ("
						+ vidfield.getText() + ","
						+ ((UserPanel)dPanel).getUserId() + ")");
				sendAction(ButtonSourceType.UPLOAD_VIDEO);
				vidfield.setText("");
			}
		});
		//for search
		layout.putConstraint(layout.NORTH, upload, 5, layout.NORTH, this);
		layout.putConstraint(layout.WEST, upload, -150, layout.EAST, this);
		layout.putConstraint(layout.SOUTH, upload, 40, layout.NORTH, this);
		layout.putConstraint(layout.EAST, upload, -5, layout.EAST, this);
		this.add(upload);
	}
	
	public void confirmMessage(){
		message.setText(vidfield.getText() + " is uploaded successfully");
		this.revalidate();
	}

	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void sendAction(ButtonSourceType type) {
		dPanel.sendAction(type);
	}

	@Override
	public void display(String result, ButtonSourceType type) {
		
	}

	@Override
	public void sendQuery(String query) {
		UserPanel up = (UserPanel)dPanel;
		up.sendQuery(query);
	}

	@Override
	public void sendUpdate(String update) {
		UserPanel up = (UserPanel)dPanel;
		up.sendUpdate(update);
	}
}
