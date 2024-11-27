package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.msu.entities.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

	@Query("SELECT s FROM Section s JOIN CourseSemesterMapping csm "
			+ "ON s.courseSemesterMappingId = csm.courseSemesterMappingId "
			+ "WHERE s.roomId = :roomId AND csm.semesterId = :semesterId")
	List<Section> findByRoomIdAndSemesterId(@Param("roomId") int roomId, @Param("semesterId") int semesterId);

	public List<Section> findByCourseSemesterMappingId(Long courseSemesterMappingId);

	public Section findBySectionId(Long sectionId);

	public List<Section> findByProfessorIdAndCourseSemesterMappingId(Long professorId, Long courseSemesterMappingId);

	@Modifying
	@Transactional
	@Query("DELETE FROM Section s WHERE s.sectionId = :sectionId")
	void deleteBySectionId(@Param("sectionId") Long sectionId);

	@Modifying
	@Transactional
	@Query("DELETE FROM Section s WHERE s.courseSemesterMappingId = :courseSemesterMappingId")
	void deleteByCourseSemesterMappingId(@Param("courseSemesterMappingId") Long courseSemesterMappingId);

}
