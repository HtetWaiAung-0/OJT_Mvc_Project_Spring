package bean;

public class UserBean {
	private String userId;
	private String userMail;
	private String userRole;
	private String userPassword;
	private String userConPassword;
	private String searchUserId;
	private String searchUserMail;

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String getSearchUserMail() {
		return searchUserMail;
	}

	public void setSearchUserMail(String searchUserMail) {
		this.searchUserMail = searchUserMail;
	}

	public String getUserConPassword() {
		return userConPassword;
	}

	public void setUserConPassword(String userConPassword) {
		this.userConPassword = userConPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
