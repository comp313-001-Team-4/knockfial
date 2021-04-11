package com.comp313.knockknockapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    
	   
	    private String username;
	   
	  
	    private String techname;
	   
	  
	    private String category;


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


	


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getTechname() {
			return techname;
		}


		public void setTechname(String techname) {
			this.techname = techname;
		}


		public String getCategory() {
			return category;
		}


		public void setCategory(String category) {
			this.category = category;
		}
	    
}
