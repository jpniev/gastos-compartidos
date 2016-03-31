package main.ar.com.globant.springmvc.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import main.ar.com.globant.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
 
    public User findById(int id) {
        return getByKey(id);
    }
 
    public User findByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        return (User) crit.uniqueResult();
    }
    
    public User findByApiKey(String apiKey){
    	Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("apiKey", apiKey));
        return (User) crit.uniqueResult();
    }
 
     
}
