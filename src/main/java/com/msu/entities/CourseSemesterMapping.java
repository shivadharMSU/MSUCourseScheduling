package com.msu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_semester_mappping") // Note the typo fix in the table name
public class CourseSemesterMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_semester_mapping_id")
    private Long courseSemesterMappingId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "sem_id")
    private Integer semesterId;

    @Column(name = "tenure")
    private Integer tenure;

    @Column(name = "term_tenure")
    private Integer termTenure;

    
   

    // Getters and setters
    public Long getCourseSemesterMappingId() {
        return courseSemesterMappingId;
    }

    public void setCourseSemesterMappingId(Long courseSemesterMappingId) {
        this.courseSemesterMappingId = courseSemesterMappingId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public Integer getTermTenure() {
        return termTenure;
    }

    public void setTermTenure(Integer termTenure) {
        this.termTenure = termTenure;
    }

    
}
