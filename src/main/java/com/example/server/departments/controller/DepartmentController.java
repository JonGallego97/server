package com.example.server.departments.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.server.departments.DepartmentExpands;
import com.example.server.departments.repository.DepartmentRepository;
import com.example.server.departments.repository.DepartmentResponse;
import com.example.server.departments.service.DepartmentServiceModel;
import com.example.server.employees.service.EmployeeServiceModel;
import com.example.server.persistence.Department;
import com.example.server.persistence.Employee;

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
	public ResponseEntity<DepartmentServiceModel> getDepartmentById(
		@PathVariable("id") Integer id, 
		@RequestParam(required = false) List<DepartmentExpands> expand
	) {
		
		Department department = departmentRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No encontrado")
		);
		
		List<EmployeeServiceModel> employees = null;
		
		if (expand != null && expand.indexOf(DepartmentExpands.EMPLOYEES) != -1) {
			employees = new ArrayList<EmployeeServiceModel>();
			for (Employee currentEmployee : department.getEmployees()) {
				EmployeeServiceModel bossResponse = null;
				if (expand.indexOf(DepartmentExpands.EMPLOYEES_BOSS) != -1) {
					Employee boss = currentEmployee.getBoss();
					bossResponse = new EmployeeServiceModel(
							boss.getId(),
							boss.getName(),
							boss.getPosition(),
							boss.getSalary(),
							null,
							boss.getBossId(),
							null,
							boss.getDepartmentId()
					);
				}
				employees.add(
					new EmployeeServiceModel(
						currentEmployee.getId(),
						currentEmployee.getName(),
						currentEmployee.getPosition(),
						currentEmployee.getSalary(),
						bossResponse,
						currentEmployee.getBossId(),
						null,
						currentEmployee.getDepartmentId()
					)
				);
			}
		}
		
		DepartmentServiceModel response = new DepartmentServiceModel(department.getId(), department.getName(), department.getCity(), employees);
		return new ResponseEntity<DepartmentServiceModel>(response, HttpStatus.OK);
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
		
		Department department = new Department(departmentResponse.getName(), departmentResponse.getCity());
		return department;
		
	}
}
