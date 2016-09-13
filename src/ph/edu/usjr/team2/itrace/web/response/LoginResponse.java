package ph.edu.usjr.team2.itrace.web.response;

import java.util.List;

import ph.edu.usjr.team2.itrace.web.model.Message;
import ph.edu.usjr.team2.itrace.web.model.Playlist;
import ph.edu.usjr.team2.itrace.web.model.User;

public class LoginResponse {
	
	
	
	private Message message;
	private List<Playlist> recentlyPlayedPlaylists;
	
	public LoginResponse(){}

	
	public LoginResponse(User user, Message message, List<Playlist> recentlyPlayedPlaylists){
		this.message = message;
		this.recentlyPlayedPlaylists = recentlyPlayedPlaylists;
	}
	
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Playlist> getRecentlyPlayedPlaylists() {
		return recentlyPlayedPlaylists;
	}

	public void setRecentlyPlayedPlaylists(List<Playlist> recentPlaylistsPlayed) {
		this.recentlyPlayedPlaylists = recentPlaylistsPlayed;
	}


		
}
