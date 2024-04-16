package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.CourseSemesterMapping;
import com.msu.repositories.CourseSemesterMappingRepository;
import com.msu.services.CourseSemesterMappingService;

@Service("courseSemesterMappingService")
public class CourseSemesterMappingServiceImpl implements CourseSemesterMappingService {

	
	@Autowired
	CourseSemesterMappingRepository customerSemesterMappingRepository;
	
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
	
	

}
