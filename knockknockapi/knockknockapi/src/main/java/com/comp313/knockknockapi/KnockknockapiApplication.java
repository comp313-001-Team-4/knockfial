package com.comp313.knockknockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KnockknockapiApplication {

	    @Bean
	    BCryptPasswordEncoder bCryptPasswordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(KnockknockapiApplication.class, args);
		
	}

}
