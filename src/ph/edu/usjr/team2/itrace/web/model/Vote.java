package ph.edu.usjr.team2.itrace.web.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Vote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sequence_gen")
	@SequenceGenerator(name="sequence_gen",sequenceName="sequence_gen",allocationSize=1)
	private int voteId;
	
	private String candidateName;
	private int numberOfVotes;
	
	public Vote(){}

	
	
	public Vote(String candidateName, int numberOfVotes) {
		super();
		this.candidateName = candidateName;
		this.numberOfVotes = numberOfVotes;
	}



	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}



	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", candidateName=" + candidateName + ", numberOfVotes=" + numberOfVotes + "]";
	}
	
}
