package ph.edu.usjr.team2.itrace.web.model;

public class User {
	
	private long userId;
	private String username;
	private String password;
	
	public User(){}
	
	public User(String username){
		this.username = username;
	}
	public User(String username,String password){
		this.username = username;
		this.password = password;
	}


	public User(long userId, String username, String password){
		this.userId = userId;
		this.username = username;
		this.password = password;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}
}
