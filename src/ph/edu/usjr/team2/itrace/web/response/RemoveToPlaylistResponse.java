package ph.edu.usjr.team2.itrace.web.response;

import ph.edu.usjr.team2.itrace.web.model.Message;

public class RemoveToPlaylistResponse {
	private Message message;
	
	
	public RemoveToPlaylistResponse(){
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}
	
}
