package com.comp313.knockknockapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.comp313.knockknockapi.domain.User;
import com.comp313.knockknockapi.domain.UserDetails;

public interface UserDetailsRepo extends CrudRepository<UserDetails, Long> {

	UserDetails findByUsername(String name);

}
