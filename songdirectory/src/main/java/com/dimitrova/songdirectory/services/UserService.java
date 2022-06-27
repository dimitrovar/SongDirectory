package com.dimitrova.songdirectory.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dimitrova.songdirectory.models.LoginUser;
import com.dimitrova.songdirectory.models.User;
import com.dimitrova.songdirectory.repositories.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepo;
    
    public User register(User newUser, BindingResult result) {
        
//		Rejections
    	Optional<User> userLookUp = userRepo.findByEmail(newUser.getEmail());
    	if (userLookUp.isPresent()) {
    		result.rejectValue("email", "Unique", "Account with this email already exists.");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
        
//    	return null for errors
    	if(result.hasErrors()) {
    		return null;
    	}
    
//    	hash and save
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	
    	newUser = userRepo.save(newUser);
    	
        return newUser;
    }
    
    public User login(LoginUser newLogin, BindingResult result) {
        
//		Look for user in db, reject if unable to find
    	Optional<User> userLookUp = userRepo.findByEmail(newLogin.getEmail());
    	if (!userLookUp.isPresent()) {
    		result.rejectValue("email", "MissingAccount", "No account found.");
    		return null;
    	}
    	
    	// if user exists, retrieve id
    	User user = userLookUp.get();
        
        // reject if password does not match
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	
        // return null for errors
    	if(result.hasErrors()) {
    		return null;
    	}
    	
        return user;
    }

	public User findByEmail(String email) {
		
		Optional<User> result = userRepo.findByEmail(email);
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
	}
	
	public User findById(Long id) {
		
		Optional<User> result = userRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
	}
}
