package com.msu.DTO;
public class CourseDetailsDTO {
    private Long courseId;
    private Integer courseNumber;
    private String courseName;
    private Integer credits;
    private Boolean computerRequired;

    // Constructors
    public CourseDetailsDTO() {}

    public CourseDetailsDTO(Long courseId, Integer courseNumber, String courseName, Integer credits, Boolean computerRequired) {
        this.courseId = courseId;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.credits = credits;
        this.computerRequired = computerRequired;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Boolean getComputerRequired() {
        return computerRequired;
    }

    public void setComputerRequired(Boolean computerRequired) {
        this.computerRequired = computerRequired;
    }
}
