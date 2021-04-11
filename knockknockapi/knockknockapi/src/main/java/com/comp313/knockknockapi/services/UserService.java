package com.comp313.knockknockapi.services;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comp313.knockknockapi.domain.TechDetails;
import com.comp313.knockknockapi.domain.User;
import com.comp313.knockknockapi.domain.UserDetails;
import com.comp313.knockknockapi.exceptions.UsernameAlreadyExistsException;
import com.comp313.knockknockapi.repositories.TechDetailsRepo;
import com.comp313.knockknockapi.repositories.UserDetailsRepo;
import com.comp313.knockknockapi.repositories.UserRepository;

@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;

	 @Autowired
	    private UserDetailsRepo userDetailsRepository;
	 
	 @Autowired
	    private TechDetailsRepo techDetailsRepository;


	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    public User saveUser (User newUser){

	        try{
	            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
	            //Username has to be unique (exception)
	            newUser.setUsername(newUser.getUsername());
	            // Make sure that password and confirmPassword match
	            // We don't persist or show the confirmPassword
	            newUser.setConfirmPassword("");
	            return userRepository.save(newUser);

	        }catch (Exception e){
	            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
	        }

	    }
	    public UserDetails saveUserDetails (UserDetails newUserDetails){

	        try{
	            //Username has to be unique (exception)
	          //  newUserDetails.setUsername(newUserDetails.getUsername());
	            // Make sure that password and confirmPassword match
	            // We don't persist or show the confirmPassword
	         //   newUserDetails.setConfirmPassword("");
	           return userDetailsRepository.save(newUserDetails);
	        	//return "";

	        }catch (Exception e){
	            throw new UsernameAlreadyExistsException("Username '"+newUserDetails.getUsername()+"' already exists");
	        }

	    }
	    public TechDetails saveTechDetails (TechDetails newTechDetails){

	        try{
	          
	           return techDetailsRepository.save(newTechDetails);
	        	

	        }catch (Exception e){
	            throw new UsernameAlreadyExistsException("Username '"+newTechDetails.getUsername()+"' already exists");
	        }

	    }

	    public Iterable<TechDetails> findAllByType(String username,Principal principal){
	        
	    	UserDetails user = userDetailsRepository.findByUsername(principal.getName());
	    	System.out.println(user.getFullName());
	    	 double lat= user.getLat();
	    	 double lon=user.getLon();
	    	
	    	List<TechDetails> list= techDetailsRepository.findAllByType(username);
	    	
	    	 list.forEach((tech)->find(tech,lat,lon));
	    	
	    	
	    	
	        return techDetailsRepository.findAllByType(username);
	    }
		private void find(TechDetails tech,double lat,double lon) {
			// TODO Auto-generated method stub
			  tech.setDistance(
					distance(
							
							tech.getLat(),tech.getLon(),lat,lon)  
					  
					  
					  );
			  
			
		}
		private double distance(double lat1, double lon1, double lat2, double lon2) {
		      double theta = lon1 - lon2;
		      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		      dist = Math.acos(dist);
		      dist = rad2deg(dist);
		      dist = dist * 60 * 1.1515;
		      double roundOff = Math.round(dist*100)/100;		     
		      return (roundOff);
		    }
		    
		    
		    private double deg2rad(double deg) {
		      return (deg * Math.PI / 180.0);
		    }
		    
		  
		    private double rad2deg(double rad) {
		    	 return (rad * 180.0 / Math.PI);
		    }
		    }

	
