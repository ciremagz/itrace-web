package ph.edu.usjr.team2.itrace.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import ph.edu.usjr.team2.itrace.web.model.PlaylistSong;
import ph.edu.usjr.team2.itrace.web.model.Song;
import ph.edu.usjr.team2.itrace.web.model.User;
import ph.edu.usjr.team2.itrace.web.response.GeneratePlaylistResponse;
import ph.edu.usjr.team2.itrace.web.response.LibraryResponse;
import ph.edu.usjr.team2.itrace.web.response.PlaylistResponse;
import ph.edu.usjr.team2.itrace.web.response.PlaylistSongsResponse;
import ph.edu.usjr.team2.itrace.web.response.RemoveToPlaylistResponse;

@Controller
public class MusicController {
	private String webHost = "http://localhost:8081/syntones-web";

	@RequestMapping(value = "/saveGeneratedPlaylist", method = RequestMethod.POST)
	public ModelAndView saveGeneratedPlaylist(
			// @RequestParam("songIds") long[] songIds,
			@RequestParam("playlistName") String playlistName, HttpServletRequest request) {
		System.out.println("");
		ModelAndView mav = new ModelAndView();
		NavigationController nc = new NavigationController();
		long[] songIds = (long[]) request.getSession().getAttribute("songIds");
		for (long l : songIds) {
			System.out.println(l + " ++++++++++++=");
		}
		// converting user object to json
		Playlist playlist = new Playlist();
		playlist.setSongIds(songIds);
		User user = new User((String) request.getSession().getAttribute("username"));
		playlist.setUser(user);
		playlist.setPlaylistName(playlistName);
		Gson gson = new Gson();
		String userJson = gson.toJson(playlist);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		GeneratePlaylistResponse generatePlaylistResponse = restTemplate
				.postForObject(webHost + "/saveGeneratedPlaylist", entity, GeneratePlaylistResponse.class);
		mav = nc.home(request);
		mav.addObject("system_message", generatePlaylistResponse.getMessage().getMessage());
		return mav;
	}

