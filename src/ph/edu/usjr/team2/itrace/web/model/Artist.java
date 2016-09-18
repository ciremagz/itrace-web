package ph.edu.usjr.team2.itrace.web.model;


public class Artist {

	
	private long artistId;
	

	private String artistName;
	
	public Artist(){
		
	}
	

	public Artist(String artistName){
		this.artistName = artistName;
	}
	public long getArtistId() {
		return artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	
}
