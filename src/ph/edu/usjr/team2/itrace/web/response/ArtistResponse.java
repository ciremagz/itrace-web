package ph.edu.usjr.team2.itrace.web.response;

import java.util.List;

import ph.edu.usjr.team2.itrace.web.model.Artist;
import ph.edu.usjr.team2.itrace.web.model.Message;

public class ArtistResponse {
	private List<Artist> artists;
	private Message message;
	
	public ArtistResponse(){
		
	}


	public List<Artist> getArtists() {
		return artists;
	}


	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}


	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}
