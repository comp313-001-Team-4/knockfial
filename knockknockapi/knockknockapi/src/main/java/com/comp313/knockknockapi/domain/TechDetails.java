package com.comp313.knockknockapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class TechDetails {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    
	    @Column(unique = true)
	    private String username;
	   
	    @NotBlank(message = "FullName field is required")
	    private String fullName;
	   
	    @NotBlank(message = "Address field is required")
	    private String address;
	    @NotBlank(message = "ServiceType field is required")
	    private String type;
	    @NotBlank(message = "LicenseNo field is required")
	    private String licenseNo;
	    private float rating;
	    private double lat;
	    private double lon;
	    
	    @Transient
	    private double distance;
	    
	    
		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		public Long getId() {
			return id;
		}
		
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLon() {
			return lon;
		}

		public void setLon(double lon) {
			this.lon = lon;
		}

		public String getType() {
			return type;
		}

		public String getLicenseNo() {
			return licenseNo;
		}

		public void setLicenseNo(String licenseNo) {
			this.licenseNo = licenseNo;
		}

		public void setType(String type) {
			this.type = type;
		}

		public float getRating() {
			return rating;
		}

		public void setRating(float rating) {
			this.rating = rating;
		}

		
		
		
	    
	    
	    
}

