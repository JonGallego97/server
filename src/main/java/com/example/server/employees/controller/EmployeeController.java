package com.example.server.employees.controller;

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

import com.example.server.departments.repository.DepartmentRepository;
import com.example.server.departments.service.DepartmentServiceModel;
import com.example.server.employees.EmployeesExpands;
import com.example.server.employees.repository.EmployeeRepository;
import com.example.server.employees.service.EmployeeServiceModel;
import com.example.server.persistence.Department;
import com.example.server.persistence.Employee;

@RestController
@RequestMapping("api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeServiceModel>> getEmployees(){
		Iterable<Employee> employees = employeeRepository.findAll();
		List<EmployeeServiceModel> response = new ArrayList<EmployeeServiceModel>();
		for(Employee employee: employees) {
			response.add(new EmployeeServiceModel(
					employee.getId(),
					employee.getName(),
					employee.getPosition(),
					employee.getSalary(),
					null,
					employee.getBossId(),
					null,
					employee.getDepartmentId()
					)
				);
			
		}
		return new ResponseEntity<List<EmployeeServiceModel>>(response,HttpStatus.OK);

	
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeServiceModel> getEmployeeById(
			@PathVariable("id")Integer id,
			@RequestParam(required = false) List<EmployeesExpands> expand
			){
		
		DepartmentServiceModel departmentResponse = null;
		EmployeeServiceModel bossResponse = null;
		
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"Empleado no encontrado"));
		
		if(expand!=null && expand.indexOf(EmployeesExpands.BOSS)!=-1) {
			Employee boss = employee.getBoss();
			
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
		if (expand != null && expand.indexOf(EmployeesExpands.DEPARTMENT) != -1) {
			Department department = employee.getDepartment();
			departmentResponse = new DepartmentServiceModel(
				department.getId(),
				department.getName(),
				department.getCity()
			);
		}
		EmployeeServiceModel response = new EmployeeServiceModel(
				employee.getId(),
				employee.getName(),
				employee.getPosition(),
				employee.getSalary(),
				bossResponse, // o nulo o cargado
				employee.getBossId(),
				departmentResponse, // o nulo o cargado
				employee.getDepartmentId()
		);
		return new ResponseEntity<EmployeeServiceModel>(response, HttpStatus.OK); 

		
		
		
	}
	
	@PostMapping("/employees")
	public ResponseEntity<EmployeeServiceModel> createEmployee(@RequestBody EmployeeResponse employeeResponse){
		
		
		Employee boss = employeeRepository.findById(employeeResponse.getBossId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT,"Empleado no encontrado")
		);
		
		Department department = departmentRepository.findById(employeeResponse.getDepartmentId()).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.CONFLICT,"departamento no encontrado"));
		
		
		Employee employee = new Employee(
				employeeResponse.getName(), 
				employeeResponse.getPosition(), 
				employeeResponse.getSalary(), 
				boss, 
				department
			);
		
		employee = employeeRepository.save(employee);
		
		EmployeeServiceModel response = new EmployeeServiceModel(
				employee.getId(),
				employee.getName(),
				employee.getPosition(),
				employee.getSalary(),
				null, // o nulo o cargado. pero habría que llamar a la funcion para cargarlo
				employee.getBossId(),
				null, // o nulo o cargado. pero habría que llamar a la funcion para cargarlo.
				employee.getDepartmentId()
			);
		
		return new ResponseEntity<EmployeeServiceModel>(response, HttpStatus.CREATED);
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
	public ResponseEntity<EmployeeServiceModel> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeResponse employeeResponse) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Empleado no encontrado")
		);
		
		if (employeeResponse.getName() != null) {
			employee.setName(employeeResponse.getName());
		}
		
		//Employee employee = new Employee(employeeResponse.getName(), employeeResponse.getPosition(), employeeResponse.getSalary(), employeeResponse.getBossId(),employeeResponse.getDepartmentId());
		//return employee;
		
		employee = employeeRepository.save(employee);
		EmployeeServiceModel response = new EmployeeServiceModel(
			employee.getId(),
			employee.getName(),
			employee.getPosition(),
			employee.getSalary(),
			null, // o nulo o cargado. pero habría que llamar a la funcion para cargarlo
			employee.getBossId(),
			null, // o nulo o cargado. pero habría que llamar a la funcion para cargarlo.
			employee.getDepartmentId()
		);
		
		return new ResponseEntity<EmployeeServiceModel>(response, HttpStatus.OK);
	}
	
	
	

}
