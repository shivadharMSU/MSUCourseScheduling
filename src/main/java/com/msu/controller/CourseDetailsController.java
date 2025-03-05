package com.msu.controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msu.DTO.CourseFullDetailsDTO;
import com.msu.DTO.CourseResponseDto;
import com.msu.DTO.SemesterTenureDTO;
import com.msu.entities.CourseDetails;
import com.msu.services.CourseDetailsService;
import com.msu.servicesImpl.DatabasePDFService;

@RestController
@RequestMapping("/coursedetails")
public class CourseDetailsController {

    @Autowired
    private CourseDetailsService coursedetailservice;

    // Generate PDF of all course details
    @GetMapping(value = "/getallcourses", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getAllCourseDetails() {

        List<CourseDetails> coursedetails = coursedetailservice.getcourselist();

        ByteArrayInputStream coursepdfreport = DatabasePDFService.coursepdfreport(coursedetails);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=courses.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(coursepdfreport));
    }

    // Get all courses with professor and semester mappings
    @GetMapping("/fullCoursesDetails")
    public ResponseEntity<List<CourseFullDetailsDTO>> getFullCoursesDetails() {
        List<CourseFullDetailsDTO> courses = coursedetailservice.getFullCoursesDetails();
        return ResponseEntity.ok(courses);
    }

    // Save or update course details based on courseId presence
    @PostMapping("/savecourse")
    public ResponseEntity<Map<String, String>> saveCourseDetails(@RequestBody CourseResponseDto courseResponseDto) {
        try {
            if (courseResponseDto.getCourseId() != null) {
                coursedetailservice.updateCourseDetails(courseResponseDto);
                return ResponseEntity.ok(Map.of("message", "Course updated successfully"));
            } else {
                coursedetailservice.saveCourseDetails(courseResponseDto);
                return ResponseEntity.ok(Map.of("message", "Course saved successfully"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Failed to save course"));
        }
    }
    
    
    
    @PostMapping("/getSemesterTenure")
    public ResponseEntity<List<SemesterTenureDTO>> getSemesterTenure() {
       
            List<SemesterTenureDTO> semesterTenureList = coursedetailservice.getSemesterTenureList();
            return ResponseEntity.ok(semesterTenureList);
        
    }

}
