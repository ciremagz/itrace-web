package ph.edu.usjr.team2.itrace.web.model;

import java.util.List;

public class Playlist {
	private User user;
	private List<Song> songList;
	private String[] songIdList;
	
	public Playlist(){}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

	public String[] getSongIdList() {
		return songIdList;
	}

	public void setSongIdList(String[] songIdList) {
		this.songIdList = songIdList;
	}
	
	
	
	
	
}
