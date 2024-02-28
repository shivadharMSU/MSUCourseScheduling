package com.msu.services;

import java.util.List;

import com.msu.DTO.getProfessorResponseDTO;
import com.msu.entities.ProfessorDetails;

public interface ProfessorService {
	
     public List<ProfessorDetails> findAll();
     public List<getProfessorResponseDTO> findAllProfessorsWithDetails();
     public void saveProfessorDetails(ProfessorDetails professorDetails);
	

}
