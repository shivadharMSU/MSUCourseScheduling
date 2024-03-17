package com.msu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_details")
public class CourseDetails {
	// ghfv
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private long courseId;
	
	@Column(name = "course_number")
    private int courseNumber;
	
	@Column(name = "crn")
    private int crn;  
	
	@Column(name = "course_name")
    private String courseName; 
	
	
	
	@Column(name = "credits")
    private int credits; 
	
	@Column(name = "cmputer_required")
    private boolean computerRequired;  
	


    public CourseDetails() {
		super();
	}



	public CourseDetails(long courseId, int courseNumber, int crn, String courseName, int termTenure, int credits, boolean computerRequired) {
        this.courseId = courseId;
        this.courseNumber = courseNumber;
        this.crn = crn;
        this.courseName = courseName;
        this.credits = credits;
        this.computerRequired = computerRequired;
    }

    

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

 

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseNumber=" + courseNumber +
                ", crn=" + crn +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", computerRequired=" + computerRequired +
                '}';
    }



	

}
