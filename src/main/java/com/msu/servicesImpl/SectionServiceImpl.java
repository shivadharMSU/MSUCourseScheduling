package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.CrossSectionDropDown;
import com.msu.DTO.CrossSectionDropDownList;
import com.msu.entities.CourseDetails;
import com.msu.entities.CourseSemesterMapping;
import com.msu.entities.Section;
import com.msu.repositories.CourseSemesterMappingRepository;
import com.msu.repositories.SectionRepository;
import com.msu.services.CourseDetailsService;
import com.msu.services.SectionService;

@Service("SectionService")
public class SectionServiceImpl implements SectionService {

	 @Autowired
	 private SectionRepository sectionRepository;
	 @Autowired
	 private CourseSemesterMappingRepository courseSemesterMappingRepository;
	 @Autowired
	 private CourseDetailsService courseDetailsService;
	 
	@Override
	public List<Section> findAll() {
		try {
			return sectionRepository.findAll();
		}catch(Exception ex) {
		System.out.println("exception while fetching Section "+ex);
		}
		return null;
	}

	@Override
	public void saveSection(Section section) {
		try {
			sectionRepository.save(section);
		}catch(Exception ex) {
			System.out.println("exception while saving Section "+ex);
		}
		
		
	}

	@Override
	public List<Section> findByCourseSemesterMappingId(Long courseSemesterMappingId) {
		try {
			return sectionRepository.findByCourseSemesterMappingId(courseSemesterMappingId);

		}catch(Exception ex) {
			System.out.println("exception while findByCourseSemesterMappingId "+ex);
		}
		return null;
	}

	@Override
	public Section findBySectionId(Long sectionId) {
		return sectionRepository.findBySectionId(sectionId);
		
	}

	@Override
	public List<Section> findByProfessorIdAndCourseSemesterMappingId(Long professorId, Long courseSemesterMappingId) {
		// TODO Auto-generated method stub
		return sectionRepository.findByProfessorIdAndCourseSemesterMappingId(professorId, courseSemesterMappingId);
	}

	@Override
	public void deleteSectionBySection(Long sectionId) {
		sectionRepository.deleteBySectionId(sectionId);
	}


	@Override
	public CrossSectionDropDownList getCrossSectionList(Integer semId, Long courseId) {
		
		CourseSemesterMapping courseSemesterMapping = courseSemesterMappingRepository.findBySemesterIdAndCourseId(semId, courseId);
	   CrossSectionDropDownList ans = new CrossSectionDropDownList();
	   List<CrossSectionDropDown> list = new ArrayList<CrossSectionDropDown>();
	  
		
		List<Section> sectionsList = findAll().stream()
				.filter(section -> section.getCourseSemesterMappingId() != courseSemesterMapping.getCourseSemesterMappingId())
				.collect(Collectors.toList());
		
		for(Section section: sectionsList) {
			 CrossSectionDropDown crossSectionDropDown = new CrossSectionDropDown();
			 crossSectionDropDown.setId(section.getSectionId());
			 
			 CourseSemesterMapping courseSemesterMappingObj = courseSemesterMappingRepository.findByCourseSemesterMappingId(section.getCourseSemesterMappingId());
			 CourseDetails courseDetail = courseDetailsService.findCourseDetailsByCourseId(courseSemesterMappingObj.getCourseId());
			 crossSectionDropDown.setSectionName(courseDetail.getCourseName()+" - "+section.getSectionNo());
			 list.add(crossSectionDropDown);
		}
		
		ans.setCorsssSectionDropDownList(list);
			return ans;
	}


}
