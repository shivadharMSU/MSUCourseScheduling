package com.msu.DTO;

public class SectionListDTO {
	
	private Long sectionId;
	private String sectionNo;
	private String professorName;
	private String dayAndTime;
	private boolean status;
	private String classRoomName;
	
	
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public String getDayAndTime() {
		return dayAndTime;
	}
	public void setDayAndTime(String dayAndTime) {
		this.dayAndTime = dayAndTime;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getClassRoomName() {
		return classRoomName;
	}
	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}
	
	
	
	
	
	

}
