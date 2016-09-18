package ph.edu.usjr.team2.itrace.web.model;

public class User {


	private String username, password, email, dateOfBirth, gender;

	private long userId;
	public User(String username, String password, String email, String dateOfBirth, String gender) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;

	}
	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(long userId, String username, String password, String email, String dateOfBirth, String gender) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", username=" + username + ", email=" + email
				+ ", gender=" + gender + ", date_of_birth=" + dateOfBirth + "]";

	}

}
