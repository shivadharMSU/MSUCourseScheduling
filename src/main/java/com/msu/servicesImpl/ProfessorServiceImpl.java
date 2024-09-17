package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.ProfessorAvailabilityDTO;
import com.msu.DTO.SaveOrUpdateProfessorRequestDTO;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.DTO.getProfessorTypeResponseDTO;
import com.msu.Enums.WeekDaysEnum;
import com.msu.entities.ProfessorDetails;
import com.msu.entities.ProfessorType;
import com.msu.repositories.ProfessorAvailabilityRepository;
import com.msu.repositories.ProfessorDetailsRepository;
import com.msu.repositories.ProfessorTypeRepository;
import com.msu.services.ProfessorService;

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
					availabilityDTO.setProfessorId(availability.getProfessorId()); // Assuming
																					// getProfessorAvailabilityDTO has
																					// this field
					availabilityDTO.setSemNameId(availability.getSemNameId());
					availabilityDTO
							.setDayOfWeek(WeekDaysEnum.fromNumber(availability.getDayOfWeek()).getDayAbbreviation());
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
		return professorDetailsRepository.findAll();
	}

	@Override
	public List<getProfessorTypeResponseDTO> findAllProfessorType() {
		List<ProfessorType> professorTypes = professorTypeRepository.findAll();
		List<getProfessorTypeResponseDTO> dtos = new ArrayList<>();

		for (ProfessorType professorType : professorTypes) {
			getProfessorTypeResponseDTO dto = new getProfessorTypeResponseDTO();
			dto.setId(professorType.getId()); // Assuming getProfessorTypesResponseDTO has this field
			dto.setName(professorType.getType()); // Assuming getProfessorTypesResponseDTO has this field
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public ProfessorDetails findByProfessorId(Long professorId) {

		return professorDetailsRepository.findByProfessorId(professorId);
	}

	@Override
	public void saveOrUpdateProfessor(SaveOrUpdateProfessorRequestDTO saveOrUpdateProfessorRequestDTO) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void saveOrUpdateProfessor(SaveOrUpdateProfessorRequestDTO saveOrUpdateProfessorRequestDTO) {
//		// Retrieve or create a ProfessorDetails entity
//		ProfessorDetails professor = professorDetailsRepository
//				.findById(saveOrUpdateProfessorRequestDTO.getProfessorId()).orElse(new ProfessorDetails());
//
//		// Update professor details
//		professor.setProfessorId(saveOrUpdateProfessorRequestDTO.getProfessorId());
//		professor.setName(saveOrUpdateProfessorRequestDTO.getName());
//		professor.setCourseLoad(saveOrUpdateProfessorRequestDTO.getCourseLoad());
//		professor.setProfessorType(saveOrUpdateProfessorRequestDTO.getProfType());
//
//		// Check and update professor type
//		ProfessorType profType = professorTypeRepository.findByType(saveOrUpdateProfessorRequestDTO.getProfType())
//				.orElse(new ProfessorType()); // This assumes you have a method to find by type name
//		profType.setType(saveOrUpdateProfessorRequestDTO.getProfType()); // Ensure the type is set (for new types)
//		professorTypeRepository.save(profType); // Save new or updated types
//
//		// Handle availabilities
//		// First, clear existing availabilities if they are to be replaced
//		professorAvailabilityRepository.deleteByProfessorId(professor.getProfessorId());
//
//		// Then, create and add new availabilities
//		List<ProfessorAvailability> updatedAvailabilities = saveOrUpdateProfessorRequestDTO.getAvailabilities().stream()
//				.map(availabilityDTO -> {
//					ProfessorAvailability availability = new ProfessorAvailability();
//					availability.setProfessorDetails(professor);
//					availability.setDayOfWeek(availabilityDTO.getDayOfWeek());
//					availability.setStartTime(availabilityDTO.getStartTime());
//					availability.setEndTime(availabilityDTO.getEndTime());
//					return availability;
//				}).collect(Collectors.toList());
//
//		// Save the updated list of availabilities
//		professorAvailabilityRepository.saveAll(updatedAvailabilities);
//
//		// Finally, save the updated professor details
//		professorDetailsRepository.save(professor);
//	}

}
