package com.msu.services;

import java.util.List;

import com.msu.entities.SectionSchedule;

public interface SectionScheduleService {
	
	
	public List<SectionSchedule> findAll();
	public void saveSectionSchedule(SectionSchedule sectionSchedule);
	public List<SectionSchedule> findBySectionId(Long sectionId); 

}
