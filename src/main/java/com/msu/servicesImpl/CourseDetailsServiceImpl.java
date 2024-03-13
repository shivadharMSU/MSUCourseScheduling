package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.Coursedetails;
import com.msu.repositories.CoursedetailsRepository;
import com.msu.services.CourseDetailsService;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {
	
	@Autowired
	private CoursedetailsRepository coursedetailsRepository;

	@Override
	public List<Coursedetails> getcourselist() {
	    List<Coursedetails> courseDetailsList = coursedetailsRepository.findAll();
	    return courseDetailsList;
	}

	
	@Override
	public void saveCourseDetails(Coursedetails coursedetails) {
		
		coursedetailsRepository.save(coursedetails);
		
	}


	

	

	

}
