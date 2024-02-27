package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.Semester;
import com.msu.repositories.SemesterRepository;
import com.msu.services.SemesterService;
@Service("semesterService")
public class SemesterServiceImpl implements SemesterService{

	 @Autowired
	 private SemesterRepository semesterRepository;
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

}
