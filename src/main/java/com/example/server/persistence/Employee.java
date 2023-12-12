package com.example.server.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 60)
	private String name;
	@Column(length = 60)
	private String position;
	@Column
	private Integer salary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="boss_id",foreignKey = @ForeignKey(name="Fk_boss_id"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Employee boss;

	@Column(name="boss_id", updatable= false, insertable=false)
	private Integer boss_id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="department_id",foreignKey = @ForeignKey(name="fk_departmentId"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Department department;
	
	@Column(name="department_id", updatable= false, insertable=false)
	private Integer department_id;

	
	public Employee(Integer id, String name, String position, Integer salary, Integer boss_id, Integer departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.boss_id = boss_id;
		this.department_id = departmentId;
	}
	
	public Employee(String name, String position, Integer salary, Integer boss_id, Integer department_id) {
		super();
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.boss_id = boss_id;
		this.department_id = department_id;
	}
	public Employee getBoss() {
		return boss;
	}
	public void setBoss(Employee boss) {
		this.boss = boss;
	}

	
	public Employee(Integer id, String name, String position, Integer salary) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	public Employee() {
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





}
