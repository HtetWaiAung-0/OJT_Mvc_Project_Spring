package bean;

public class UserBean {
	private String userId;
	private String userMail;
	private String userRole;
	private String userPassword;
	private String userConPassword;

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
