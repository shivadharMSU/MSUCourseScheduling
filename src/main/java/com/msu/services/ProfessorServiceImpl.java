package com.msu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.ProfessorDetails;
import com.msu.repositories.ProfessorDetailsRepository;



@Service("professorService")
public class ProfessorServiceImpl implements ProfessorService{
	
	@Autowired
	private ProfessorDetailsRepository	 professorRegistry;

	@Override
	public List<ProfessorDetails> findAll() {
		try {
			return professorRegistry.findAll();
			
		}catch(Exception ex) {
			System.out.println("Exception while fetching "+ex);
		}
		return null;
		
	}

	@Override
	public void saveProfessorDetails(ProfessorDetails professorDetails) {
		
		
	}


	
}
