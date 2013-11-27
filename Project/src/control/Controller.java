package control;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.ButtonSourceType;
import view.MainFrame;
import view.PageType;
import database.Model;

public class Controller {
	private Model model;
	private MainFrame mFrame;
	private String result;
	private int updateStatus;
	private String[] user;
	private String[] selectedTuple;
	private JTextField inputtext;
	
	public Controller(Model model){
		this.model = model;
	}
	
	public void setFrame(MainFrame mFrame){
		this.mFrame = mFrame;
	}
	
	public void execute(ButtonSourceType type){
		try{
			switch(type){
			case REGISTER:
				if(mFrame.isNewUser()){
					update(mFrame.getUpdate());
					if(updateStatus == 1){
						select(mFrame.selectUser());
						setUserName();
						mFrame.switchPage(PageType.USER);
					}
					else{
						mFrame.showErrorMessage();
					}
				}
				break;
			case LOGIN:
				if(mFrame.isUser()){
					select(mFrame.selectUser());
					setUserName();
					mFrame.switchPage(PageType.USER);
				}
				else mFrame.switchPage(PageType.ADMIN);
				break;
			case CHANNEL_AVE:
				System.out.println(selectedTuple[0]);
				model.channelavgrating(Integer.parseInt(selectedTuple[0]));
				result = model.getResult();
				System.out.println("Channel Average");
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case CHANNEL_LATEST:
				System.out.println(selectedTuple[0]);
				model.latestVideo(Integer.parseInt(selectedTuple[0]));
				result = model.getResult();
				System.out.println("Lastest Video");
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_RECOMMEND:
				model.recommendation(Integer.parseInt(user[0]));
				result = model.getResult();
				System.out.println("RECOMMENDATION");
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case LOGOUT:
				mFrame.switchPage(PageType.START);
				mFrame.showTable("clear", null);
				break;
			case ARCHIVE:
				//System.out.println("WSDFSDFASF");
				String input;
				int number = JOptionPane.showConfirmDialog(null, getPanel(), "Enter Date YYYY-MM-DD", JOptionPane.OK_CANCEL_OPTION);
				if( number == JOptionPane.OK_OPTION)
				{
					input = inputtext.getText();
					input = "'" + input + "'";
					model.archievetable(input);
					//System.out.println(input);
				}
				break;
			case MOST_FAMOUS:
				model.getMostFamous();
				result = model.getResult();
				System.out.println("Most Famous");
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case RECENT_HOT:
				model.getRecentHot();
				result = model.getResult();
				System.out.println("RECENT_Hot");
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case INACTIVE_USER:
				model.getInactiveUser();
				result = model.getResult();
				System.out.println("Inactive userl");
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case ADMIN_USER:
			case ADMIN_VIDEO:
			case ADMIN_HISTORY:
			case ADMIN_FAVORITES:
			case ADMIN_CHANNEL:
				show(type);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_SEARCH:
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_UPLOAD:
				select("select * from video where uid = ?:" + user[0]);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_HISTORY:
				select("select * from history where uid = ?:" + user[0]);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case USER_FAVORITES:
				select("select * from favorites where uid = ?:" + user[0]);
				mFrame.switchSection(type);
				mFrame.showTable(result, null);
				break;
			case UPLOAD_VIDEO:
				select("select * from video where uid = ?:" + user[0]);
				mFrame.showTable(result, null);
				break;
			case SELECT:
				select("select * from history where uid = ?:" + user[0]);
				mFrame.showTable(result, null);
				break;
			default:
				break;
			}
		}catch(Exception e){System.out.println("error");}
	}
	
	public void setUserName(){
		//controller.getUserName contains labels which have to be omitted
		String[] str = result.split("\n");
		user = str[1].split("\t");
		mFrame.setUserName(user);
	}
	
	public void show(ButtonSourceType type){
		try{
			switch (type){
			case ADMIN_USER:
				model.getUser();
				break;
			case ADMIN_VIDEO:
				model.getVideo();
				break;
			case ADMIN_HISTORY:
				model.getHistory();
				break;
			case ADMIN_FAVORITES:
				model.getFavorites();
				break;
			case ADMIN_CHANNEL:
				model.getChannel();
				break;
			default:
				break;
			}
		}catch(Exception e){}
		result = model.getResult();
	}
	
	public void update(String update){
		updateStatus = model.update(update);
	}
	
	public void select(String query){
		try{
			model.select(query);
		}catch(Exception e){}
		result = model.getResult();
	}
	
	public void setSelectedTuple(String[] row){
		this.selectedTuple = row;
	}
	
	public String getUserName(String query){
		try{
			model.select(query);
		}catch(Exception e){}
		return getResult();
	}
	
	public String getResult(){
		return result;
	}
	
	public int getUpdateStatus(){
		return updateStatus;
	}
	
	public JPanel getPanel()
	{
		JPanel bozispanel = new JPanel();
		inputtext = new JTextField(10);
		bozispanel.add(new JLabel("Enter Date"));
		bozispanel.add(inputtext);
		return bozispanel;
	}
}
