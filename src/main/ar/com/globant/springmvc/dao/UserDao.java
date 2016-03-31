package main.ar.com.globant.springmvc.dao;

import main.ar.com.globant.springmvc.model.User;

public interface UserDao {
	 
    User findById(int id);
     
    User findByUsername(String username);
    
    User findByApiKey(String apiKey);
     
}
