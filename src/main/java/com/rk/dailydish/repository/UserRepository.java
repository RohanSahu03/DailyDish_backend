package com.rk.dailydish.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rk.dailydish.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	  Optional<User> findByPhone(String phone);
}
