package ph.edu.usjr.team2.itrace.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


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
