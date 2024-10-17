package com.msu.DTO;
import java.util.List;

public class CourseFullDetailsDTO {
    private CourseDetailsDTO courseDetails;
    private List<CourseProfessorMappingDTO> professorMappings;
    private List<CourseSemesterMappingDTO> semesterMappings;

    // Constructors
    public CourseFullDetailsDTO() {}

    public CourseFullDetailsDTO(CourseDetailsDTO courseDetails, List<CourseProfessorMappingDTO> professorMappings, List<CourseSemesterMappingDTO> semesterMappings) {
        this.courseDetails = courseDetails;
        this.professorMappings = professorMappings;
        this.semesterMappings = semesterMappings;
    }

    // Getters and Setters
    public CourseDetailsDTO getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(CourseDetailsDTO courseDetails) {
        this.courseDetails = courseDetails;
    }

    public List<CourseProfessorMappingDTO> getProfessorMappings() {
        return professorMappings;
    }

    public void setProfessorMappings(List<CourseProfessorMappingDTO> professorMappings) {
        this.professorMappings = professorMappings;
    }

    public List<CourseSemesterMappingDTO> getSemesterMappings() {
        return semesterMappings;
    }

    public void setSemesterMappings(List<CourseSemesterMappingDTO> semesterMappings) {
        this.semesterMappings = semesterMappings;
    }
}
