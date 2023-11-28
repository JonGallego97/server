package com.example.server.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.server.employees.repository.EmployeeRepository;
import com.example.server.persistence.Employee;

@RestController
@RequestMapping("api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<Iterable<Employee>> getEmployees(){
		return new ResponseEntity<Iterable<Employee>>(employeeRepository.findAll(), HttpStatus.OK);
	
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")Integer id){
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"Empleado no encontrado"));
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeResponse employeeResponse){
		
		Employee employee = convertEmployeeResponsetoEmployee(employeeResponse);
		employee = employeeRepository.save(employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id")Integer id){
		try {
			employeeRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Empleado no encontrado");
		}
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeResponse employeeResponse){
		Employee employee = convertEmployeeResponsetoEmployee(employeeResponse);
		employee = employeeRepository.save(employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
	}
	
	private Employee convertEmployeeResponsetoEmployee(EmployeeResponse employeeResponse) {
		
		Employee employee = new Employee(null,employeeResponse.getName(), employeeResponse.getPosition(), employeeResponse.getSalary());
		return employee;
		
	}
	

}
