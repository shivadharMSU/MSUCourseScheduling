package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.CourseSemesterMapping;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface CourseSemesterMappingRepository extends JpaRepository<CourseSemesterMapping, Long> {

	
	 public List<CourseSemesterMapping> findBySemesterId(Integer semesterId);
	 public CourseSemesterMapping findByCourseSemesterMappingId(Long courseSemesterMappingId);
	 public CourseSemesterMapping findBySemesterIdAndCourseId(Integer semesterId,Long courseId);
	@Modifying
	@Transactional
	@Query("DELETE FROM CourseSemesterMapping c WHERE c.courseId = :courseId AND c.semesterId = :semesterId")
	void deleteByCourseIdAndSemesterId(@Param("courseId") Long courseId, @Param("semesterId") Integer semesterId);
	public List<CourseSemesterMapping> findByCourseId(long courseId);

	 
}
