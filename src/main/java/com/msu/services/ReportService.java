package com.msu.services;

import com.msu.DTO.ProfessorReportScheduleList;
import com.msu.DTO.SectionReportSceduleList;

public interface ReportService {

	 public ProfessorReportScheduleList getProfessorReportScheduleListService(Integer semId);
	 public SectionReportSceduleList getSectionReportScheduleListService(Integer semId);

}
