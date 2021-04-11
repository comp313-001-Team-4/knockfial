package com.comp313.knockknockapi.repositories;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.comp313.knockknockapi.domain.TechDetails;

public interface TechDetailsRepo extends CrudRepository<TechDetails, Long>{
    
	List<TechDetails> findAllByType(String username);
}
