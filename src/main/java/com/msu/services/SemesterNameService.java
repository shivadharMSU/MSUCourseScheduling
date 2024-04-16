package com.msu.services;

import java.util.List;

import com.msu.entities.SemesterName;

public interface SemesterNameService {

	
	
	public List<SemesterName> findAll();
    public void saveSemesterName(SemesterName semesterName);
    public SemesterName findBySemNameId(Integer semNameId);
	public SemesterName findByName(String name);
    
}
