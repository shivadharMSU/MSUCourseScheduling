package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.Section;
import org.springframework.stereotype.Repository;


public interface SectionRepository extends JpaRepository<Section, Long> {

	public List<Section> findByCourseSemesterMappingId(Long courseSemesterMappingId);
	public Section findBySectionId(Long sectionId);
	public List<Section> findByProfessorIdAndCourseSemesterMappingId(Long professorId,Long courseSemesterMappingId);



}
