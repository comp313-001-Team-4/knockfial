package com.comp313.knockknockapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import com.comp313.knockknockapi.domain.User;
import com.comp313.knockknockapi.payload.LoginRequest;
import com.comp313.knockknockapi.web.UserController;

@SpringBootTest
class KnockknockapiApplicationTests {


@Autowired
private UserController userController;

private BindingResult result;
private
	@Test
	void contextLoads() {
	
	}


}
