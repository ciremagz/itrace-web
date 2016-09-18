package ph.edu.usjr.team2.itrace.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ph.edu.usjr.team2.itrace.web.response.PlaylistSongsResponse;

@Controller
public class MusicController {
	private String webHost = "http://localhost:8081/syntones-web";
	
	
	@RequestMapping(value = "/playPlaylist")
	public ModelAndView playPlaylist(@RequestParam("id") long playlistId,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("listen");

		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(playlistId);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);
		
		RestTemplate restTemplate = new RestTemplate();
		PlaylistSongsResponse psResponse = restTemplate.postForObject(webHost + "/playPlaylist", entity,
				PlaylistSongsResponse.class);
		
		mav.addObject("songTitle",psResponse.getPlaylist().getSongs().get(0).getSongTitle());
		mav.addObject("artist",psResponse.getPlaylist().getSongs().get(0).getArtist().getArtistName());
		mav.addObject("playlistName",psResponse.getPlaylist().getPlaylistName());
		mav.addObject("songs",psResponse.getPlaylist().getSongs());
		
		//validating if psResponse got lyrics aswell
		System.out.println("validating if psResponse got lyrics aswell");
		System.out.println(psResponse.getPlaylist().getSongs().get(0).getLyrics());
		return mav;
	}
}
