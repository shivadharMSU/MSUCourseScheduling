package com.msu.DTO;

public class getProfessorTypeResponseDTO {
	
	private int id;
    private String type;

    // Constructors
    public getProfessorTypeResponseDTO() {
    }

    public getProfessorTypeResponseDTO(int id, String type) {
        this.id = id;
        this.type = type;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setName(String type) {
        this.type = type;
    }

}
