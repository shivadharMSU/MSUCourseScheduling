package com.msu.controller;

import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.DTO.ProfessorReportScheduleList;
import com.msu.entities.CourseDetails;
import com.msu.services.CourseDetailsService;
import com.msu.services.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {
     @Autowired
   private  CourseDetailsService courseDetailsService;
      @Autowired
    private  ReportService reposrtService;
      
    @PostMapping("/getCourseReports")
    public ResponseEntity<List<CourseDetails>> SaveCourseDetails() {

        List<CourseDetails> getcourselist = courseDetailsService.getcourselist();

        return ResponseEntity.ok(getcourselist);

    }
    
    @GetMapping("/getProfReport/{semId}")
    public ResponseEntity<ProfessorReportScheduleList> getProfessorSchedule(@PathVariable Integer semId) {
    	         
    	ProfessorReportScheduleList professorReportScheduleListService = reposrtService.getProfessorReportScheduleListService(semId);
    	return  ResponseEntity.ok(professorReportScheduleListService);
    }
        
    
}


