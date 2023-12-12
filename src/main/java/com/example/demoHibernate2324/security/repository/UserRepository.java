package com.example.demoHibernate2324.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import security.persistance.UserDAO;



public interface UserRepository extends CrudRepository<UserDAO, Integer> {
	Optional<UserDAO> findByEmail(String email);
}
