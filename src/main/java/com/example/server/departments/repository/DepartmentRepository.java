package com.example.server.departments.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.server.persistence.Deparment;

public interface DepartmentRepository extends CrudRepository<Deparment, Integer> {

}

