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
	private String courseLoad; // Consider converting this to List<Integer> or int[]

	@JsonProperty("profType")
	private String profType;

	@JsonProperty("availabilities")
	private List<Availability> availabilities;

	// Getters and setters

	public static class Availability {

        @JsonProperty("dayOfWeek")
        private String dayOfWeek;

        @JsonProperty("startTime")
        private LocalTime startTime;

        @JsonProperty("endTime")
        private LocalTime endTime;
        
	}

}
