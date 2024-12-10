package com.msu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msu.DTO.ProfessorReportScheduleList;
import com.msu.DTO.SectionReportSceduleList;
import com.msu.entities.CourseDetails;
import com.msu.services.CourseDetailsService;
import com.msu.services.ReportService;

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
    
    
    @GetMapping("/getSectionReport/{semId}")
    public ResponseEntity<SectionReportSceduleList> getsectionreport(@PathVariable Integer semId) {
    	         
    	 SectionReportSceduleList sectionReportScheduleListService = reposrtService.getSectionReportScheduleListService(semId);
    	return  ResponseEntity.ok(sectionReportScheduleListService);
    }
        
    
}


