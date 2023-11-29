package com.example.server.employees.controller;

public class EmployeeResponse {

	private String name;
	private String position;
	private Integer salary;
	private Integer bossId;
	private Integer departmentId;
	
	public EmployeeResponse() {
		super();
	}
	public EmployeeResponse(String name, String position, Integer salary, Integer bossId,
			Integer departmentId) {
		super();
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.bossId = bossId;
		this.departmentId = departmentId;
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
	public Integer getBossId() {
		return bossId;
	}
	public void setBossId(Integer bossId) {
		this.bossId = bossId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	

	
}
