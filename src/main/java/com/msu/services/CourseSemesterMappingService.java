package com.msu.services;

import java.util.List;

import com.msu.DTO.CourseDropDownForSemesterDTO;
import com.msu.DTO.SaveCourseForSemesterDTO;
import com.msu.entities.CourseSemesterMapping;

public interface CourseSemesterMappingService {
	
	
	public List<CourseSemesterMapping> findAll();
	public void saveCourseSemesterMapping(CourseSemesterMapping courseSemesterMapping);
	public List<CourseSemesterMapping> findBySemesterId(int semesterId);
	 public CourseSemesterMapping findByCourseSemesterMappingId(Long courseSemesterMappingId);
	 public  void deleteCourseSemesterMappingByCourseIfAndSemId(Integer semId,Long courseId);

     public List<CourseDropDownForSemesterDTO> getCourseDropDownNotIncludedForSemester(Integer semId);
	 public void getSaveCourseForSemesterDTO(SaveCourseForSemesterDTO saveCourseForSemesterDTO);


}
