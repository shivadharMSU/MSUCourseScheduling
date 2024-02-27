package com.msu.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sem_id")
    private Integer semId;

    @Column(name = "sem_name_id")
    private Integer semNameId;

    @Column(name = "year", length = 100)
    private String year;

    // Constructors, getters, and setters
    // Getters and setters
    public Integer getSemId() {
        return semId;
    }

    public void setSemId(Integer semId) {
        this.semId = semId;
    }

    public Integer getSemNameId() {
        return semNameId;
    }

    public void setSemNameId(Integer semNameId) {
        this.semNameId = semNameId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
