package com.example.server.employees.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.server.persistence.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	

}
