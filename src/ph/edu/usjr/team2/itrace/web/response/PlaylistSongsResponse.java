package ph.edu.usjr.team2.itrace.web.response;

import java.util.List;

import ph.edu.usjr.team2.itrace.web.model.Message;
import ph.edu.usjr.team2.itrace.web.model.Playlist;
import ph.edu.usjr.team2.itrace.web.model.Song;

public class PlaylistSongsResponse {
	private Playlist playlist;
	private Message message;

	public PlaylistSongsResponse() {
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
}
