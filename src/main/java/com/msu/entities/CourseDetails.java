package com.msu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;
    
    @Column(name = "course_number")
    private Integer courseNumber;
    
    @Column(name = "crn")
    private Integer crn;
    
    @Column(name = "course_name")
    private String courseName;
    
    @Column(name = "credits")
    private Integer credits;
    
    @Column(name = "computer_required")
    private Boolean computerRequired;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Integer courseNumber) {
		this.courseNumber = courseNumber;
	}

	public Integer getCrn() {
		return crn;
	}

	public void setCrn(Integer crn) {
		this.crn = crn;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Boolean getComputerRequired() {
		return computerRequired;
	}

	public void setComputerRequired(Boolean computerRequired) {
		this.computerRequired = computerRequired;
	}
    
    
    
    
    // Constructors, getters, and setters
}
