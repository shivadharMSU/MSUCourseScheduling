package com.msu.controller;

import com.msu.DTO.*;
import com.msu.services.CourseSemesterMappingService;
import com.msu.services.SectionScheduleService;
import com.msu.services.SectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@Controller
public class SectionSchduleController {




	@Autowired
	private SectionScheduleService sectionScheduleService;
    @Autowired
	private CourseSemesterMappingService courseSemesterMappingService;
    @Autowired
    private SectionService sectionService;
	
	@PostMapping("/getProfessorListForDropDown")
	public ResponseEntity<List<ProfessorDropDownListResponseDTO>> getProfessorListForDropDown(@RequestBody ProfessorDropDownListRequestDTO professorDropDownListRequestDTO) {
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
	public ResponseEntity<List<ClassDropDownListResponseDTO>> getClassListForDropDown(@RequestBody ClassDropDownListRequestDTO classDropDownListRequestDTO){
		
		
		try {
			System.out.println(classDropDownListRequestDTO.getSemId());
			return ResponseEntity.ok(sectionScheduleService.classDropDownList(classDropDownListRequestDTO));
		}catch(Exception ex) {
	        System.out.println("getClassListForDropDown: " + ex.getMessage());

		}
		return null;
		
	}
	

	
	@PostMapping("/getSuggestion")
	public ResponseEntity<SuggestionsResponseDTO> getSuggestion(@RequestBody SuggestionRequestDTO suggestionRequestDTO){
		
		try {
			
			SuggestionsResponseDTO suggestions = sectionScheduleService.getSuggestions(suggestionRequestDTO);
		
			return ResponseEntity.ok(suggestions);
		}catch(Exception ex) {
	        System.out.println("getClassListForDropDown: " + ex.getMessage());

		}
		return null;
		
	}

	@GetMapping("/getSectionSchedule/{sectionId}")
	public ResponseEntity<SectionScheduleSaveDTO> getSectionScedule(@PathVariable Long sectionId){
		
		try {
			SectionScheduleSaveDTO sectionScheduleDTO = sectionScheduleService.getSectionSceduleBySectionSceduleId(sectionId);
			return ResponseEntity.ok(sectionScheduleDTO);

			
		}catch(Exception ex) {
	        System.out.println("getClassListForDropDown: " + ex.getMessage());

		}
		return null;
		
	}

	@GetMapping("/deleteSection/{sectionId}")
	public ResponseEntity<String> deleteSection(@PathVariable Long sectionId){
		try {
			 sectionScheduleService.deleteSectionSchedule(sectionId);
			return ResponseEntity.ok("success");
		}catch(Exception ex) {
			System.out.println("getClassListForDropDown: " + ex.getMessage());
			return ResponseEntity.ok("failure");
		}
	}

	//saveSectionSchedule

	@PostMapping("/saveSctionSchedule")
	public ResponseEntity<String> saveSctionSchedule(@RequestBody SectionScheduleSaveDTO sectionScheduleSaveDTO){
		try {
			 sectionScheduleService.saveSectionSchedule(sectionScheduleSaveDTO);
			return ResponseEntity.ok("success");
		}catch(Exception ex) {
			System.out.println("getClassListForDropDown: " + ex.getMessage());
		}
		return null;

	}


	@GetMapping("/getCourseDropDownNotIncludedForSemesterList/{semId}")
	public ResponseEntity<CourseDropDownForSemesterDTOList> getCourseDropDownNotIncludedForSemester(@PathVariable Integer semId){

		try {
			List<CourseDropDownForSemesterDTO> courseDropDownNotIncludedForSemester = courseSemesterMappingService.getCourseDropDownNotIncludedForSemester(semId);
			CourseDropDownForSemesterDTOList courseDropDownForSemesterDTOList = new CourseDropDownForSemesterDTOList();
			courseDropDownForSemesterDTOList.setCourseDropDownForSemester(courseDropDownNotIncludedForSemester);
			return ResponseEntity.ok(courseDropDownForSemesterDTOList);
		}catch(Exception ex) {
			System.out.println("getClassListForDropDown: " + ex.getMessage());

		}
		return null;

	}

	@GetMapping("/deleteCourseFromSem/{semId}/{courseId}")
	public ResponseEntity<String> deleteCourseBySemId(@PathVariable Integer semId, @PathVariable Long courseId) {
		try {
			courseSemesterMappingService.deleteCourseSemesterMappingByCourseIfAndSemId(semId,courseId);
			return ResponseEntity.ok("success");
		} catch (Exception ex) {
			System.out.println("getClassListForDropDown: " + ex.getMessage());
			return ResponseEntity.ok("failure");
		}
	}

	
	@GetMapping("/getCrossSectionDropDownList/{semId}/{courseId}")
	public ResponseEntity<CrossSectionDropDownList> getCourseSectionId(@PathVariable Integer semId, @PathVariable Long courseId) {
		try {
			
			CrossSectionDropDownList list = sectionService.getCrossSectionList(semId, courseId);
			return ResponseEntity.ok(list);
		} catch (Exception ex) {
			System.out.println("getClassListForDropDown: " + ex.getMessage());
			
		}
		return null;
	}
	
	@PostMapping("/saveCourseForSemester")
	public ResponseEntity<APIResponseDTO> saveCourseForSemester(@RequestBody SaveCourseForSemesterDTO saveCourseForSemesterDTO){
		try {
			courseSemesterMappingService.getSaveCourseForSemesterDTO(saveCourseForSemesterDTO);
			APIResponseDTO response = new APIResponseDTO();
			response.setResponseMessage("success");
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			System.out.println("getClassListForDropDown: " + ex.getMessage());
		}
		return null;

	}


}
