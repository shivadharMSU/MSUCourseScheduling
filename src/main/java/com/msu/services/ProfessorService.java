package com.msu.services;

import java.util.List;

import com.msu.entities.ProfessorDetails;

public interface ProfessorService {
	
     public List<ProfessorDetails> findAll();
     public void saveProfessorDetails(ProfessorDetails professorDetails);
	

}
