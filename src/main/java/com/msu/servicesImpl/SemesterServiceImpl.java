package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.CreateNewSemesterRequestDTO;
import com.msu.DTO.DisplayCourseAndSectionResponseDTO;
import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.DTO.PreviousSemesterDTO;
import com.msu.DTO.PreviousSemesterListDTO;
import com.msu.DTO.PreviousSemesterListRequestDTO;
import com.msu.DTO.SectionListDTO;
import com.msu.DTO.SemesterDTO;
import com.msu.Enums.SmesterEnum;
import com.msu.Enums.WeekEnum;
import com.msu.entities.CopySemesterRequestDTO;
import com.msu.entities.CourseDetails;
import com.msu.entities.CourseSemesterMapping;
import com.msu.entities.ProfessorDetails;
import com.msu.entities.Section;
import com.msu.entities.SectionSchedule;
import com.msu.entities.Semester;
import com.msu.repositories.SemesterRepository;
import com.msu.services.CourseDetailsService;
import com.msu.services.CourseSemesterMappingService;
import com.msu.services.ProfessorService;
import com.msu.services.SectionScheduleService;
import com.msu.services.SectionService;
import com.msu.services.SemesterService;
@Service("semesterService")
public class SemesterServiceImpl implements SemesterService{

	 @Autowired
	 private SemesterRepository semesterRepository;
	 
	 @Autowired 
	 private CourseSemesterMappingService courseSemesterMappingService;
	 
	 @Autowired
	 private SectionService sectionService;
	 
	 @Autowired
	 private SectionScheduleService sectionScheduleService;
	 
	 @Autowired
	 private CourseDetailsService courseDetailsService;
	 
	 @Autowired
	 private ProfessorService professorService;
	 
	 
	@Override
	public List<Semester> findAll() {
		try {
			return semesterRepository.findAll();
		}catch(Exception ex) {
			System.out.println("Exception while fetching semester "+ex);
		}
 		return null;
	}

	@Override
	public void saveSemester(Semester semesterName) {
     try {
    	 semesterRepository.save(semesterName);
		}catch(Exception ex) {
			System.out.println("Exception saving fetching semester "+ex);
		}		
	}

	@Override
	public List<GetSemesterResponseDTO> getSemesterDetails() {
		try {
			
			List<GetSemesterResponseDTO> list = new ArrayList<GetSemesterResponseDTO>();
			
			List<Semester> allSemesterDetails = semesterRepository.findAll();
			Set<String> uniqueYears = new HashSet<>();
	        for (Semester semester : allSemesterDetails) {
	        	uniqueYears.add(semester.getYear());
	        }

	        String[] uniqueYearsArray = uniqueYears.toArray(new String[uniqueYears.size()]);

	        System.out.println("Unique Years:");
	        for (String year : uniqueYearsArray) {
	           
	        	
	        	List<Semester> semesterDetailsWithYear = allSemesterDetails.stream()
                        .filter(semester -> semester.getYear().equals(year)) 
                        .collect(Collectors.toList());
	        	GetSemesterResponseDTO semesterResponse = new GetSemesterResponseDTO();
	        	semesterResponse.setYear(year);
	        	List<SemesterDTO> semesterdtoList = new ArrayList<SemesterDTO>();
	        	SemesterDTO[] semDTOArray = new SemesterDTO[4];
	        	for(Semester sem:semesterDetailsWithYear) {
	        		SemesterDTO semster = new SemesterDTO();
	        		if(sem.getSemNameId() == 1) {
	        			semster.setSemId(sem.getSemId());
	        			semster.setSemName(SmesterEnum.getSemesterName(1));
	        			semesterdtoList.add(semster);
	        			semDTOArray[0] = semster;
	        			
	        		}else if(sem.getSemNameId() == 2) {
	        			semster.setSemId(sem.getSemId());
	        			semster.setSemName(SmesterEnum.getSemesterName(2));
	        			semesterdtoList.add(semster);
	        			semDTOArray[1] = semster;
	        			
	        		}else if(sem.getSemNameId() == 3) {
	        			semster.setSemId(sem.getSemId());
	        			semster.setSemName(SmesterEnum.getSemesterName(3));
	        			semesterdtoList.add(semster);
	        			semDTOArray[2] = semster;
	        		}else if(sem.getSemNameId() == 4) {
	        			semster.setSemId(sem.getSemId());
	        			semster.setSemName(SmesterEnum.getSemesterName(4));
	        			semesterdtoList.add(semster);
	        			semDTOArray[3] = semster;
	        		}
	        		
	        			
	        	}
	        	
	        	
	        	 
	        	semesterResponse.setSemesterList(semDTOArray);
	        list.add(semesterResponse);
	        	
	        	
	        }
			return list;
			
		}catch(Exception ex) {
			System.out.println("Exception saving fetching semester response "+ex);
		}
		return null;
	}

