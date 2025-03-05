package com.msu.services;

import java.util.List;

import com.msu.DTO.CourseFullDetailsDTO;
import com.msu.DTO.CourseResponseDto;
import com.msu.DTO.SemesterTenureDTO;
import com.msu.entities.CourseDetails;
import com.msu.entities.SemesterTenure;

public interface CourseDetailsService {
	
	
	public List<CourseDetails> getcourselist();
	List<CourseFullDetailsDTO> getFullCoursesDetails();
	public CourseDetails findCourseDetailsByCourseId(Long courseId);
    public void updateCourseDetails(CourseResponseDto courseResponseDto);
    public void saveCourseDetails(CourseResponseDto courseResponseDto);
    public List<SemesterTenureDTO> getSemesterTenureList();
	

}
