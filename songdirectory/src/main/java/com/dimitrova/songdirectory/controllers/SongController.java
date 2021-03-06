package com.dimitrova.songdirectory.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.dimitrova.songdirectory.models.LoginUser;
import com.dimitrova.songdirectory.models.Song;
import com.dimitrova.songdirectory.models.User;
import com.dimitrova.songdirectory.services.SongService;
import com.dimitrova.songdirectory.services.UserService;

@Controller
public class SongController {
	
//	set up access to service
	@Autowired
	private SongService songService;
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
	 // Bind empty User and LoginUser objects to the JSP
	 // to capture the form input
		model.addAttribute("newUser", new User());
	    model.addAttribute("newLogin", new LoginUser());
	    return "userLogin.jsp";
	    }
	
//	registers new user, checks for errors, registers in db
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		
		User user = userService.register(newUser, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "userLogin.jsp";
		}
		
		session.setAttribute("userId", user.getId());
		
		return "redirect:/songs";
	}
	
//	logs in existing user, checks for errors
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
        
        User user = userService.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "userLogin.jsp";
        }
    
        session.setAttribute("userId", user.getId());
    
        return "redirect:/songs";
    }
	

//	main page, lists all current songs	
	@GetMapping("/songs")
    public String songs(Model model, HttpSession session) {
    	
//		redirects unauthorized users back to login page
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	model.addAttribute("user", userService.findById((Long)session.getAttribute("userId")));
    	model.addAttribute("songs", songService.allSongs());
    	return "index.jsp";
    }
	
//	create new songs
	@GetMapping("/songs/new")
    public String newSong(@ModelAttribute("song") Song song, Model model, HttpSession session) {
        
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		
        User user = userService.findById((Long)session.getAttribute("userId"));
        
    	model.addAttribute("user", user);
        
        return "newSong.jsp";
    }
	
	@PostMapping("/songs/new")
	public String index(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session) {
		
		if(result.hasErrors()) {
			return "newSong.jsp";
		}
		
		else {
			User user = userService.findById((Long)session.getAttribute("userId"));
			song.setUser(user);
			songService.createSong(song);
			return "redirect:/songs";
		}
	}
	
//	edit songs
	@GetMapping("/songs/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
        
		if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
		
		Song song = songService.findSong(id);
        model.addAttribute("song", song);
        
        return "edit.jsp";
    }
    
    @PutMapping("/songs/{id}/edit")
    public String update(@Valid @ModelAttribute("song") Song song, @PathVariable("id") Long id, BindingResult result, HttpSession session) {
        
    	if (result.hasErrors()) {
            return "redirect:/songs/{id}/edit";
        } 
        
    	else {
    		Song currentSong = songService.findSong(id);
    		song.setUpdatedTimes(currentSong.getUpdatedTimes());
			User user = userService.findById((Long)session.getAttribute("userId"));
			song.setUser(user);
            songService.updateSong(song);
            return "redirect:/songs";
        }
    }
	
//    delete songs
    @DeleteMapping("/songs/{id}")
    public String destroy(@PathVariable("id") Long id) {
        songService.deleteSong(id);
        return "redirect:/songs";
    }
    
//    song details
    @GetMapping("/songs/{id}")
    public String songSongs(@PathVariable("id") Long id, Model model, HttpSession session) {
		
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	Song song = songService.findSong(id);
    	
    	model.addAttribute("user", userService.findById((Long)session.getAttribute("userId")));
    	model.addAttribute("song", song);
    	
    	return "details.jsp";
    }
    
//    logout user
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	
    	session.invalidate();
    	
    	return "redirect:/";
    }
}

