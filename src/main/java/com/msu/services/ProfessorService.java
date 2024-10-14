package com.msu.services;

import java.util.List;

import com.msu.DTO.SaveOrUpdateProfessorRequestDTO;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.DTO.getProfessorTypeResponseDTO;
import com.msu.entities.ProfessorAvailability;
import com.msu.entities.ProfessorDetails;

public interface ProfessorService {

	public List<ProfessorDetails> findAll();

	public List<getProfessorResponseDTO> findAllProfessorsWithDetails();

	public List<getProfessorTypeResponseDTO> findAllProfessorType();

	public ProfessorDetails findByProfessorId(Long professorId);

	public boolean deleteProfessor(Long professorId);

	ProfessorDetails updateProfessor(Long professorId, SaveOrUpdateProfessorRequestDTO updateProfessorRequestDTO);

	ProfessorDetails saveProfessor(SaveOrUpdateProfessorRequestDTO saveProfessorRequestDTO);

	public ProfessorDetails saveOrUpdateProfessor(SaveOrUpdateProfessorRequestDTO saveOrUpdateProfessorRequestDTO);

	void saveProfessorAvailability(ProfessorAvailability professorAvailability);

	ProfessorDetails saveProfessorDetails(ProfessorDetails professorDetails);
}
