package com.msu.DTO;

import jakarta.persistence.Column;

public class SemesterTenureDTO {
	

	private Integer tenureId;
	private String weeklyTenure;
	
	public Integer getTenureId() {
		return tenureId;
	}
	public void setTenureId(Integer tenureId) {
		this.tenureId = tenureId;
	}
	public String getWeeklyTenure() {
		return weeklyTenure;
	}
	public void setWeeklyTenure(String weeklyTenure) {
		this.weeklyTenure = weeklyTenure;
	}
	  
	

}
