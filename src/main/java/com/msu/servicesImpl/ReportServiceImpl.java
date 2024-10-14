package com.msu.servicesImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msu.DTO.Occupency;
import com.msu.DTO.Schedule;
import com.msu.entities.*;
import com.msu.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.ProfessorReportSchedule;
import com.msu.DTO.ProfessorReportScheduleList;
import com.msu.Enums.WeekEnum;
import com.msu.repositories.ProfessorAvailabilityRepository;

@Service("ReportServiceImpl")
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ProfessorService professorService;
	
	
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private SectionScheduleService sectionScheduleService;
	
	@Autowired
	private CourseSemesterMappingService courseSemesterMappingService;

	@Autowired
	private CourseDetailsService courseDetailsService;

	@Override
	public ProfessorReportScheduleList getProfessorReportScheduleListService(Integer semId) {
		
		ProfessorReportScheduleList professorScheduleList = new ProfessorReportScheduleList();
		
		List<ProfessorDetails> professorList = professorService.findAll();
		List<Section> sectionList = sectionService.findAll();
		List<CourseSemesterMapping> courseSemesterMappingList = courseSemesterMappingService.findBySemesterId(semId);
		
		List<ProfessorReportSchedule> professorReportScheduleList  = new ArrayList<ProfessorReportSchedule>();
		for(ProfessorDetails profDetails:professorList) {
			ProfessorReportSchedule profReportSedule = new ProfessorReportSchedule();
			profReportSedule.setId(profDetails.getProfessorId());
			profReportSedule.setName(profDetails.getName());
			Map<String,List<String>> map = new HashMap<String,List<String>>();
			for(CourseSemesterMapping courseSemesterMapping: courseSemesterMappingList) {
				List<Section> sectionbyProfessorAndSemester = sectionService.findByProfessorIdAndCourseSemesterMappingId(profDetails.getProfessorId(), courseSemesterMapping.getCourseSemesterMappingId());
				for(Section section : sectionbyProfessorAndSemester) {
					List<SectionSchedule> sectionScedule = sectionScheduleService.findBySectionId(section.getSectionId());
					for(SectionSchedule sectionSection: sectionScedule) {
						String weekValue = WeekEnum.getWeekByValue(sectionSection.getWeekDay());
						CourseDetails courseDetails = courseDetailsService.findCourseDetailsByCourseId(courseSemesterMapping.getCourseId());
						LocalTime time = sectionSection.getStartTime().now(); // Example LocalTime
				        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
				        
				        String formattedStartedTime = time.format(formatter);
				        
				        
				        LocalTime endtime = sectionSection.getEndTime().now(); // Example LocalTime
				        DateTimeFormatter endTimeformatter = DateTimeFormatter.ofPattern("hh:mm a");
				        
				        String endFormattedStartedTime = endtime.format(endTimeformatter);
						map.computeIfAbsent(weekValue, k -> new ArrayList<>()).add(courseDetails.getCourseName()+"-"+formattedStartedTime+"-"+endFormattedStartedTime);
					}
					
				}
			}

			Schedule schedule = new Schedule();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				String key = entry.getKey();
				List<String> values = entry.getValue();
                    System.out.println(WeekEnum.getWeekByValue(1));
				if(key.equals(WeekEnum.getWeekByValue(1))){
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setMon(occupency);
				}
				if(key.equals(WeekEnum.getWeekByValue(2))){
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setTues(occupency);
				}
				if(key.equals(WeekEnum.getWeekByValue(3))){
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setWed(occupency);
				}
				if(key.equals(WeekEnum.getWeekByValue(4))){
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setThurs(occupency);
				}
				if(key.equals(WeekEnum.getWeekByValue(5))){
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setFri(occupency);
				}

			}
			profReportSedule.setSchedule(schedule);

			professorReportScheduleList.add(profReportSedule);
		}
		// get all professorList
		
		professorScheduleList.setProfessorReportSchedule(professorReportScheduleList);
		
		return professorScheduleList;
	}
	
	

}
