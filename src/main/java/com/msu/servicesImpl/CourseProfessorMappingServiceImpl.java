package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.CourseProfessorMapping;
import com.msu.repositories.CourseProfessorMappingRepository;
import com.msu.services.CourseProfessorMappingService;

@Service("courseProfessorMapping")
public class CourseProfessorMappingServiceImpl implements CourseProfessorMappingService {

	@Autowired
	private CourseProfessorMappingRepository courseProfessorMappingRepository;
	
	@Override
	public List<CourseProfessorMapping> findAll() {
		try {
			return courseProfessorMappingRepository.findAll();
		}catch(Exception ex) {
			System.out.println("error while fetaching CourseProfessorMapping");
		}
		
		return null;
	}

	@Override
	public void saveCourseProfessorMapping(CourseProfessorMapping courseProfessorMapping) {
		try {
			courseProfessorMappingRepository.save(courseProfessorMapping);
		}catch(Exception ex) {
			System.out.println("error while saving CourseProfessorMapping");

		}
		
	}

}
