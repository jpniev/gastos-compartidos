package main.ar.com.globant.springmvc.service;

import java.util.List;

import main.ar.com.globant.springmvc.model.User;

public interface UserService {
	 
    User findById(int id);
     
    User findByUsername(String username);
    
    User findByApiKey(String apiKey);
     
    List<User> findAllUsers();
}