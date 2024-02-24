package com.msu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="professor")
public class Professor {
	
	@Id
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="professor_type")
	private String professorType;
	
	@Column(name="list_of_courses")
	private String listOfCourses;
	
	@Column(name="available_time")
	private String availableTime;
	
	@Column(name="available_day")
	private String availableDay;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfessorType() {
		return professorType;
	}
	public void setProfessorType(String professorType) {
		this.professorType = professorType;
	}
	public String getListOfCourses() {
		return listOfCourses;
	}
	public void setListOfCourses(String listOfCourses) {
		this.listOfCourses = listOfCourses;
	}
	public String getAvailableTime() {
		return availableTime;
	}
	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}
	public String getAvailableDay() {
		return availableDay;
	}
	public void setAvailableDay(String availableDay) {
		this.availableDay = availableDay;
	}
	
	

}
