package com.msu.servicesImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.ClassDropDownListRequestDTO;
import com.msu.DTO.ClassDropDownListResponseDTO;
import com.msu.DTO.ProfessorDropDownListRequestDTO;
import com.msu.DTO.ProfessorDropDownListResponseDTO;
import com.msu.DTO.SectionScheduleSaveDTO;
import com.msu.entities.ClassRoom;
import com.msu.entities.ProfessorDetails;
import com.msu.entities.Section;
import com.msu.entities.SectionSchedule;
import com.msu.repositories.ProfessorTypeRepository;
import com.msu.repositories.SectionScheduleRepository;
import com.msu.services.ClassRomService;
import com.msu.services.ProfessorService;
import com.msu.services.SectionScheduleService;
import com.msu.services.SectionService;

@Service("SectionSchedule")
public class SectionScheduleServiceImpl implements SectionScheduleService {

	@Autowired
	SectionScheduleRepository sectionScheduleRepository;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	ClassRomService classRoomService;
	
	@Autowired
	SectionService sectionService;
	
	
	@Override
	public List<SectionSchedule> findAll() {
		
		return sectionScheduleRepository.findAll();
	}

	@Override
	public void saveSectionSchedule(SectionSchedule sectionSchedule) {
		sectionScheduleRepository.save(sectionSchedule);
		
	}

	@Override
	public List<SectionSchedule> findBySectionId(Long sectionId) {
		return sectionScheduleRepository.findBySectionId(sectionId);
		
	}

	@Override
	public SectionSchedule findBySectionScheduledId(Long sectionScheduledId) {
		
		return sectionScheduleRepository.findBySectionScheduledId(sectionScheduledId);
	}

	@Override
	public List<ProfessorDropDownListResponseDTO> professorDropDownList(
			ProfessorDropDownListRequestDTO professorDropDownListRequestDTO) {
		List<ProfessorDetails> professorDetailsList = professorService.findAll();
		List<ProfessorDropDownListResponseDTO> list = new ArrayList<ProfessorDropDownListResponseDTO>();
		for(ProfessorDetails professorDetails:professorDetailsList) {
			ProfessorDropDownListResponseDTO professorDropDownList = new ProfessorDropDownListResponseDTO();
			professorDropDownList.setProffessorId(professorDetails.getProfessorId());
			professorDropDownList.setProfessorName(professorDetails.getName());
			professorDetails.getProfessorId();
			list.add(professorDropDownList);
		}
		return list;
	}

	@Override
	public List<ClassDropDownListResponseDTO> classDropDownList(
			ClassDropDownListRequestDTO professorDropDownListRequestDTO) {
		List<ClassRoom> classRoomList = classRoomService.findAll();
		List<ClassDropDownListResponseDTO> list = new ArrayList<ClassDropDownListResponseDTO>();
		for(ClassRoom classRoom:classRoomList) {
			ClassDropDownListResponseDTO classDropDownListResponseDTO = new ClassDropDownListResponseDTO();
			classDropDownListResponseDTO.setId(classRoom.getRoomId());
			classDropDownListResponseDTO.setRoomName(classRoom.getRoomName());
			list.add(classDropDownListResponseDTO);
		}
		return list;
	}

	@Override
	public void saveSectionSchedule(SectionScheduleSaveDTO sectionScheduleSaveDTO) {
		
		try {
			
			Section section = new Section();
			section.setSectionNo(sectionScheduleSaveDTO.getSectionNo());
			section.setProfessor_id(sectionScheduleSaveDTO.getProfessorId());
			section.setRoom_id(sectionScheduleSaveDTO.getRoomId());
			section.setCourseSemesterMappingId(sectionScheduleSaveDTO.getCourseSemesterMappingId());
			section.setCrossSectionId(sectionScheduleSaveDTO.getCrossSectionId());
			section.setCourseSemesterMappingId(sectionScheduleSaveDTO.getCourseSemesterMappingId());
			section.setCapacity(sectionScheduleSaveDTO.getCapacity());
			section.setMaxCapacity(sectionScheduleSaveDTO.getMaxCapacity());
			sectionService.saveSection(section);
			SectionSchedule sectionSchedule = new SectionSchedule();
			sectionSchedule.setSectionId(section.getSectionId());
			sectionSchedule.setWeekDay(sectionScheduleSaveDTO.getWeekId());
			 DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("hh:mm a");
		     LocalTime startTime = LocalTime.parse(sectionScheduleSaveDTO.getStartTime(), formatter12Hour);
			sectionSchedule.setStartTime(startTime);
			sectionSchedule.setEndTime(startTime);
			
			saveSectionSchedule(sectionSchedule);
			
			
		}catch(Exception ex) {
			System.out.print("exception while saveSectionSchedule "+ex);
		}
		
	}
	
	public static void main(String[] args) {

		
		// Example time string in 12-hour format
        String timeString = "09:30 PM";

        // Define formatter for 12-hour time pattern
        DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("hh:mm a");

        // Parse the string to LocalTime object
        LocalTime localTime = LocalTime.parse(timeString, formatter12Hour);

        // Format LocalTime to 24-hour format string
        String time24HourFormat = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(time24HourFormat);
	}
	
	
	

}
