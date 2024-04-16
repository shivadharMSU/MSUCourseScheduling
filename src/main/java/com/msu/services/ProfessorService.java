package com.msu.services;

import java.util.List;

import com.msu.DTO.SaveOrUpdateProfessorRequestDTO;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.DTO.getProfessorTypeResponseDTO;
import com.msu.entities.ProfessorDetails;

public interface ProfessorService {

	public List<ProfessorDetails> findAll();

	public List<getProfessorResponseDTO> findAllProfessorsWithDetails();

	public void saveProfessorDetails(ProfessorDetails professorDetails);

	public List<getProfessorTypeResponseDTO> findAllProfessorType();
	
	public ProfessorDetails findByProfessorId(Long professorId);

	public void saveOrUpdateProfessor(SaveOrUpdateProfessorRequestDTO saveOrUpdateProfessorRequestDTO);

}
