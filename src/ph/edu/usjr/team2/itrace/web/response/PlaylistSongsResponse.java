package ph.edu.usjr.team2.itrace.web.response;

import java.util.List;

import ph.edu.usjr.team2.itrace.web.model.Message;
import ph.edu.usjr.team2.itrace.web.model.Playlist;
import ph.edu.usjr.team2.itrace.web.model.Song;

public class PlaylistSongsResponse {
	private List<Song> songsOnPlaylist;
	private Message message;
	
	public PlaylistSongsResponse(){}
	
	


	public List<Song> getSongsOnPlaylist() {
		return songsOnPlaylist;
	}




	public void setSongsOnPlaylist(List<Song> songsOnPlaylist) {
		this.songsOnPlaylist = songsOnPlaylist;
	}




	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}
