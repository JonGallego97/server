package com.example.server.departments.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.server.persistence.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}

