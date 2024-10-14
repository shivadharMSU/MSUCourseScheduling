package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.msu.DTO.CourseDropDownForSemesterDTO;
import com.msu.DTO.SaveCourseForSemesterDTO;
import com.msu.entities.CourseDetails;
import com.msu.services.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.CourseSemesterMapping;
import com.msu.entities.Section;
import com.msu.repositories.CourseSemesterMappingRepository;
import com.msu.repositories.SectionRepository;
import com.msu.repositories.SectionScheduleRepository;
import com.msu.services.CourseSemesterMappingService;
import com.msu.services.SectionService;

@Service("courseSemesterMappingService")
public class CourseSemesterMappingServiceImpl implements CourseSemesterMappingService {

	
	@Autowired
	CourseSemesterMappingRepository customerSemesterMappingRepository;

	@Autowired
	CourseDetailsService courseDetailsService;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	SectionScheduleRepository sectionScheduleRepository;
	
	@Override
	public List<CourseSemesterMapping> findAll() {
		
	 return customerSemesterMappingRepository.findAll();
	
	}

	@Override
	public void saveCourseSemesterMapping(CourseSemesterMapping courseSemesterMapping) {
		
		customerSemesterMappingRepository.save(courseSemesterMapping);
		
	}

	@Override
	public List<CourseSemesterMapping> findBySemesterId(int semesterId) {
		return customerSemesterMappingRepository.findBySemesterId(semesterId);
	}

	@Override
	public CourseSemesterMapping findByCourseSemesterMappingId(Long courseSemesterMappingId) {
		
		return customerSemesterMappingRepository.findByCourseSemesterMappingId(courseSemesterMappingId);
	}

	@Override
	public void deleteCourseSemesterMappingByCourseIfAndSemId(Integer semId, Long courseId) {
		
		CourseSemesterMapping courseSenesterMapping = customerSemesterMappingRepository.findBySemesterIdAndCourseId(semId, courseId);
		List<Section> sectionList = sectionRepository.findByCourseSemesterMappingId(courseSenesterMapping.getCourseSemesterMappingId());
		for(Section section: sectionList) {
		sectionScheduleRepository.deleteBySectionId(section.getSectionId());
		sectionRepository.deleteBySectionId(section.getSectionId());
		}
		customerSemesterMappingRepository.deleteByCourseIdAndSemesterId(courseId,semId);
	}

	@Override
	public List<CourseDropDownForSemesterDTO> getCourseDropDownNotIncludedForSemester(Integer semId) {

		List<CourseDetails> finalCourseList = new ArrayList<CourseDetails>();
		List<CourseDetails> courseDetailsList = courseDetailsService.getcourselist();
		List<CourseSemesterMapping> courseSemesterMappingList = customerSemesterMappingRepository.findAll();
		List<CourseSemesterMapping> filteredList = courseSemesterMappingList.stream()
				.filter(s -> s.getSemesterId().equals(semId)) // Filter by specific courseId
				.toList();
		List<Long> couseIdList = new ArrayList<Long>();
		for(CourseSemesterMapping courseMapping: filteredList){
			couseIdList.add(courseMapping.getCourseId());
;		}

		for(CourseDetails courseDetails:courseDetailsList){
			if(!couseIdList.contains(courseDetails.getCourseId())){
				finalCourseList.add(courseDetails);
			}
		}
		List<CourseDropDownForSemesterDTO> ans = new ArrayList<CourseDropDownForSemesterDTO>();
		for(CourseDetails courseDetails : finalCourseList) {
			CourseDropDownForSemesterDTO courseSemDropDown = new CourseDropDownForSemesterDTO();
			courseSemDropDown.setCourseId(courseDetails.getCourseId());
			courseSemDropDown.setCourseName(courseDetails.getCourseName());
			ans.add(courseSemDropDown);
		}
	//	return courseDetailsService.;
		return ans;
	}

	@Override
	public void getSaveCourseForSemesterDTO(SaveCourseForSemesterDTO saveCourseForSemesterDTO) {

		CourseSemesterMapping courseSemesterMapping = new CourseSemesterMapping();
		courseSemesterMapping.setCourseId(saveCourseForSemesterDTO.getCourseId());
		courseSemesterMapping.setSemesterId(saveCourseForSemesterDTO.getSemId());
		courseSemesterMapping.setTenure(saveCourseForSemesterDTO.getTenure());
		customerSemesterMappingRepository.save(courseSemesterMapping);

	}
	
	


}
