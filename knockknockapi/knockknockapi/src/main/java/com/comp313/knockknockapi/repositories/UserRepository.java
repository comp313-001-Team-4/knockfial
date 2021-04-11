package com.comp313.knockknockapi.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comp313.knockknockapi.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {



    User findByUsername(String username);
    User getById(Long id);
}


