package view.body;

import javax.swing.JPanel;

import view.ButtonSourceType;
import view.SectionType;

public interface WindowPanel extends SectionPanel{
	public void switchSection(SectionType sectionType);
}
