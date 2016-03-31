package main.ar.com.globant.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.ar.com.globant.springmvc.dao.UserDao;
import main.ar.com.globant.springmvc.model.User;
 
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao dao;
 
    public User findById(int id) {
        return dao.findById(id);
    }
 
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }
    
    public User findByApiKey(String apiKey){
    	return dao.findByApiKey(apiKey);
    }
    
 
}