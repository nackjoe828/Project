package view;

public enum ButtonSourceType {
	//buttons from admin page
	ADMIN_USER, ADMIN_VIDEO, ADMIN_HISTORY, ADMIN_FAVORITES, ADMIN_CHANNEL,
	
	//buttons from user page
	USER_SEARCH, USER_UPLOAD, USER_HISTORY, USER_FAVORITES,
	
	//buttons from upload section
	UPLOAD_VIDEO,
	
	//buttons from table
	WATCH, SELECT,
	
	//extra buttons?
	REGISTER, LOGIN, LOGOUT, ARCHIVE, INACTIVE_USER, MOST_FAMOUS, RECENT_HOT, HIGH_AVE, USER_RECOMMEND, CHANNEL_AVE, CHANNEL_LATEST;
}
