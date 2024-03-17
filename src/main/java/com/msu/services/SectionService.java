package com.msu.services;

import java.util.List;

import com.msu.entities.Section;

public interface SectionService {

	
	public List<Section> findAll();
    public void saveSection(Section section);
    public List<Section> findByCourseSemesterMappingId(Long courseSemesterMappingId);
    
}