	@RequestMapping(value = "/generatePlaylistByArtist", method = RequestMethod.POST)
	public ModelAndView generatePlaylistbyArtist(@RequestParam("artistName") String artistName,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		NavigationController nc = new NavigationController();
		System.out.println("Sending artistname \""+artistName+"\"to generate a playlist.");
		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(artistName);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		GeneratePlaylistResponse generatePlaylistResponse = restTemplate
				.postForObject(webHost + "/generatePlaylistByArtist", entity, GeneratePlaylistResponse.class);
		List<Song> songs = generatePlaylistResponse.getSongs();
		long[] songIds = new long[songs.size()];

		for (int i = 0; i < songs.size(); i++) {
			songIds[i] = songs.get(i).getSongId();
		}
		request.getSession().setAttribute("songIds", songIds);
		if (generatePlaylistResponse.getMessage().getFlag()) {
			mav.addObject("songs", songs);
			mav.setViewName("generatePlaylist");
		} else {
			mav = nc.home(request);
			mav.addObject("system_message", generatePlaylistResponse.getMessage().getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/generatePlaylistByTag", method = RequestMethod.POST)
	public ModelAndView generatePlaylist(@RequestParam("tags") String[] tags, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		NavigationController nc = new NavigationController();
		for (String s : tags) {
			System.out.println(s);
		}

		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(tags);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		GeneratePlaylistResponse generatePlaylistResponse = restTemplate
				.postForObject(webHost + "/generatePlaylistByTags", entity, GeneratePlaylistResponse.class);
		List<Song> songs = generatePlaylistResponse.getSongs();
		long[] songIds = new long[songs.size()];

		for (int i = 0; i < songs.size(); i++) {
			songIds[i] = songs.get(i).getSongId();
		}
		request.getSession().setAttribute("songIds", songIds);
		if (generatePlaylistResponse.getMessage().getFlag()) {
			mav.addObject("songs", songs);
			mav.setViewName("generatePlaylist");
		} else {
			mav = nc.home(request);
			mav.addObject("system_message", generatePlaylistResponse.getMessage().getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/removePlaylist", method = RequestMethod.POST)
	public ModelAndView removePlaylist(HttpServletRequest request, @RequestParam("playlistId") long playlistId) {
		Playlist playlist = new Playlist();
		playlist.setPlaylistId(playlistId);
		String username = (String) request.getSession().getAttribute("username");
		User user = new User(username);
		playlist.setUser(user);
		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(playlist);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		RemoveToPlaylistResponse rtpResponse = restTemplate.postForObject(webHost + "/removePlaylist", entity,
				RemoveToPlaylistResponse.class);
		NavigationController nc = new NavigationController();
		ModelAndView mav = nc.showPlaylist(request);
		return mav;
	}

	@RequestMapping(value = "/removeToPlaylist")
	public ModelAndView removeToPlaylist(HttpServletRequest request, @RequestParam("songId") long songId,
			@RequestParam("playlistId") long playlistId) {

		String username = (String) request.getSession().getAttribute("usename");
		User user = new User(username);
		PlaylistSong playlistSong = new PlaylistSong(songId, playlistId, user);

		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(playlistSong);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		RemoveToPlaylistResponse rtpResponse = restTemplate.postForObject(webHost + "/removeToPlaylist", entity,
				RemoveToPlaylistResponse.class);
		NavigationController nc = new NavigationController();
		ModelAndView mav = nc.showPlaylistSongs(playlistId, request);
		mav.addObject("system_message", rtpResponse.getMessage().getMessage());
		return mav;
	}

	@RequestMapping(value = "/addToPlaylist")
	public ModelAndView addToPlaylist(HttpServletRequest request, @RequestParam("songId") long songId,
			@RequestParam("playlistId") long playlistId) {
		NavigationController nc = new NavigationController();
		ModelAndView mav = nc.home(request);

		String username = (String) request.getSession().getAttribute("username");
		User user = new User(username);
		PlaylistSong playlistSong = new PlaylistSong(songId, playlistId, user);
		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(playlistSong);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		RestTemplate restTemplate = new RestTemplate();
		LibraryResponse libraryResponse = restTemplate.postForObject(webHost + "/addToPlaylist", entity,
				LibraryResponse.class);

		if (libraryResponse.getMessage().getFlag()) {
			List<Playlist> recentlyPlayedPlaylists = libraryResponse.getRecentlyPlayedPlaylists();
			System.out.println("Received from response lists of playlists");
			if (recentlyPlayedPlaylists != null) {
				for (Playlist pl : recentlyPlayedPlaylists) {
					// System.out.println("harharharharharh");
					System.out.println("Playlist Name: " + pl.getPlaylistName());
				}
				mav.addObject("playlists", recentlyPlayedPlaylists);
			} else {
				mav.addObject("system_message", "you do not have a saved playlist yet.");
			}

			mav.setViewName("library");
		} else {
			mav.setViewName("library");
			mav.addObject("system_message", libraryResponse.getMessage().getMessage());
		}

		return mav;
	}

	@RequestMapping(value = "/showAddToPlaylist", method = RequestMethod.POST)
	public ModelAndView showPlaylist(@RequestParam("songId") long songId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("addToPlaylist");
		User user = new User((String) request.getSession().getAttribute("username"));

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
		mav.addObject("songId", songId);
		return mav;
	}

	@RequestMapping(value = "/playPlaylist")
	public ModelAndView playPlaylist(@RequestParam("id") long playlistId, HttpServletRequest request) {
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

		mav.addObject("songTitle", psResponse.getPlaylist().getSongs().get(0).getSongTitle());
		mav.addObject("artist", psResponse.getPlaylist().getSongs().get(0).getArtist().getArtistName());
		mav.addObject("playlistName", psResponse.getPlaylist().getPlaylistName());
		mav.addObject("songs", psResponse.getPlaylist().getSongs());

		// validating if psResponse got lyrics aswell
		System.out.println("validating if psResponse got lyrics aswell");
		System.out.println(psResponse.getPlaylist().getSongs().get(0).getLyrics());
		return mav;
	}

	@RequestMapping(value = "/savePlaylist")
	public ModelAndView savePlayList(@RequestParam("playlistName") String playlistName, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String username = (String) request.getSession().getAttribute("username");
		System.out.println("Running UserController.savePlaylist().");
		System.out.println("Username from session: " + username);

		RestTemplate restTemplate = new RestTemplate();
		Playlist playlist = new Playlist();
		User user = new User(username);

		playlist.setUser(user);
		playlist.setPlaylistName(playlistName);

		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(playlist);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);
		PlaylistResponse playlistResponse = restTemplate.postForObject(webHost + "/savePlaylist", entity,
				PlaylistResponse.class);
		NavigationController nc = new NavigationController();
		mav = nc.showPlaylist(request);
		
		
		
		
		
		
		return mav;
	}
}
