package com.example.server.departments.controller;

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

import com.example.server.departments.repository.DepartmentRepository;
import com.example.server.departments.repository.DepartmentResponse;
import com.example.server.persistence.Department;

@RestController
@RequestMapping("api")
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/departments")
	public ResponseEntity<Iterable<Department>> getDepartments(){
		return new ResponseEntity<Iterable<Department>>(departmentRepository.findAll(), HttpStatus.OK);
	
	}
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable("id")Integer id){
		Department department = departmentRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"departamento no encontrado"));
		return new ResponseEntity<Department>(department,HttpStatus.OK);
		
	}
	
	@PostMapping("/departments")
	public ResponseEntity<Department> createDepartment(@RequestBody DepartmentResponse departmentResponse){
		
		Department department= convertDepartmentResponseToDepartment(departmentResponse);
		department = departmentRepository.save(department);
		return new ResponseEntity<Department>(department,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable("id")Integer id){
		try {
			departmentRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,"departamento no encontrado");
		}
	}
	
	@PutMapping("/departments/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") Integer id, @RequestBody DepartmentResponse departmentResponse){
		Department department= convertDepartmentResponseToDepartment(departmentResponse);
		department= departmentRepository.save(department);
		return new ResponseEntity<Department>(department,HttpStatus.CREATED);
	}
	
	private Department convertDepartmentResponseToDepartment(DepartmentResponse departmentResponse) {
		
		Department department = new Department(null,departmentResponse.getName(), departmentResponse.getCity());
		return department;
		
	}
}
