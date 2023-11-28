package com.example.server.persistence;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 55)
	private String name;
	@Column(length = 45)
	private String city;
	
	@OneToMany(mappedBy="department",cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Employee> employees;
	public Department(Integer id, String name, String city, List<Employee> employees) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.employees = employees;
	}
	public Department(Integer id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}
	public Department() {
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
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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
	public List<Employee> getListEmployees() {
		return employees;
	}
	public void setListEmployees(List<Employee> listEmployees) {
		this.employees = listEmployees;
	} 
	

}
