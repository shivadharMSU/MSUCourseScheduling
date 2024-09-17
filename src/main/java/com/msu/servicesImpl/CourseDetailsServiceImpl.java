package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.CourseDetails;
import com.msu.repositories.CoursedetailsRepository;
import com.msu.services.CourseDetailsService;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {
	
	@Autowired
	private CoursedetailsRepository coursedetailsRepository;

	@Override
	public List<CourseDetails> getcourselist() {
	    List<CourseDetails> courseDetailsList = coursedetailsRepository.findAll();

	    return courseDetailsList;
	}

	
	@Override
	public void saveCourseDetails(CourseDetails coursedetails) {
		
		coursedetailsRepository.save(coursedetails);
		
	}


	@Override
	public CourseDetails findCourseDetailsByCourseId(Long courseId) {
		return coursedetailsRepository.findCourseDetailsByCourseId(courseId);
		
	}


	

	

	

}
