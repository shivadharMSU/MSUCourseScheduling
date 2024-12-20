package com.msu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor_details")
public class ProfessorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "course_load", columnDefinition = "TEXT")
    private String courseLoad;

    @Column(name = "professor_type")
    private Integer professorType;
    
    @Column(name = "prof_status")
    private boolean profStatus;
    
    // Getters and setters
    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getProfStatus() {
		return profStatus;
	}

	public void setProfStatus(boolean profStatus) {
		this.profStatus = profStatus;
	}

	public String getCourseLoad() {
        return courseLoad;
    }

    public void setCourseLoad(String courseLoad) {
        this.courseLoad = courseLoad;
    }

    public Integer getProfessorType() {
        return professorType;
    }

    public void setProfessorType(Integer professorType) {
        this.professorType = professorType;
    }


}
