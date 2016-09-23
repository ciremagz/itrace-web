package ph.edu.usjr.team2.itrace.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import ph.edu.usjr.team2.itrace.web.model.Message;
import ph.edu.usjr.team2.itrace.web.model.Playlist;
import ph.edu.usjr.team2.itrace.web.model.User;
import ph.edu.usjr.team2.itrace.web.response.LibraryResponse;

import ph.edu.usjr.team2.itrace.web.response.ProfileResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {
	private String webHost = "http://localhost:8081/syntones-web";

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute User user, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("Running Itrace.web.UserController.login() method. > " + user.getUsername());
		RestTemplate restTemplate = new RestTemplate();

		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(user);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		LibraryResponse loginResponse = restTemplate.postForObject(webHost + "/login", entity, LibraryResponse.class);
		System.out.println(loginResponse.getMessage().getFlag());
		if (loginResponse.getMessage().getFlag()) {

			List<Playlist> recentlyPlayedPlaylists = loginResponse.getRecentlyPlayedPlaylists();
			System.out.println("Received from response lists of playlists");
			if(recentlyPlayedPlaylists != null){
				for (Playlist pl : recentlyPlayedPlaylists) {
					// System.out.println("harharharharharh");
					System.out.println("Playlist Name: " + pl.getPlaylistName());
				}
				mav.addObject("playlists", recentlyPlayedPlaylists);
			}else{
				mav.addObject("system_message", "you do not have a saved playlist yet.");
			}
			
			mav.setViewName("library");
			// creating session
			session.invalidate();
			HttpSession newSession = request.getSession();
			newSession.setAttribute("username", user.getUsername());
		} else {
			mav.setViewName("index");
			mav.addObject("system_message", loginResponse.getMessage().getMessage());
		}

		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView addUser(@ModelAttribute User user) {
		ModelAndView mav = new ModelAndView("index");
		System.out.println("Running Itrace.web.UserController.addUser() method. > " + user.getUsername());

		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(user);

		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		// send user details to the rest api
		RestTemplate rt = new RestTemplate();
		Message resultMessage = rt.postForObject(webHost + "/register", entity, Message.class);
		mav.addObject("system_message", resultMessage.getMessage());
		return mav;
	}

	@RequestMapping(value = "/profile")
	public ModelAndView showProfile(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("profile");
		RestTemplate restTemplate = new RestTemplate();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		User user = new User(username);
		// converting user object to json
		Gson gson = new Gson();
		String userJson = gson.toJson(user);
		// adding the object to the headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJson, header);

		ProfileResponse profileResponse = restTemplate.postForObject(webHost + "/profile", entity,
				ProfileResponse.class);
		User fetchedUser = profileResponse.getUser();
		mav.addObject("user", fetchedUser);
		return mav;
	}

	@RequestMapping(value = "/delete/{username}")
	public ModelAndView deleteUser(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("login");
		// insert rest service here to delete the user
		return mav;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout() {

		ModelAndView mav = new ModelAndView("index");

		return mav;
	}

}
