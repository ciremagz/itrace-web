package ph.edu.usjr.team2.itrace.web.model;

import java.util.List;

public class Watcher {

	
	private String candidateID;
	private String precinctCode;
	private String userNumber;
	private List<Precinct> precinctList;
	
	public Watcher(){}

	public Watcher(String candidateID, String precinctCode, String userNumber) {
		super();
		this.candidateID = candidateID;
		this.precinctCode = precinctCode;
		this.userNumber = userNumber;
	}

	public String getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}

	public String getPrecinctCode() {
		return precinctCode;
	}

	public void setPrecinctCode(String precinctCode) {
		this.precinctCode = precinctCode;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public List<Precinct> getPrecinctList() {
		return precinctList;
	}

	public void setPrecinctList(List<Precinct> precinctList) {
		this.precinctList = precinctList;
	}
	
}
