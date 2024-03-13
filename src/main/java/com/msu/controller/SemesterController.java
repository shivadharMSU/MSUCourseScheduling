package com.msu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.services.SemesterService;

@Controller
public class SemesterController {
	
	@Autowired
	private SemesterService semesterService;
	
	
	@GetMapping("/getSemesterDetails")
	@ResponseBody
	public List<GetSemesterResponseDTO> getSemeserDetails() {
		
		try {
			return semesterService.getSemesterDetails();
		}catch(Exception ex) {
			System.out.println("exception in GetSemesterResponseDTO controller");
		}
		
		return null;
		
		
	}
	
	@GetMapping("/testCopySemester")
	@ResponseBody
	public void testCopySemester() {
		
		try {
			 semesterService.testSaveSemester();
		}catch(Exception ex) {
			System.out.println("exception in GetSemesterResponseDTO controller");
		}
		
		
		
		
	

	}
	
	
	

}
