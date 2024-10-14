package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msu.DTO.ProfessorAvailabilityDTO;
import com.msu.DTO.SaveOrUpdateProfessorRequestDTO;
import com.msu.DTO.SaveOrUpdateProfessorRequestDTO.Availability;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.DTO.getProfessorTypeResponseDTO;
import com.msu.Enums.WeekDaysEnum;
import com.msu.Enums.WeekEnum;
import com.msu.entities.ProfessorAvailability;
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

	private Map<Integer, ProfessorType> professorTypeMap;

	List<ProfessorType> professorTypes = null;

	public ProfessorServiceImpl(ProfessorTypeRepository professorTypeRepository) {
		this.professorTypeRepository = professorTypeRepository;
		professorTypes = professorTypeRepository.findAll();
		professorTypeMap = professorTypes.stream().collect(Collectors.toMap(ProfessorType::getId, pt -> pt));
	}

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
		dto.setProfStatus(professor.getProfStatus());
		ProfessorType professorType = professorTypeMap.get(professor.getProfessorType());
		if (professorType == null) {
			throw new RuntimeException("ProfessorType not found for ID: " + professor.getProfessorType());
		}
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
	public List<ProfessorDetails> findAll() {
		return professorDetailsRepository.findAll();
	}

	@Override
	public List<getProfessorTypeResponseDTO> findAllProfessorType() {
		professorTypes = professorTypeRepository.findAll();
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

	public ProfessorDetails saveProfessor(SaveOrUpdateProfessorRequestDTO saveProfessorRequestDTO) {
		ProfessorDetails newProfessor = new ProfessorDetails();
		newProfessor.setName(saveProfessorRequestDTO.getName());
		newProfessor.setCourseLoad(saveProfessorRequestDTO.getCourseLoad());
		newProfessor.setProfessorType(saveProfessorRequestDTO.getProfType());
		newProfessor.setProfStatus(saveProfessorRequestDTO.isProfStatus());

		try {
			// Save the new professor and return the saved entity
			ProfessorDetails savedProfessor = saveProfessorDetails(newProfessor);

			// Save the availability for the professor
			List<Availability> availabilities = saveProfessorRequestDTO.getAvailabilities();
			for (Availability availabilityDTO : availabilities) {
				ProfessorAvailability newProfessorAvailability = new ProfessorAvailability();
				newProfessorAvailability.setProfessorId(savedProfessor.getProfessorId());
				newProfessorAvailability.setDayOfWeek(WeekEnum.getWeekIdByWeekName(availabilityDTO.getDayOfWeek()));
				newProfessorAvailability.setStartTime(availabilityDTO.getStartTime());
				newProfessorAvailability.setEndTime(availabilityDTO.getEndTime());
				newProfessorAvailability.setSemNameId(null);

				saveProfessorAvailability(newProfessorAvailability);
			}
			return savedProfessor;

		} catch (Exception ex) {
			System.out.println("Exception while saving professor: " + ex);
			return null;
		}
	}

	@Override
	public ProfessorDetails saveProfessorDetails(ProfessorDetails professorDetails) {
		try {
			return professorDetailsRepository.save(professorDetails);
		} catch (Exception ex) {
			System.out.println("Exception while saving professorDetails: " + ex.getMessage());
			ex.printStackTrace(); // Print the stack trace for debugging purposes
			return null; // Return null if there's an exception
		}
	}

	@Override
	public void saveProfessorAvailability(ProfessorAvailability professorAvailability) {
		try {
			professorAvailabilityRepository.save(professorAvailability);
		} catch (Exception ex) {
			System.out.println("Exception while saving saveProfessorAvailability: " + ex);
		}
	}

	@Override
	public ProfessorDetails updateProfessor(Long professorId,
			SaveOrUpdateProfessorRequestDTO updateProfessorRequestDTO) {

		ProfessorDetails existingProfessor = new ProfessorDetails();
		existingProfessor.setProfessorId(professorId); // Set the ID to ensure the update happens

		// Update the professor's details from the DTO
		existingProfessor.setName(updateProfessorRequestDTO.getName());
		existingProfessor.setCourseLoad(updateProfessorRequestDTO.getCourseLoad());
		existingProfessor.setProfessorType(updateProfessorRequestDTO.getProfType());
		existingProfessor.setProfStatus(updateProfessorRequestDTO.isProfStatus());

		try {
			// Save the updated professor directly
			ProfessorDetails updatedProfessor = saveProfessorDetails(existingProfessor);
			deleteProfessorAvailabilityByProfessorId(professorId);
			// Update the availability details
			List<Availability> availabilities = updateProfessorRequestDTO.getAvailabilities();
			for (Availability availabilityDTO : availabilities) {
				ProfessorAvailability updatedProfessorAvailability = new ProfessorAvailability();
				updatedProfessorAvailability.setProfessorId(updatedProfessor.getProfessorId());
				updatedProfessorAvailability.setDayOfWeek(WeekEnum.getWeekIdByWeekName(availabilityDTO.getDayOfWeek()));
				updatedProfessorAvailability.setStartTime(availabilityDTO.getStartTime());
				updatedProfessorAvailability.setEndTime(availabilityDTO.getEndTime());
				updatedProfessorAvailability.setSemNameId(null);
				saveProfessorAvailability(updatedProfessorAvailability);
			}
		} catch (Exception ex) {
			System.out.println("Exception while updating professor: " + ex);
		}
		return existingProfessor;
	}

	private void deleteProfessorAvailabilityByProfessorId(Long professorId) {
		try {
			List<ProfessorAvailability> availabilities = professorAvailabilityRepository.findByProfessorId(professorId);
			if (!availabilities.isEmpty()) {
				professorAvailabilityRepository.deleteAll(availabilities);
			}
		} catch (Exception ex) {
			System.out.println("Exception while deleting professor availability: " + ex.getMessage());
			ex.printStackTrace(); // Print the stack trace for debugging purposes
		}
	}

	@Override
	public boolean deleteProfessor(Long professorId) {
		// Check if professor exists in the professor_details table
		if (professorDetailsRepository.existsById(professorId)) {
			// Retrieve the professor's details
			ProfessorDetails professor = professorDetailsRepository.findByProfessorId(professorId);

			if (professor != null) {
				// Toggle the professor's status
				boolean currentStatus = professor.getProfStatus(); // Assuming getProfStatus returns a boolean
				professor.setProfStatus(!currentStatus); // Toggle the status (true -> false, false -> true)

				// Save the updated status
				saveProfessorDetails(professor);

				// Optionally delete professor availabilities if needed (this part is commented
				// out)
				// List<ProfessorAvailability> availabilities =
				// professorAvailabilityRepository.findByProfessorId(professorId);
				// if (!availabilities.isEmpty()) {
				// professorAvailabilityRepository.deleteAll(availabilities); // Remove all
				// availabilities for this professor
				// }

				return true;
			}
		}
		return false; // Return false if the professor does not exist
	}

	@Override
	public ProfessorDetails saveOrUpdateProfessor(SaveOrUpdateProfessorRequestDTO saveOrUpdateProfessorRequestDTO) {
		if (saveOrUpdateProfessorRequestDTO.getProfessorId() != null) {
			// Call the update method if an ID is provided
			return updateProfessor(saveOrUpdateProfessorRequestDTO.getProfessorId(), saveOrUpdateProfessorRequestDTO);
		} else {
			// Call the save method if no ID is provided (new professor)
			return saveProfessor(saveOrUpdateProfessorRequestDTO);
		}
	}

}
