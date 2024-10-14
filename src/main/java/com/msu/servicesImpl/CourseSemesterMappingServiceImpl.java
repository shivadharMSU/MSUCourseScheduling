package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.msu.DTO.CourseDropDownForSemesterDTO;
import com.msu.entities.CourseDetails;
import com.msu.services.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.CourseSemesterMapping;
import com.msu.repositories.CourseSemesterMappingRepository;
import com.msu.services.CourseSemesterMappingService;

@Service("courseSemesterMappingService")
public class CourseSemesterMappingServiceImpl implements CourseSemesterMappingService {

	
	@Autowired
	CourseSemesterMappingRepository customerSemesterMappingRepository;

	@Autowired
	CourseDetailsService courseDetailsService;
	
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

	}

	@Override
	public CourseDropDownForSemesterDTO getCourseDropDownNotIncludedForSemester(Integer semId) {

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
	//	return courseDetailsService.;
		return null;
	}


}
