package com.msu.DTO;

import java.util.List;

public class GetSemesterResponseDTO {
	
	
	private String year;
	private SemesterDTO[] semesterList;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public SemesterDTO[] getSemesterList() {
		return semesterList;
	}
	public void setSemesterList(SemesterDTO[] semesterList) {
		this.semesterList = semesterList;
	}

}
