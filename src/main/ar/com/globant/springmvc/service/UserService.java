package main.ar.com.globant.springmvc.service;

import main.ar.com.globant.springmvc.model.User;

public interface UserService {
	 
    User findById(int id);
     
    User findByUsername(String username);
    
    User findByApiKey(String apiKey);
     
}