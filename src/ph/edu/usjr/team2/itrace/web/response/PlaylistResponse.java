package ph.edu.usjr.team2.itrace.web.response;

import ph.edu.usjr.team2.itrace.web.model.Message;
import ph.edu.usjr.team2.itrace.web.model.Playlist;

public class PlaylistResponse {
	private Message message;
	private Playlist playlist;
	
	
	public PlaylistResponse(){}
	public PlaylistResponse(Playlist playlist, Message message){
		this.playlist = playlist;
		this.message = message;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Playlist getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
}
