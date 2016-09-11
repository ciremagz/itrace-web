package ph.edu.usjr.team2.itrace.web.response;

import ph.edu.usjr.team2.itrace.web.model.Message;

public class SearchResponse {
	private Message message;

	public SearchResponse() {
	}

	public SearchResponse(Message message) {
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
