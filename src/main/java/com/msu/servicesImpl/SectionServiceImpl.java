package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.Section;
import com.msu.repositories.SectionRepository;
import com.msu.services.SectionService;

@Service("SectionService")
public class SectionServiceImpl implements SectionService {

	 @Autowired
	 private SectionRepository sectionRepository;
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

}
