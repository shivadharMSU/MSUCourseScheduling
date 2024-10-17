package com.msu.DTO;
public class CourseProfessorMappingDTO {
    private Long mappingId;
    private Long courseId;
    private Long professorId;

    // Constructors
    public CourseProfessorMappingDTO() {}

    public CourseProfessorMappingDTO(Long mappingId, Long courseId, Long professorId) {
        this.mappingId = mappingId;
        this.courseId = courseId;
        this.professorId = professorId;
    }

    // Getters and Setters
    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}
