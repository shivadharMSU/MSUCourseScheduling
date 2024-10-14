package com.msu.DTO;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveOrUpdateProfessorRequestDTO {

	@JsonProperty("professorId")
	private Long professorId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("courseLoad")
	private String courseLoad; 

	@JsonProperty("profTypeId")
	private int profType;

	@JsonProperty("profStatus")
	private boolean profStatus;

	@JsonProperty("availabilities")
	private List<Availability> availabilities;

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseLoad() {
		return courseLoad;
	}

	public void setCourseLoad(String courseLoad) {
		this.courseLoad = courseLoad;
	}

	public int getProfType() {
		return profType;
	}

	public void setProfType(int profType) {
		this.profType = profType;
	}

	public boolean isProfStatus() {
		return profStatus;
	}

	public void setProfStatus(boolean profStatus){
		this.profStatus = profStatus;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	// Getters and setters

	public static class Availability {
		
		public int getSemNameId() {
			return semNameid;
		}
		
		public void setSemNameId(int semNameid) {
			this.semNameid = semNameid;
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
		
		@JsonProperty("dayOfWeek")
		private int semNameid;

		@JsonProperty("dayOfWeek")
		private String dayOfWeek;

		@JsonProperty("startTime")
		private LocalTime startTime;

		@JsonProperty("endTime")
		private LocalTime endTime;

	}

}
