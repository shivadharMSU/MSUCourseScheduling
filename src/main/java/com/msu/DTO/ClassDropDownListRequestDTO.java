package com.msu.DTO;

public class ClassDropDownListRequestDTO {
	
	private Long professorId;
	private Integer semId;
	private String startTime;
	private String endTime;
	private Long courseId;
	public Long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	public Integer getSemId() {
		return semId;
	}
	public void setSemId(Integer semId) {
		this.semId = semId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
		

}
