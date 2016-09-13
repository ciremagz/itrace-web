package ph.edu.usjr.team2.itrace.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ph.edu.usjr.team2.itrace.web.model.Playlist;
import ph.edu.usjr.team2.itrace.web.model.Song;
import ph.edu.usjr.team2.itrace.web.model.User;
import ph.edu.usjr.team2.itrace.web.response.PlaylistResponse;
import ph.edu.usjr.team2.itrace.web.response.PlaylistSongsResponse;
import ph.edu.usjr.team2.itrace.web.response.SearchResponse;
import ph.edu.usjr.team2.itrace.web.response.SongListResponse;

@Controller
public class NavigationController {
	private String webHost = "http://localhost:8081/syntones-web";

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/library")
	public String home() {
		return "library";
	}

	@RequestMapping(value = "/registration")
	public String registration() {
		return "registration";
	}

	@RequestMapping(value = "/playlist")
	public ModelAndView showPlaylist(HttpServletRequest request) {
		// requires userId / username
		ModelAndView mav = new ModelAndView("playlist");
		User user = new User();
		String username = (String) request.getSession().getAttribute("username");
		user.setUsername(username);

		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(user);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		PlaylistResponse playlistResponse = restTemplate.postForObject(webHost + "/playlist", entity,
				PlaylistResponse.class);

		if (playlistResponse != null) {
			System.out.println(playlistResponse.getMessage().getFlag());
			if (playlistResponse.getMessage().getFlag()) {
				List<Playlist> playlists = playlistResponse.getPlaylists();
				for (Playlist e : playlists) {
					System.out.println(e.getPlaylistName());
				}
				mav.addObject("playlists", playlists);
			} else {

			}
		}

		return mav;
	}

	@RequestMapping(value = "/songList", method = RequestMethod.GET)
	public ModelAndView showSongList() {
		ModelAndView mav = new ModelAndView("songList");

		RestTemplate restTemplate = new RestTemplate();
		SongListResponse slr = restTemplate.getForObject(webHost + "/songList", SongListResponse.class);
		return mav;
	}

	@RequestMapping(value = "/artistList")
	public String showArtistList() {
		return "artistList";
	}

	@RequestMapping(value = "/newPlaylist")
	public ModelAndView showMakePlayListPage() {
		ModelAndView mav = new ModelAndView("newPlaylist");
		RestTemplate restTemplate = new RestTemplate();
		SongListResponse songListResponse = restTemplate.getForObject(webHost + "/songList", SongListResponse.class);
		if (songListResponse.getMessage().getFlag()) {
			mav.addObject("songList", songListResponse.getSongs());
		} else {
			mav.addObject("system_message", songListResponse.getMessage().getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/search")
	public ModelAndView showSearch(@RequestParam(value = "searchString") String searchString) {
		ModelAndView mav = new ModelAndView("search");

		RestTemplate restTemplate = new RestTemplate();

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(searchString, header);

		SearchResponse searchResponse = restTemplate.postForObject(webHost + "/search", entity, SearchResponse.class);
		mav.addObject("system_message", searchResponse.getMessage().getMessage());
		return mav;
	}

	@RequestMapping(value = "/playlistSongs", method = RequestMethod.POST)
	public ModelAndView showPlaylistSongs(@RequestParam(value = "id") long id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("playlistSongs");

		Map<String, String> data = new HashMap<>();
		
		data.put("id", String.valueOf(id));
		data.put("username", (String)request.getSession().getAttribute("username"));
		
		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(data);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		PlaylistSongsResponse psResponse = restTemplate.postForObject(webHost + "/playlistSong", entity,
				PlaylistSongsResponse.class);
		if(psResponse.getMessage().getFlag()){
			System.out.println("Received response list of song for playlist: "+id);
			for(Song e: psResponse.getSongsOnPlaylist()){
				System.out.println(e.toString());
			}
			mav.addObject("songs", psResponse.getSongsOnPlaylist());
		}else{
			mav.addObject("system_message",psResponse.getMessage().getMessage());
		}		
		return mav;
	}

}
