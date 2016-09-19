package ph.edu.usjr.team2.itrace.web.response;

import java.util.List;

import ph.edu.usjr.team2.itrace.web.model.Message;
import ph.edu.usjr.team2.itrace.web.model.Song;

public class SearchResponse {
	private Message message;
	private List<Song> allSongsInTheDB;
	
	public SearchResponse() {
	}
	public SearchResponse(Message message) {
		this.message = message;
	}

	
	
	
	public List<Song> getAllSongsInTheDB() {
		return allSongsInTheDB;
	}
	public void setAllSongsInTheDB(List<Song> allSongsInTheDB) {
		this.allSongsInTheDB = allSongsInTheDB;
	}
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
