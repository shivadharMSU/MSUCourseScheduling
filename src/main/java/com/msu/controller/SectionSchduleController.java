package com.msu.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.msu.DTO.ClassDropDownListRequestDTO;
import com.msu.DTO.ClassDropDownListResponseDTO;
import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.DTO.ProfessorDropDownListRequestDTO;
import com.msu.DTO.ProfessorDropDownListResponseDTO;
import com.msu.DTO.SectionScheduleSaveDTO;
import com.msu.DTO.SuggestionRequestDTO;
import com.msu.DTO.SuggestionResponseDTO;
import com.msu.services.SectionScheduleService;
import com.msu.services.SemesterService;

@Controller
public class SectionSchduleController {
	
	
	
	
	@Autowired
	private SectionScheduleService sectionScheduleService;
	
	@PostMapping("/getProfessorListForDropDown")
	public ResponseEntity<List<ProfessorDropDownListResponseDTO>> getProfessorListForDropDown(ProfessorDropDownListRequestDTO professorDropDownListRequestDTO) {
	    try {
	    	System.out.print(professorDropDownListRequestDTO.getCourseId());
	    	return ResponseEntity.ok(sectionScheduleService.professorDropDownList(professorDropDownListRequestDTO));
	    
	        
	    } catch (Exception ex) {
	        System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Collections.emptyList()); 
	    }
	}
	
	@PostMapping("/getClassListForDropDown")
	public ResponseEntity<List<ClassDropDownListResponseDTO>> getClassListForDropDown(ClassDropDownListRequestDTO classDropDownListRequestDTO){
		
		try {
			System.out.println(classDropDownListRequestDTO.getSemId());
			return ResponseEntity.ok(sectionScheduleService.classDropDownList(classDropDownListRequestDTO));
		}catch(Exception ex) {
	        System.out.println("getClassListForDropDown: " + ex.getMessage());

		}
		return null;
		
	}
	
	
	
	@PostMapping("/getSuggestion")
	public ResponseEntity<SuggestionResponseDTO> getSuggestion(@RequestBody SuggestionRequestDTO suggestionRequestDTO){
		
		try {
			System.out.println(suggestionRequestDTO.getProfessorId());
			System.out.println(suggestionRequestDTO.isCreateNew());
			SuggestionResponseDTO res = new SuggestionResponseDTO();
			res.setSuggestion("qhwbd ehbd hbndfj ibnfjw hbfhdb hdbfhj ");
		
			return ResponseEntity.ok(res);
		}catch(Exception ex) {
	        System.out.println("getClassListForDropDown: " + ex.getMessage());

		}
		return null;
		
	}
	
	@PostMapping("/saveSctionSchedule")
	public ResponseEntity<String> saveSuggestionRequest(@RequestBody SectionScheduleSaveDTO sectionScheduleDTO){
		
		try {
			sectionScheduleService.saveSectionSchedule(sectionScheduleDTO);
			
		
			return ResponseEntity.ok("Success");
		}catch(Exception ex) {
	        System.out.println("getClassListForDropDown: " + ex.getMessage());

		}
		return null;
		
	}

}
