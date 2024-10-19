package com.msu.DTO;
public class CourseSemesterMappingDTO {
    private Long courseSemesterMappingId;
    private Long courseId;
    private Integer semesterId;
    private Integer tenure;
    private Integer termTenure;

    // Constructors
    public CourseSemesterMappingDTO() {}

    public CourseSemesterMappingDTO(Long courseSemesterMappingId, Long courseId, Integer semesterId, Integer tenure, Integer termTenure) {
        this.courseSemesterMappingId = courseSemesterMappingId;
        this.courseId = courseId;
        this.semesterId = semesterId;
        this.tenure = tenure;
        this.termTenure = termTenure;
    }

    // Getters and Setters
    public Long getCourseSemesterMappingId() {
        return courseSemesterMappingId;
    }

    public void setCourseSemesterMappingId(Long courseSemesterMappingId) {
        this.courseSemesterMappingId = courseSemesterMappingId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public Integer getTermTenure() {
        return termTenure;
    }

    public void setTermTenure(Integer termTenure) {
        this.termTenure = termTenure;
    }
}
