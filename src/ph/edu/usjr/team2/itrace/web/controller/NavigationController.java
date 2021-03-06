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
import ph.edu.usjr.team2.itrace.web.model.Tag;
import ph.edu.usjr.team2.itrace.web.model.User;
import ph.edu.usjr.team2.itrace.web.response.ArtistResponse;
import ph.edu.usjr.team2.itrace.web.response.LibraryResponse;
import ph.edu.usjr.team2.itrace.web.response.PlaylistResponse;
import ph.edu.usjr.team2.itrace.web.response.PlaylistSongsResponse;
import ph.edu.usjr.team2.itrace.web.response.SearchResponse;
import ph.edu.usjr.team2.itrace.web.response.SongListResponse;
import ph.edu.usjr.team2.itrace.web.response.TagsResponse;

@Controller
public class NavigationController {
	private String webHost = "http://localhost:8081/syntones-web";

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	
	@RequestMapping(value="/showArtists")
	public ModelAndView generatePlaylistByArtist(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("showArtists");
		RestTemplate restTemplate = new RestTemplate();
		ArtistResponse artistResponse = restTemplate.getForObject(webHost+"/getAllArtists", ArtistResponse.class);
//		for(Tag e: tagResponse.getTags()){
//			System.out.println(">>> "+e.getTag());
//		}
		mav.addObject("artists",artistResponse.getArtists());
		return mav;
	}
	
	
	@RequestMapping(value="/showTags")
	public ModelAndView showTagsPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("showTags");
		RestTemplate restTemplate = new RestTemplate();
		TagsResponse tagResponse = restTemplate.getForObject(webHost+"/getAllTags", TagsResponse.class);
//		for(Tag e: tagResponse.getTags()){
//			System.out.println(">>> "+e.getTag());
//		}
		mav.addObject("tags",tagResponse.getTags());
		return mav;
	}
	
	
	@RequestMapping(value = "/library")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("library");
		User user = new User((String) request.getSession().getAttribute("username"));
		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(user);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);
		RestTemplate restTemplate = new RestTemplate();

		LibraryResponse libraryResponse = restTemplate.postForObject(webHost + "/library", entity,
				LibraryResponse.class);
		if (libraryResponse.getMessage().getFlag()) {
			List<Playlist> recentlyPlayedPlaylists = libraryResponse.getRecentlyPlayedPlaylists();
			System.out.println("Received from response lists of playlists");
			for (Playlist pl : recentlyPlayedPlaylists) {
				// System.out.println("harharharharharh");
				System.out.println("Playlist Name: " + pl.getPlaylistName());
				
			}
			mav.addObject("playlists", recentlyPlayedPlaylists);
		} else {
			mav.addObject("system_message", libraryResponse.getMessage().getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/registration")
	public String registration() {
		return "registration";
	}
	
	@RequestMapping(value="/index")
	public String login(){
		return "index";
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
				mav.addObject("system_message", "you do not have any playlist yet.");
			}
		}
		
		return mav;
	}

	@RequestMapping(value = "/songlist")
	public ModelAndView showSongList() {
		ModelAndView mav = new ModelAndView("songlist");
		RestTemplate restTemplate = new RestTemplate();
		SongListResponse songListResponse = restTemplate.getForObject(webHost + "/songlist", SongListResponse.class);
		if (songListResponse.getMessage().getFlag()) {
			for (Song s : songListResponse.getSongs()) {
				System.out.println(">> " + s.toString());
			}
			mav.addObject("songs", songListResponse.getSongs());
		} else {
			mav.addObject("system_message", songListResponse.getMessage().getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/artistList")
	public String showArtistList() {
		return "artistList";
	}

	@RequestMapping(value = "/newPlaylist")
	public ModelAndView showMakePlayListPage() {
		ModelAndView mav = new ModelAndView("newPlaylist");
		return mav;
	}

	@RequestMapping(value = "/search")
	public ModelAndView showSearch() {
		ModelAndView mav = new ModelAndView("search");
		RestTemplate restTemplate = new RestTemplate();
		SongListResponse searchResponse = restTemplate.getForObject(webHost + "/songList", SongListResponse.class);
		mav.addObject("system_message", searchResponse.getMessage().getMessage());

		return mav;
	}

	@RequestMapping(value = "/playlistSongs", method = RequestMethod.POST)
	public ModelAndView showPlaylistSongs(@RequestParam(value = "id") long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("playlistSongs");

		Map<String, String> data = new HashMap<>();

		data.put("id", String.valueOf(id));
		data.put("username", (String) request.getSession().getAttribute("username"));

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
		if (psResponse.getMessage().getFlag()) {
			System.out.println("Received response list of song for playlist: " + id);
			for (Song e : psResponse.getPlaylist().getSongs()) {
				System.out.println(e.toString());
			}
			mav.addObject("songs", psResponse.getPlaylist().getSongs());
			mav.addObject("playlistName", psResponse.getPlaylist().getPlaylistName());
			mav.addObject("id", id);
		} else {
			mav.addObject("system_message", psResponse.getMessage().getMessage());
		}
		return mav;
	}

}