	@Override
	public void testSaveSemester() {
		
		Semester sem = new Semester();
		
		sem.setSemNameId(1);
		sem.setYear("2025");
		semesterRepository.save(sem);
		System.out.println(sem.getSemId());
		
		
	}

	@Override
	public void copySemester(CopySemesterRequestDTO coursedetails) {
		
		try {
			System.out.println("start of copying sem");
			List<CourseSemesterMapping> oldCourseSemMappingList = courseSemesterMappingService.findBySemesterId(coursedetails.getOldSemId());
			
			for(CourseSemesterMapping oldCourseSemMapping: oldCourseSemMappingList) {
				
				CourseSemesterMapping newCourseSemMapping  = new CourseSemesterMapping();
				newCourseSemMapping.setCourseId(oldCourseSemMapping.getCourseId());
				newCourseSemMapping.setSemesterId(coursedetails.getNewSemId());
				newCourseSemMapping.setTenure(oldCourseSemMapping.getTenure());
				courseSemesterMappingService.saveCourseSemesterMapping(newCourseSemMapping);
				System.out.println("new semester id "+newCourseSemMapping.getCourseSemesterMappingId());
				List<Section> OldSectionList = sectionService.findByCourseSemesterMappingId(oldCourseSemMapping.getCourseSemesterMappingId());
				for(Section oldSection:OldSectionList){
					
					Section newSection = new Section();
					newSection.setSectionNo(oldSection.getSectionNo());
					newSection.setCapacity(oldSection.getCapacity());
					newSection.setMaxCapacity(oldSection.getCapacity());
					newSection.setProfessorId(oldSection.getProfessorId());
					newSection.setRoomId(oldSection.getRoomId());
					newSection.setCrossSectionId(oldSection.getCrossSectionId());
					newSection.setCourseSemesterMappingId(newCourseSemMapping.getCourseSemesterMappingId());
					sectionService.saveSection(newSection);
					System.out.println("get new section id "+newSection.getSectionId());
					List<SectionSchedule> oldSectionSceduleList = sectionScheduleService.findBySectionId(oldSection.getSectionId());
					
					for(SectionSchedule oldSectionSchedule: oldSectionSceduleList){
						
						SectionSchedule newSectionSchedule = new SectionSchedule();
						newSectionSchedule.setSectionId(newSection.getSectionId());
						newSectionSchedule.setWeekDay(oldSectionSchedule.getWeekDay());
						newSectionSchedule.setStartTime(oldSectionSchedule.getEndTime());
						newSectionSchedule.setEndTime(oldSectionSchedule.getEndTime());
						sectionScheduleService.saveSectionSchedule(newSectionSchedule);
						
						
					}
				}
				
			}
			System.out.println("end of copying sem");
		}catch(Exception ex){
			System.out.println("error while copying sem"+ex);
			ex.printStackTrace();
		}
		
		
	}

	@Override
	public boolean createNewSemester(CreateNewSemesterRequestDTO createnEWSmesterRequestDTO) {
		try{
			
			Semester newSemester = new Semester();
			newSemester.setSemNameId(createnEWSmesterRequestDTO.getSemNameId());
			newSemester.setYear(createnEWSmesterRequestDTO.getYear()+"");
			semesterRepository.save(newSemester);
			return true;
			
		}catch(Exception ex) {
			System.out.println("excpetion while creating new semester"+ex);
		}
		return false;
	}

