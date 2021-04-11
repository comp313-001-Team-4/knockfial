package com.comp313.knockknockapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.comp313.knockknockapi.domain.Orders;
import com.comp313.knockknockapi.domain.UserDetails;

public interface OrderRepo extends CrudRepository<Orders, Integer> {

}
