package view.body.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.ButtonSourceType;
import view.QueryGenerator;
import view.body.UserPanel;
import view.body.WindowPanel;
import view.body.SectionPanel;

public class UploadPanel extends JPanel implements SectionPanel, QueryGenerator{
	private JLabel vid;
	private JTextField vidfield;
	private JLabel message;
	private WindowPanel dPanel;
	private JButton upload;
	
	private static final String intro = "Enter vid to upload";
	
	public UploadPanel(WindowPanel dPanel){
		super();
		this.dPanel = dPanel;
		vid = new JLabel(intro);
		vidfield = new JTextField(10);
		message = new JLabel();
		this.add(message);
		this.add(vid);
		this.add(vidfield);
		this.addUploadButton();
	}
	
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
	public void display(String result) {
		
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
