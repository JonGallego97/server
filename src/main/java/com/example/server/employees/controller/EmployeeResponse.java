package com.example.server.employees.controller;

import com.example.server.persistence.Department;
import com.example.server.persistence.Employee;


public class EmployeeResponse {

	private Integer id;
	private String name;
	private String position;
	private Integer salary;
	private Employee boss;
	private Integer boss_id;

	private Department department;
	public EmployeeResponse(Integer id, String name, String position, Integer salary, Integer boss_id,
			Integer departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.boss_id = boss_id;
		this.departmentId = departmentId;
	}
	private Integer departmentId;

	
	public Integer getBoss_id() {
		return boss_id;
	}
	public void setBoss_id(Integer boss_id) {
		this.boss_id = boss_id;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public EmployeeResponse(Integer id, String name, String position, Integer salary, Employee boss,
			Department department) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.boss = boss;
		this.department = department;
	}
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
