package com.msu.DTO;

import java.time.LocalTime;

public class TimeSlotsDTO {
	
	private String[] days;
//	private String startTime;
//	private String endTime;
	 private LocalTime startTime;
	    private LocalTime endTime;
	
	
	
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
	}
	/*
	 * public String getStartTime() { return startTime; } public void
	 * setStartTime(String startTime) { this.startTime = startTime; } public String
	 * getEndTime() { return endTime; } public void setEndTime(String endTime) {
	 * this.endTime = endTime; }
	 */
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
	
	
	
	

}
