package com.msu.services;

import java.util.List;

import com.msu.entities.CourseDetails;
import com.msu.entities.CourseProfessorMapping;



public interface CourseProfessorMappingService{

	public List<CourseProfessorMapping> findAll();
    public void saveCourseProfessorMapping(CourseProfessorMapping courseProfessorMapping);
	public List<CourseProfessorMapping> findCourseProfessorMappingByCourse(Long course);

}
