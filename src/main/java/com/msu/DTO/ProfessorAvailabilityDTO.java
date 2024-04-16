package com.msu.DTO;

import java.time.LocalTime;

public class ProfessorAvailabilityDTO {

	private Long id;
	private Long professorId;
	private Integer semNameId;
	private String dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;

	// Constructors
	public ProfessorAvailabilityDTO() {
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Integer getSemNameId() {
		return semNameId;
	}

	public void setSemNameId(Integer semNameId) {
		this.semNameId = semNameId;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	// Optional: toString(), equals(), and hashCode() methods
}
