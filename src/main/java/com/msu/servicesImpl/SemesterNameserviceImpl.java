package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.SemesterName;
import com.msu.repositories.SemesterNameRepository;
import com.msu.services.SemesterNameService;

@Service("semesterNameservice")
public class SemesterNameserviceImpl implements SemesterNameService{

	@Autowired
	private SemesterNameRepository semesterNameRepository;
	@Override
	public List<SemesterName> findAll() {
		try {
			return semesterNameRepository.findAll();
		}catch(Exception ex) {
			System.out.println("exception while fetching semester name"+ex);
		}
		return null;
	}

	@Override
	public void saveSemesterName(SemesterName semesterName) {
		try {
			semesterNameRepository.save(semesterName);
		}catch(Exception ex) {
			System.out.println("exception while saving "+ex);
		}
		
		
	}

	@Override
	public SemesterName findBySemNameId(Integer semNameId) {
		return semesterNameRepository.findBySemNameId(semNameId);
	}

	@Override
	public SemesterName findByName(String name) {
		return semesterNameRepository.findByName(name);
	}
	
	

}
