package com.msu.DTO;
import java.util.List;

public class CourseResponseDto {
    private Long courseId;
    private String courseName;
    private int courseNumber;
    private int credits;
    private boolean computerRequired;
    private List<Long> professors;

    // Constructor
    public CourseResponseDto(Long courseId, String courseName, int courseNumber,
                             int credits, boolean computerRequired, List<Long> professors) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.credits = credits;
        this.computerRequired = computerRequired;
        this.professors = professors;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isComputerRequired() {
        return computerRequired;
    }

    public void setComputerRequired(boolean computerRequired) {
        this.computerRequired = computerRequired;
    }

    public List<Long> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Long> professors) {
        this.professors = professors;
    }

    // toString() for debugging purposes
    @Override
    public String toString() {
        return "CourseResponseDto{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", credits=" + credits +
                ", computerRequired=" + computerRequired +
                ", professors=" + professors +
                '}';
    }
}
