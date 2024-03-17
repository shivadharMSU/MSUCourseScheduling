package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.CourseSemesterMapping;


public interface CourseSemesterMappingRepository extends JpaRepository<CourseSemesterMapping, Long> {

	
	 public List<CourseSemesterMapping> findBySemesterId(Integer semesterId);
	 
}