	@Override
	public List<DisplayCourseAndSectionResponseDTO> fetchCourseAndSemesterDetails(Integer semId) {

		List<DisplayCourseAndSectionResponseDTO> list = new ArrayList<DisplayCourseAndSectionResponseDTO>();
		try {
			List<CourseSemesterMapping> courseSemesterMappingList = courseSemesterMappingService
					.findBySemesterId(semId);
			for (CourseSemesterMapping courseSemesterMapping : courseSemesterMappingList) {
				DisplayCourseAndSectionResponseDTO displayCourseAndSection = new DisplayCourseAndSectionResponseDTO();

				CourseDetails courseDetails = courseDetailsService
						.findCourseDetailsByCourseId(courseSemesterMapping.getCourseId());

				displayCourseAndSection.setCourseId(courseDetails.getCourseId());
				displayCourseAndSection.setCourseName(courseDetails.getCourseName());
				displayCourseAndSection.setCourseNubmer(courseDetails.getCourseNumber());
				displayCourseAndSection.setCourseSemesterMappingId(courseSemesterMapping.getCourseSemesterMappingId());

				List<Section> sectionList = sectionService
						.findByCourseSemesterMappingId(courseSemesterMapping.getCourseSemesterMappingId());
				List<SectionListDTO> sectionListDTOList = new ArrayList<SectionListDTO>();
				for (Section section : sectionList) {
					SectionListDTO sectionListDTO = new SectionListDTO();
					sectionListDTO.setSectionId(section.getSectionId());
					sectionListDTO.setSectionNo(section.getSectionNo());
					
					ProfessorDetails professorDetails = professorService.findByProfessorId(section.getProfessorId());
					sectionListDTO.setProfessorName(professorDetails.getName());
					List<SectionSchedule> sectionSceduleList = sectionScheduleService
							.findBySectionId(section.getSectionId());
					String time = "";
					for (SectionSchedule sectionSchedule : sectionSceduleList) {
						sectionSchedule.getWeekDay();
						sectionSchedule.getStartTime();
						sectionSchedule.getEndTime();
						String weekEnum = WeekEnum.getWeekEnum(sectionSchedule.getWeekDay());
						time = time + " " + weekEnum + " " + sectionSchedule.getStartTime() + "-"
								+ sectionSchedule.getEndTime();

					}
					sectionListDTO.setDayAndTime(time);
					sectionListDTOList.add(sectionListDTO);

				}
				displayCourseAndSection.setSectionList(sectionListDTOList);
				list.add(displayCourseAndSection);

			}

		} catch (Exception ex) {
			System.out.println("excpetion while fetchCourseAndSemesterDetails" + ex);
		}
		return list;
	}

	@Override
	public Semester findBySemId(Integer semId) {
		return semesterRepository.findBySemId(semId);
	}

	@Override
	public Semester findBySemNameId(Integer semNameId) {
		return semesterRepository.findBySemNameId(semNameId);
	}

	@Override
	public PreviousSemesterListDTO getPrevioussemDetails(
			PreviousSemesterListRequestDTO previousSemesterListRequestDTO) {
		PreviousSemesterListDTO previoussemList = new PreviousSemesterListDTO();
		List<PreviousSemesterDTO> list = new ArrayList<PreviousSemesterDTO>();
		List<Semester> allSemesters = semesterRepository.findAll();
		List<Semester> finalSemesterList = allSemesters.stream().filter(sem->sem.getSemId() != previousSemesterListRequestDTO.getCurrentSemId()).collect(Collectors.toList());
	      for(Semester semester:finalSemesterList) {
	    	  PreviousSemesterDTO previousSemesterDTO = new PreviousSemesterDTO();
	    	  previousSemesterDTO.setSemId(semester.getSemId());
	    	  previousSemesterDTO.setSemName(semester.getYear()+"-"+SmesterEnum.getSemesterName(semester.getSemNameId()));
	    	  list.add(previousSemesterDTO);
	      }
	      previoussemList.setPreviousSemList(list);
		return previoussemList;
	}

	

}
