package view.body.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.ButtonSourceType;
import view.body.DisplayPanel;
import view.body.SectionPanel;

public class UploadPanel extends JPanel implements SectionPanel{
	private JLabel vid;
	private JTextField vidfield;
	private DisplayPanel dPanel;
	private JButton upload;
	
	public UploadPanel(DisplayPanel dPanel){
		super();
		this.dPanel = dPanel;
		vid = new JLabel("vid : ");
		vidfield = new JTextField(10);
		this.add(vid);
		this.add(vidfield);
		this.addUploadButton();
	}
	
	private void addUploadButton(){
		upload = new JButton("Upload");
		upload.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(ButtonSourceType.UPLOAD_VIDEO);
			}
		});
		this.add(upload);
	}

	@Override
	public JPanel get() {
		return this;
	}

	@Override
	public void sendMessage(ButtonSourceType type) {
		dPanel.sendMessage(type);
	}

	@Override
	public void display(String result) {
		
	}
}
