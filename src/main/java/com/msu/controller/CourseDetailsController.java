package com.msu.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msu.entities.CourseDetails;
import com.msu.services.CourseDetailsService;
import com.msu.servicesImpl.DatabasePDFService;

@RestController
@RequestMapping("/coursedetails")
public class CourseDetailsController {
	
	@Autowired
	private CourseDetailsService coursedetailservicce;
	
	@GetMapping(value = "/getallcourses", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getallcoursedetails() {
		
			List<CourseDetails> coursedetails = coursedetailservicce.getcourselist();
			
			ByteArrayInputStream coursepdfreport = DatabasePDFService.coursepdfreport(coursedetails);
			System.out.println(coursedetails);
			
			
			
			HttpHeaders headers = new  HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=courses.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(coursepdfreport));
		
	}
	@PostMapping("/savecourse")
	public void SaveCourseDetails(@RequestBody CourseDetails coursedetails) {
		
		coursedetailservicce.saveCourseDetails(coursedetails);
		
	}
	
	
	
	

}
