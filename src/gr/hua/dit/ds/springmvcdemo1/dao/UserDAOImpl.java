package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.springmvcdemo1.entity.Candidate;
import gr.hua.dit.ds.springmvcdemo1.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{
	 
		@Autowired
	    private SessionFactory sessionFactory;

	    @Override
	    @Transactional
	    public List<User> getUsers(String role) { 
	        // get current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();

	        // create a query
	        Query<User> query = currentSession.createQuery("from User tmp where tmp.role='"+role+"'", User.class);


	        // execute the query and get the results list
	        List<User> students = query.getResultList();

	        //return the results
	        return students;
	    }
	    
	 
		@Transactional
	    public List<Object[]> getUsersPerSuper(String supervisorID) { 
	        // get current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();

	        // create a query
	        List<Object[]> candidates = currentSession.createSQLQuery("SELECT userInfo.first_name, userInfo.last_name, userInfo.id FROM userInfo INNER JOIN candidate ON candidate.userInfo_id=userInfo.id where candidate.supervisor="+supervisorID+";").list();


	        // execute the query and get the results list
	        

	        //return the results
	        return candidates;
	    }
	    
	    @Override
	    @Transactional
	    public int getCount(String role) { 
	    	
	    	// get current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();

	        // create a query
	        Query<User> query = currentSession.createQuery("from User tmp where tmp.role='"+role+"'", User.class);


	        // execute the query and get the results list
	        List<User> result = query.getResultList();

	        //return the results
	        return result.size();
	    }
	    
	    @Override
	    @Transactional
	    public User getUserInfo(String userID) { 
	    	
	    	// get current hibernate session
	        Session currentSession = sessionFactory.getCurrentSession();

	        // create a query
	        Query<User> query = currentSession.createQuery("from User tmp where tmp.id='"+userID+"'", User.class);


	        // execute the query and get the results list
	        User result = query.getSingleResult();

	        //return the results
	        return result;
	    }
}
