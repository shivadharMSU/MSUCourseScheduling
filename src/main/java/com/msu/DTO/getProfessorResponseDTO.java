package com.msu.DTO;

import java.util.List;

public class getProfessorResponseDTO {

	private Long professorId;
	private String name;
	private String courseLoad;
	private String professorTypeName; // Descriptive professor type
	private boolean profStatus;
	private List<ProfessorAvailabilityDTO> availabilities; // Assuming you also have a DTO for availabilities

	// Constructors, Getters, and Setter

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

	public String getProfessorTypeName() {
		return professorTypeName;
	}

	public void setProfessorTypeName(String professorTypeName) {
		this.professorTypeName = professorTypeName;
	}

	public boolean isProfStatus() {
		return profStatus;
	}

	public void setProfStatus(boolean profStatus) {
		this.profStatus = profStatus;
	}

	public List<ProfessorAvailabilityDTO> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<ProfessorAvailabilityDTO> availabilities) {
		this.availabilities = availabilities;
	}

}
