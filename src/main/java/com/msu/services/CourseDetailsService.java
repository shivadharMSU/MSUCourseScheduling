package com.msu.services;

import java.util.List;

import com.msu.entities.CourseDetails;

public interface CourseDetailsService {
	
	
	public List<CourseDetails> getcourselist();
	public void saveCourseDetails(CourseDetails coursedetails);
	public CourseDetails findCourseDetailsByCourseId(Long courseId);
	

}
