package com.example.server.employees.controller;

import com.example.server.persistence.Employee;


public class EmployeeResponse {

	private Integer id;
	private String name;
	private String position;
	private Integer salary;
	private Employee boss;
	public EmployeeResponse(Integer id, String name, String position, Integer salary, Employee boss) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.boss = boss;
	}
	public EmployeeResponse() {
		super();
	}
	public EmployeeResponse(Integer id, String name, String position, Integer salary) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Employee getBoss() {
		return boss;
	}
	public void setBoss(Employee boss) {
		this.boss = boss;
	}

}
