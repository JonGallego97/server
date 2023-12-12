package com.example.server.departments.repository;

import java.util.List;

import com.example.server.employees.controller.EmployeeResponse;
import com.example.server.persistence.Employee;

public class DepartmentResponse {
	private Integer id;
	private String name;
	private String city;
	private List<EmployeeResponse> listEmployees;
	public DepartmentResponse(Integer id, String name, String city, List<EmployeeResponse> listEmployees) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.listEmployees = listEmployees;
	}
	public DepartmentResponse(Integer id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}
	public DepartmentResponse() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<EmployeeResponse> getListEmployees() {
		return listEmployees;
	}
	public void setListEmployees(List<EmployeeResponse> listEmployees) {
		this.listEmployees = listEmployees;
	}

}
