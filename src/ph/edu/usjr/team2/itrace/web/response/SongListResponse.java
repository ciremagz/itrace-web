package ph.edu.usjr.team2.itrace.web.response;

import java.util.List;

import ph.edu.usjr.team2.itrace.web.model.Message;
import ph.edu.usjr.team2.itrace.web.model.Song;

public class SongListResponse {
	private Message message;
	private List<Song> songList;
	public SongListResponse(){} 
	public SongListResponse(Message message,List<Song> songList){
		this.message = message;
		this.songList = songList;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public List<Song> getSongs(){
		return this.songList;
	}
	public void setSongList(List<Song> songList){
		this.songList = songList;
	}
	
}
