package com.msu.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msu.DTO.CreateNewSemesterRequestDTO;
import com.msu.DTO.DisplayCourseAndSectionResponseDTO;
import com.msu.DTO.GetCourseAndSemesterListRequest;
import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.entities.CopySemesterRequestDTO;
import com.msu.entities.CourseDetails;
import com.msu.services.SemesterService;

@Controller
public class SemesterController {
	
	@Autowired
	private SemesterService semesterService;
	
	
//	@GetMapping("/getSemesterDetails")
//	@ResponseBody
//	public List<GetSemesterResponseDTO> getSemeserDetails() {
//		
//		try {
//			return semesterService.getSemesterDetails();
//		}catch(Exception ex) {
//			System.out.println("exception in GetSemesterResponseDTO controller");
//		}
//		
//		return null;
//		
//		
//	}
	
	
	@GetMapping("/getSemesterDetails")
	public ResponseEntity<List<GetSemesterResponseDTO>> getSemeserDetails() {
	    try {
	        List<GetSemesterResponseDTO> semesterDetails = semesterService.getSemesterDetails();
	        return ResponseEntity.ok(semesterDetails); // HTTP 200 OK for success
	        
	    } catch (Exception ex) {
	        System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Collections.emptyList()); // HTTP 500 Internal Server Error for failure
	    }
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
	
	@PostMapping("/copySemester")
	public ResponseEntity<String> copySemesterDetails(@RequestBody CopySemesterRequestDTO coursedetails) {
		
		
		return ResponseEntity.ok("success"); // HTTP 200 OK for success
		
		///coursedetailservicce.saveCourseDetails(coursedetails);
		
	}
	
	@PostMapping("/createSemester")
	public ResponseEntity<String> createSemester(@RequestBody CreateNewSemesterRequestDTO createNewSemesterRequestDTO) {
	    try {
	        semesterService.createNewSemester(createNewSemesterRequestDTO);
	        return ResponseEntity.ok("success"); // HTTP 200 OK for success
	        
	    } catch (Exception ex) {
	        System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // HTTP 500 Internal Server Error for failure
	    }
	}
	
	@PostMapping("/getCourseAndSemesterList")
	public ResponseEntity<List<DisplayCourseAndSectionResponseDTO>> getCourseAndSemesterList(@RequestBody GetCourseAndSemesterListRequest courseAndSemesterList) {
	    try {
	        List<DisplayCourseAndSectionResponseDTO> fetchCourseAndSemesterDetails = semesterService.fetchCourseAndSemesterDetails(courseAndSemesterList.getSemId());
	        return ResponseEntity.ok(fetchCourseAndSemesterDetails); // HTTP 200 OK for success
	        
	    } catch (Exception ex) {
	        System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // HTTP 500 Internal Server Error for failure
	    }
	}
	
	
	

}
