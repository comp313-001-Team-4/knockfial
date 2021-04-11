package com.comp313.knockknockapi.payload;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {

	 @Email(message = "It does not match email format")
	 @NotBlank(message = "email is required")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
  

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
