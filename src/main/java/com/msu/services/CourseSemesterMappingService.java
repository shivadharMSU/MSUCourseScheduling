package com.msu.services;

import java.util.List;

import com.msu.entities.CourseSemesterMapping;

public interface CourseSemesterMappingService {
	
	
	public List<CourseSemesterMapping> findAll();
	public void saveCourseSemesterMapping(CourseSemesterMapping courseSemesterMapping);
	public List<CourseSemesterMapping> findBySemesterId(int semesterId);

}
