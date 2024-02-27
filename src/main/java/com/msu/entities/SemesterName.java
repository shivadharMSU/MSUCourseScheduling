package com.msu.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "semester_name")
public class SemesterName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sem_name_id")
    private Integer semNameId;

    @Column(name = "name", length = 100)
    private String name;

    
    public Integer getSemNameId() {
		return semNameId;
	}

	public void setSemNameId(Integer semNameId) {
		this.semNameId = semNameId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
