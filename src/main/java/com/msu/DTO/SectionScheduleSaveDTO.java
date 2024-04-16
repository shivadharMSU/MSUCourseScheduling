package com.msu.DTO;

public class SectionScheduleSaveDTO {
	
	
	private Long courseSemesterMappingId;
	private Long courseId;
	private Long SectionId;
	private Long professorId;
	private Integer roomId;
	private Integer capacity;
	private Integer maxCapacity;
	private String sectionNo;
	private Integer weekId;
	private String startTime;
	private String endTime;
	private Long crossSectionId;
	private TimeSlotsDTO[] timeSlots;
	
	
	
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
	public Long getSectionId() {
		return SectionId;
	}
	public void setSectionId(Long sectionId) {
		SectionId = sectionId;
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
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public Integer getWeekId() {
		return weekId;
	}
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
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
	public Long getCrossSectionId() {
		return crossSectionId;
	}
	public void setCrossSectionId(Long crossSectionId) {
		this.crossSectionId = crossSectionId;
	}
	public TimeSlotsDTO[] getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(TimeSlotsDTO[] timeSlots) {
		this.timeSlots = timeSlots;
	}
	
	
	

}
