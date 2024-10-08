package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.Section;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface SectionRepository extends JpaRepository<Section, Long> {

	public List<Section> findByCourseSemesterMappingId(Long courseSemesterMappingId);
	public Section findBySectionId(Long sectionId);
	public List<Section> findByProfessorIdAndCourseSemesterMappingId(Long professorId,Long courseSemesterMappingId);

	@Modifying
	@Transactional
	@Query("DELETE FROM Section s WHERE s.sectionId = :sectionId")
	void deleteBySectionId(@Param("sectionId") Long sectionId);

}
