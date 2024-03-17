package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.CourseDetails;

public interface CoursedetailsRepository extends JpaRepository<CourseDetails, Long> {
	
	public CourseDetails findCourseDetailsByCourseId(Long courseId);

}
