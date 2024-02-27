package com.msu.services;

import java.util.List;

import com.msu.entities.CourseSchedule;

public interface CourseScheduleService {
	
	public List<CourseSchedule> findAll();
    public void saveCourseSchedule(CourseSchedule courseSchedule);

}
