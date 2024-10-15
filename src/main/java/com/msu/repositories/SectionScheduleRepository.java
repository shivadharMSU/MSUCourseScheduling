package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msu.entities.SectionSchedule;
import org.springframework.transaction.annotation.Transactional;

public interface SectionScheduleRepository extends JpaRepository<SectionSchedule, Long>  {
	
	public List<SectionSchedule> findBySectionId(Long sectionId);   
	public SectionSchedule findBySectionScheduledId(Long semNameId);


	@Modifying
	@Transactional
	@Query("DELETE FROM SectionSchedule s WHERE s.sectionId = :sectionId")
	void deleteBySectionId(@Param("sectionId") Long sectionId);

	@Modifying
	@Transactional
	@Query("DELETE FROM SectionSchedule s WHERE s.sectionScheduledId = :sectionScheduledId")
	void deleteBySectionScheduledId(@Param("sectionScheduledId") Long sectionScheduledId);
	
 
	
}
