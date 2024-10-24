package com.msu.services;

import java.util.List;

import com.msu.entities.CourseProfessorMapping;



public interface CourseProfessorMappingService{

	public List<CourseProfessorMapping> findAll();
	public void processMethodForSave(Long profId, List<Long> courseId);
    public void saveCourseProfessorMapping(CourseProfessorMapping courseProfessorMapping);
	public List<CourseProfessorMapping> findCourseProfessorMappingByCourse(Long course);
	void deleteExistingMappingsForProfessor(Long professorId);
	List<CourseProfessorMapping> findByProfessor(Long professorId);
	void deleteExistingMappingsForCourse(Long courseId);
	void processMethodForSaveMappingCourses(Long courseId, List<Long> professorIds);
}
