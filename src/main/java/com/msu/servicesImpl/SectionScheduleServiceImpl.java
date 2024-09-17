package com.msu.servicesImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.ClassDropDownListRequestDTO;
import com.msu.DTO.ClassDropDownListResponseDTO;
import com.msu.DTO.ConflictDTO;
import com.msu.DTO.ProfessorDropDownListRequestDTO;
import com.msu.DTO.ProfessorDropDownListResponseDTO;
import com.msu.DTO.SectionScheduleSaveDTO;
import com.msu.DTO.SuggestionDTO;
import com.msu.DTO.SuggestionRequestDTO;
import com.msu.DTO.SuggestionsResponseDTO;
import com.msu.DTO.TimeSlotsDTO;
import com.msu.Enums.WeekEnum;
import com.msu.entities.ClassRoom;
import com.msu.entities.CourseDetails;
import com.msu.entities.CourseProfessorMapping;
import com.msu.entities.CourseSchedule;
import com.msu.entities.CourseSemesterMapping;
import com.msu.entities.ProfessorDetails;
import com.msu.entities.Section;
import com.msu.entities.SectionSchedule;
import com.msu.repositories.ProfessorTypeRepository;
import com.msu.repositories.SectionScheduleRepository;
import com.msu.services.ClassRomService;
import com.msu.services.CourseDetailsService;
import com.msu.services.CourseProfessorMappingService;
import com.msu.services.CourseSemesterMappingService;
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
	
	@Autowired
	CourseDetailsService coursedetailsService;
	
	@Autowired
	CourseProfessorMappingService courseProfessorMappingService;
	@Autowired
	CourseSemesterMappingService  courseSemesterMapping;
	
	
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
		List<CourseProfessorMapping> courseProfessorMappingByCourseList = courseProfessorMappingService.findCourseProfessorMappingByCourse(professorDropDownListRequestDTO.getCourseId());
		List<Long> deleteProfList = new ArrayList<Long>();
		for(CourseProfessorMapping courseProfessorMapping:courseProfessorMappingByCourseList) {
			ProfessorDetails professor = professorService.findByProfessorId(courseProfessorMapping.getProfessor());
			ProfessorDropDownListResponseDTO professorDropDownList = new ProfessorDropDownListResponseDTO();
			professorDropDownList.setProffessorId(professor.getProfessorId());
			professorDropDownList.setProfessorName(professor.getName()+" (Suggestted)");
			list.add(professorDropDownList);
			deleteProfList.add(professor.getProfessorId());
		}
		for(ProfessorDetails professorDetails:professorDetailsList) {
			if(!deleteProfList.contains(professorDetails.getProfessorId())) {
				ProfessorDropDownListResponseDTO professorDropDownList = new ProfessorDropDownListResponseDTO();
			professorDropDownList.setProffessorId(professorDetails.getProfessorId());
			professorDropDownList.setProfessorName(professorDetails.getName());
			professorDetails.getProfessorId();
			list.add(professorDropDownList);
			}
			
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
			section.setProfessorId(sectionScheduleSaveDTO.getProfessorId());
			section.setRoomId(sectionScheduleSaveDTO.getRoomId());
			section.setCourseSemesterMappingId(sectionScheduleSaveDTO.getCourseSemesterMappingId());
			section.setCrossSectionId(sectionScheduleSaveDTO.getCrossSectionId());
			section.setCourseSemesterMappingId(sectionScheduleSaveDTO.getCourseSemesterMappingId());
			section.setCapacity(sectionScheduleSaveDTO.getCapacity());
			section.setMaxCapacity(sectionScheduleSaveDTO.getMaxCapacity());
			sectionService.saveSection(section);
			
			TimeSlotsDTO[] timeSlots = sectionScheduleSaveDTO.getTimeSlots();
			for(TimeSlotsDTO timeSlot:timeSlots) {
				for(String day:timeSlot.getDays()) {
					SectionSchedule sectionSchedule = new SectionSchedule();
					sectionSchedule.setSectionId(section.getSectionId());
					
					sectionSchedule.setWeekDay(WeekEnum.getWeekIdByWeekName(day));
					sectionSchedule.setStartTime(timeSlot.getStartTime());
					sectionSchedule.setEndTime(timeSlot.getEndTime());
					
					saveSectionSchedule(sectionSchedule);
				}
				
			}
			
			
			
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

	@Override
	public SuggestionsResponseDTO getSuggestions(SuggestionRequestDTO suggestionsRequestDTO) {
		
 		SuggestionsResponseDTO suggestionsRequest = new SuggestionsResponseDTO();
		ConflictDTO conflictDTO = new ConflictDTO();
		
		List<SectionSchedule> fetchProfessorConflctsData = fetchProfessorConflctsData(suggestionsRequestDTO);
		List<SectionSchedule> fetchCourseConflctsData = fetchCourseConflctsData(suggestionsRequestDTO);
		List<SectionSchedule> fetchRoomConflctsData = fetchRoomConflctsData(suggestionsRequestDTO);
		 List<String> professorConflictList = new ArrayList<>();
		 
		 if(suggestionsRequestDTO.getProfessorId() != null) {
		 if(!fetchProfessorConflctsData.isEmpty() && fetchProfessorConflctsData.size()>0) {
		     for( SectionSchedule sectionScheduleForProfessor:fetchProfessorConflctsData) {
		    	 
		    	 Section section = sectionService.findBySectionId(sectionScheduleForProfessor.getSectionId());
		    	 ProfessorDetails professor = professorService.findByProfessorId(section.getProfessorId());
		    	 String professorName = professor.getName();
		    	 String weekDay = WeekEnum.getWeekEnum(sectionScheduleForProfessor.getWeekDay());;
		    	 String startTime = sectionScheduleForProfessor.getStartTime().toString();
		    	 String endTime = sectionScheduleForProfessor.getEndTime().toString();
		    	 String sectionNo = section.getSectionNo(); 
		    	 CourseSemesterMapping byCourseSemesterMappingId = courseSemesterMapping.findByCourseSemesterMappingId(section.getCourseSemesterMappingId());
		    	 CourseDetails courseDetails = coursedetailsService.findCourseDetailsByCourseId(byCourseSemesterMappingId.getCourseId());
		    	 String courseName = courseDetails.getCourseName();
		    	 
		    	 String finalAnswer = "professor "+professorName+" is occupied  in "+weekDay+" from "+startTime+" to "+endTime+" with "+courseName+" section no  "+sectionNo;
		    	 
		    	 professorConflictList.add(finalAnswer);
		     }
	}
		 }
		 
		     List<String> courseConflictList = new ArrayList<>();
		     if(suggestionsRequestDTO.getTimeSlots() != null && suggestionsRequestDTO.getTimeSlots().length>0) {
		     if(fetchCourseConflctsData!= null && !fetchCourseConflctsData.isEmpty() && fetchCourseConflctsData.size()>0) {
		     for( SectionSchedule sectionScheduleForCourse:fetchCourseConflctsData) {
		    	 
		    	 
		    	 Section section = sectionService.findBySectionId(sectionScheduleForCourse.getSectionId());
		    	 String weekDay = WeekEnum.getWeekEnum(sectionScheduleForCourse.getWeekDay());;
		    	 String startTime = sectionScheduleForCourse.getStartTime().toString();
		    	 String endTime = sectionScheduleForCourse.getEndTime().toString();
		    	 String sectionNo = section.getSectionNo(); 
		    	 
		    
              String finalAnswer = sectionNo+" is scheduled on "+weekDay+" from "+startTime+" to "+endTime;
		    	 
              courseConflictList.add(finalAnswer);
		     }
		     }
	}
           
		     List<String> roomConflictList = new ArrayList<>();
		     if(suggestionsRequestDTO.getRoomId() != null) {
		     if(fetchRoomConflctsData!= null && !fetchRoomConflctsData.isEmpty() && fetchRoomConflctsData.size() >0) {
		     for( SectionSchedule sectionScheduleForRoom:fetchRoomConflctsData) {
		    	 
		    	 Section section = sectionService.findBySectionId(sectionScheduleForRoom.getSectionId());
		    	 String weekDay = WeekEnum.getWeekEnum(sectionScheduleForRoom.getWeekDay());;
		    	 String startTime = sectionScheduleForRoom.getStartTime().toString();
		    	 String endTime = sectionScheduleForRoom.getEndTime().toString();
		    	 CourseSemesterMapping byCourseSemesterMappingId = courseSemesterMapping.findByCourseSemesterMappingId(section.getCourseSemesterMappingId());
		    	 CourseDetails courseDetails = coursedetailsService.findCourseDetailsByCourseId(byCourseSemesterMappingId.getCourseId());
		    	 String courseName = courseDetails.getCourseName();
		    	 
		    	ClassRoom room = classRoomService.findByRoomId(section.getRoomId());
		    	
		    
	              String finalAnswer = room.getRoomName()+" is occupied on "+weekDay+" from "+startTime+" to "+endTime+" for course "+courseName;
		    	 roomConflictList.add(finalAnswer);
	 
             }
		     }
	}
		     
		     
		     
		conflictDTO.setProfessorCnflict(professorConflictList.toString());
		conflictDTO.setCourseConflict(courseConflictList.toString());
		conflictDTO.setClassRoomConflcit(roomConflictList.toString());
		suggestionsRequest.setConflictDTO(conflictDTO);
		SuggestionDTO suggestion = new SuggestionDTO();
		suggestion.setSuggestions("suggestions here");
		
		
		return suggestionsRequest;
	}

	@Override
	public List<SectionSchedule> fetchProfessorConflctsData(SuggestionRequestDTO suggestionsRequest) {
		 TimeSlotsDTO[] timeSlots = suggestionsRequest.getTimeSlots();
		 
		 
		 
		 for(TimeSlotsDTO timeSlotsDTO: timeSlots) {
			   
				 List<CourseSemesterMapping> courseSemesterMappingList = courseSemesterMapping.findAll();
				 List<CourseSemesterMapping> filteredCourseSemesterMappingList = courseSemesterMappingList.stream()
				 .filter(csm->csm.getSemesterId().equals(suggestionsRequest.getSemId()))
				 .collect(Collectors.toList());
				 
				 for(CourseSemesterMapping courseSemesterMapping:filteredCourseSemesterMappingList) {
			
					 List<Section> filterSections = sectionService.findAll().stream()
					 .filter(section -> section.getProfessorId() == suggestionsRequest.getProfessorId())
					 .filter(section -> section.getCourseSemesterMappingId() == courseSemesterMapping.getCourseSemesterMappingId())
					    .collect(Collectors.toList());
					 if(!filterSections.isEmpty()) {
						 List<SectionSchedule> finalList = new ArrayList<SectionSchedule>();
						 for(Section section : filterSections){
						List<SectionSchedule> sectionSecheduleList =  sectionScheduleRepository.findAll().stream().filter(sr->{
							 if(sr.getSectionId().equals(section.getSectionId())) {
								  
								 for(String day:timeSlotsDTO.getDays()) {
									 if(day.equalsIgnoreCase(WeekEnum.getWeekByValue(sr.getWeekDay()))) {
									
										// Check if the start time of timeSlotsDTO is equal to sr's start time
										// or if timeSlotsDTO's start time is between sr's start and end time
										boolean isStartTimeInRange = (timeSlotsDTO.getStartTime().equals(sr.getStartTime()) ||
										    (timeSlotsDTO.getStartTime().isAfter(sr.getStartTime()) && timeSlotsDTO.getStartTime().isBefore(sr.getEndTime())));

										// Check if the end time of timeSlotsDTO is equal to sr's start time
										// or if timeSlotsDTO's end time is between sr's start and end time
										boolean isEndTimeInRange = (timeSlotsDTO.getEndTime().equals(sr.getEndTime()) ||
										    (timeSlotsDTO.getEndTime().isAfter(sr.getStartTime()) && timeSlotsDTO.getEndTime().isBefore(sr.getEndTime())));

										// If both start and end times of timeSlotsDTO are within sr's time range, return true
										return isStartTimeInRange && isEndTimeInRange;

								 }
								 }
								 
								 
							 }
							 return false;
						 }).collect(Collectors.toList());
						finalList.addAll(sectionSecheduleList);
					 }
						return finalList;
						
						
						 
					 }
					 
					 
				 }
		 
		 }
		
		return null;
	}



	@Override
	public List<SectionSchedule> fetchCourseConflctsData(SuggestionRequestDTO suggestionsRequest) {
		TimeSlotsDTO[] timeSlots = suggestionsRequest.getTimeSlots();
		 
		 
		 
		 for(TimeSlotsDTO timeSlotsDTO: timeSlots) {
			   
				 List<CourseSemesterMapping> courseSemesterMappingList = courseSemesterMapping.findAll();
				 List<CourseSemesterMapping> filteredCourseSemesterMappingList = courseSemesterMappingList.stream()
				 .filter(csm->csm.getSemesterId().equals(suggestionsRequest.getSemId()))
				 .filter(csm->csm.getCourseId().equals(suggestionsRequest.getCourseId()))
				 .collect(Collectors.toList());
				 
				 if(!filteredCourseSemesterMappingList.isEmpty()) {
					 CourseSemesterMapping courseSemesterMapping = filteredCourseSemesterMappingList.get(0);
					 List<Section> filterSections = sectionService.findAll().stream()
					 .filter(section -> section.getCourseSemesterMappingId() == courseSemesterMapping.getCourseSemesterMappingId())
					    .collect(Collectors.toList());
					 if(!filterSections.isEmpty()) {
						 Section section = filterSections.get(0);
						List<SectionSchedule> sectionSecheduleList =  sectionScheduleRepository.findAll().stream().filter(sr->{
							 if(sr.getSectionId().equals(section.getSectionId())) {
								  
								 for(String day:timeSlotsDTO.getDays()) {
									 if(day.equalsIgnoreCase(WeekEnum.getWeekByValue(sr.getWeekDay()))) {
										// Check if the start time of timeSlotsDTO is equal to sr's start time
										// or if timeSlotsDTO's start time is between sr's start and end time
										boolean isStartTimeInRange = (timeSlotsDTO.getStartTime().equals(sr.getStartTime()) ||
										    (timeSlotsDTO.getStartTime().isAfter(sr.getStartTime()) && timeSlotsDTO.getStartTime().isBefore(sr.getEndTime())));

										// Check if the end time of timeSlotsDTO is equal to sr's start time
										// or if timeSlotsDTO's end time is between sr's start and end time
										boolean isEndTimeInRange = (timeSlotsDTO.getEndTime().equals(sr.getEndTime()) ||
										    (timeSlotsDTO.getEndTime().isAfter(sr.getStartTime()) && timeSlotsDTO.getEndTime().isBefore(sr.getEndTime())));

										// If both start and end times of timeSlotsDTO are within sr's time range, return true
										return isStartTimeInRange && isEndTimeInRange;

								 }
								 }
								 
								 
							 }
							 return false;
						 }).collect(Collectors.toList());
						
						if(!sectionSecheduleList.isEmpty()) {
							return sectionSecheduleList;
						}
						 
					 }
					 
				 }
		 
		 }
		
		return null;
	}

	@Override
	public List<SectionSchedule> fetchRoomConflctsData(SuggestionRequestDTO suggestionsRequest) {
		TimeSlotsDTO[] timeSlots = suggestionsRequest.getTimeSlots();
		 
		 
		 
		 for(TimeSlotsDTO timeSlotsDTO: timeSlots) {
			   
				 List<CourseSemesterMapping> courseSemesterMappingList = courseSemesterMapping.findAll();
				 List<CourseSemesterMapping> filteredCourseSemesterMappingList = courseSemesterMappingList.stream()
				 .filter(csm->csm.getSemesterId().equals(suggestionsRequest.getSemId()))
				 .collect(Collectors.toList());
				 
				 if(!filteredCourseSemesterMappingList.isEmpty()) {
					 ClassRoom roomByRoomId = classRoomService.findByRoomId(suggestionsRequest.getRoomId());
					 for(CourseSemesterMapping courseSemesterMapping:filteredCourseSemesterMappingList) {
					 
					 List<Section> filterSections = sectionService.findAll().stream()
					 .filter(section -> section.getRoomId() == roomByRoomId.getRoomId())
					 .filter(section -> section.getCourseSemesterMappingId() == courseSemesterMapping.getCourseSemesterMappingId())
					    .collect(Collectors.toList());
					 if(!filterSections.isEmpty()) {
						 Section section = filterSections.get(0);
						List<SectionSchedule> sectionSecheduleList =  sectionScheduleRepository.findAll().stream().filter(sr->{
							 if(sr.getSectionId().equals(section.getSectionId())) {
								  
								 for(String day:timeSlotsDTO.getDays()) {
									 if(day.equalsIgnoreCase(WeekEnum.getWeekByValue(sr.getWeekDay()))) {
										// Check if the start time of timeSlotsDTO is equal to sr's start time
										// or if timeSlotsDTO's start time is between sr's start and end time
										boolean isStartTimeInRange = (timeSlotsDTO.getStartTime().equals(sr.getStartTime()) ||
										    (timeSlotsDTO.getStartTime().isAfter(sr.getStartTime()) && timeSlotsDTO.getStartTime().isBefore(sr.getEndTime())));

										// Check if the end time of timeSlotsDTO is equal to sr's start time
										// or if timeSlotsDTO's end time is between sr's start and end time
										boolean isEndTimeInRange = (timeSlotsDTO.getEndTime().equals(sr.getEndTime()) ||
										    (timeSlotsDTO.getEndTime().isAfter(sr.getStartTime()) && timeSlotsDTO.getEndTime().isBefore(sr.getEndTime())));

										// If both start and end times of timeSlotsDTO are within sr's time range, return true
										return isStartTimeInRange && isEndTimeInRange;

								 }
								 }
								 
								 
							 }
							 return false;
						 }).collect(Collectors.toList());
						
						if(!sectionSecheduleList.isEmpty()) {
							return sectionSecheduleList;
						}
						 
					 }
				 }
					 
				 }
		 
		 }
		
		return null;
	}
	
	
	
	
	

}
