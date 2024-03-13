package com.msu.services;

import java.util.List;

import com.msu.entities.Coursedetails;

public interface CourseDetailsService {
	
	
	public List<Coursedetails> getcourselist();
	public void saveCourseDetails(Coursedetails coursedetails);
	

}
