package com.msu.DTO;

import java.util.List;

public class DisplayCourseAndSectionResponseDTO {
	
	private long courseId;
	private long courseSemesterMappingId;
	private int courseNubmer;
	private String courseName;
	private List<SectionListDTO> sectionList;
	
	
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getCourseSemesterMappingId() {
		return courseSemesterMappingId;
	}
	public void setCourseSemesterMappingId(long courseSemesterMappingId) {
		this.courseSemesterMappingId = courseSemesterMappingId;
	}
	public int getCourseNubmer() {
		return courseNubmer;
	}
	public void setCourseNubmer(int courseNubmer) {
		this.courseNubmer = courseNubmer;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public List<SectionListDTO> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<SectionListDTO> sectionList) {
		this.sectionList = sectionList;
	}
	
	
	
	

}
