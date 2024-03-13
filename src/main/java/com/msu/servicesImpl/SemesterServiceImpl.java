package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.DTO.SemesterDTO;
import com.msu.Enums.SmesterEnum;
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

}
