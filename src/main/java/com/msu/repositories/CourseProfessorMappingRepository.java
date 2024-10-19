package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.msu.entities.CourseProfessorMapping;

public interface CourseProfessorMappingRepository extends JpaRepository<CourseProfessorMapping, Long> {

	List<CourseProfessorMapping> findCourseProfessorMappingByCourse(Long course);
	List<CourseProfessorMapping> findByProfessor(Long professorId);
	void deleteByProfessor(Long professorId);

}
