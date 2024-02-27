package com.msu.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_schedule")
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

  
    @Column(name = "sem_id")
    private int semId;

    
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "schedule", length = 100)
    private String schedule;

    @Column(name = "professor_id")
    private Long professorId;

    
    @Column(name = "room_id")
    private int classRoomId;

    @Column(name = "cross_section_schdule_id")
    private Long crossSectionScheduleId;

    @Column(name = "course_duration", length = 100)
    private String courseDuration;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getSemId() {
		return semId;
	}

	public void setSemId(int semId) {
		this.semId = semId;
	}

	

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	

	public int getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(int classRoomId) {
		this.classRoomId = classRoomId;
	}

	public Long getCrossSectionScheduleId() {
		return crossSectionScheduleId;
	}

	public void setCrossSectionScheduleId(Long crossSectionScheduleId) {
		this.crossSectionScheduleId = crossSectionScheduleId;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}
    
    

   
    
}
