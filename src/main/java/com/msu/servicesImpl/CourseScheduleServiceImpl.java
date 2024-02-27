package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.CourseSchedule;
import com.msu.repositories.CourseScheduleRepository;
import com.msu.services.CourseScheduleService;

@Service("courseScheduleService")
public class CourseScheduleServiceImpl implements CourseScheduleService{

	 @Autowired
	 private CourseScheduleRepository courseScheduleRepository;
	 
	@Override
	public List<CourseSchedule> findAll() {
		try {
			return courseScheduleRepository.findAll();
		}catch(Exception ex){
			System.out.println("exception while getting CourseSchedule");
		}
		return null;
	}

	@Override
	public void saveCourseSchedule(CourseSchedule courseSchedule) {
		
		try {
			courseScheduleRepository.save(courseSchedule);
		}catch(Exception ex) {
			System.out.println("exception while saving "+ex);
		}
	
		
	}
	
	

}
