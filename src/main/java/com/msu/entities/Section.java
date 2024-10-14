package com.msu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;
    
    
    @Column(name = "section_no")
    private String sectionNo;


    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "max_capacity")
    private Integer maxCapacity;
    
   
    @Column(name = "professor_id")
    private Long professorId;
    
    @Column(name = "room_id")
    private Integer roomId;
    
    @Column(name = "cross_section_id")
    private Long crossSectionId;
    
    @Column(name = "course_semester_mapping_id")
    private Long courseSemesterMappingId;
    

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}


	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	

	

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Long getCrossSectionId() {
		return crossSectionId;
	}

	public void setCrossSectionId(Long crossSectionId) {
		this.crossSectionId = crossSectionId;
	}

	public Long getCourseSemesterMappingId() {
		return courseSemesterMappingId;
	}

	public void setCourseSemesterMappingId(Long courseSemesterMappingId) {
		this.courseSemesterMappingId = courseSemesterMappingId;
	}

	public String getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	

	
	
    
}
