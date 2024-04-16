package com.msu.DTO;

public class ProfessorDropDownListRequestDTO {
	
	private Long courseId;
	private Integer semId;
	private Integer weekDay;
	private String startTime;
	private String endTime;
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Integer getSemId() {
		return semId;
	}
	public void setSemId(Integer semId) {
		this.semId = semId;
	}
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
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
	
	
	
	
	

}
