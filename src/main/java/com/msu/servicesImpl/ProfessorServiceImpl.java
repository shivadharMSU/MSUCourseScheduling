package com.msu.servicesImpl;

import com.msu.DTO.ProfessorAvailabilityDTO;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.entities.ProfessorDetails;
import com.msu.entities.ProfessorType;
import com.msu.repositories.ProfessorAvailabilityRepository;
import com.msu.repositories.ProfessorDetailsRepository;
import com.msu.repositories.ProfessorTypeRepository;
import com.msu.services.ProfessorService;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("professorService")
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDetailsRepository professorDetailsRepository;

	@Autowired
	private ProfessorAvailabilityRepository professorAvailabilityRepository;

	@Autowired
	private ProfessorTypeRepository professorTypeRepository;
	
	


	@Override
	public List<getProfessorResponseDTO> findAllProfessorsWithDetails() {
		List<ProfessorDetails> professors = professorDetailsRepository.findAll();
		return professors.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private getProfessorResponseDTO convertToDTO(ProfessorDetails professor) {
		getProfessorResponseDTO dto = new getProfessorResponseDTO();
		dto.setProfessorId(professor.getProfessorId());
		dto.setName(professor.getName());
		dto.setCourseLoad(professor.getCourseLoad());

		// Fetch the descriptive name for the professorType
		ProfessorType professorType = professorTypeRepository.findById(professor.getProfessorType())
				.orElse(new ProfessorType()); // Consider handling cases where professorType is not found more
												// gracefully
		dto.setProfessorTypeName(professorType.getType());

		// Assuming availabilities need to be fetched and converted to DTOs
		List<ProfessorAvailabilityDTO> availabilityDTOs = professorAvailabilityRepository
				.findByProfessorId(professor.getProfessorId()).stream().map(availability -> {
					ProfessorAvailabilityDTO availabilityDTO = new ProfessorAvailabilityDTO();
					// Set properties of availabilityDTO from availability
					availabilityDTO.setId(availability.getId());
					// Omitting setProfessorId to avoid redundancy, assuming it's always the same as
					// the parent professor's ID
					availabilityDTO.setSemNameId(availability.getSemNameId());
					availabilityDTO.setDayOfWeek(availability.getDayOfWeek());
					availabilityDTO.setStartTime(availability.getStartTime());
					availabilityDTO.setEndTime(availability.getEndTime());
					return availabilityDTO;
				}).collect(Collectors.toList());

		dto.setAvailabilities(availabilityDTOs);
		return dto;
	}

	@Override
	public void saveProfessorDetails(ProfessorDetails professorDetails) {
		try {
			professorDetailsRepository.save(professorDetails);
		} catch (Exception ex) {
			System.out.println("Exception while saving professorDetails: " + ex);
		}
	}

	@Override
	public List<ProfessorDetails> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}