package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msu.entities.SectionSchedule;

public interface SectionScheduleRepository extends JpaRepository<SectionSchedule, Long>  {
	
	public List<SectionSchedule> findBySectionId(Long sectionId);   
	public SectionSchedule findBySectionScheduledId(Long semNameId); 
	
	
 
	
}
