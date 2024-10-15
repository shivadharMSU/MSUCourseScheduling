package com.msu.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.msu.DTO.ClassDropDownListRequestDTO;
import com.msu.DTO.ClassDropDownListResponseDTO;
import com.msu.DTO.ProfessorDropDownListRequestDTO;
import com.msu.DTO.ProfessorDropDownListResponseDTO;
import com.msu.DTO.SectionScheduleSaveDTO;
import com.msu.DTO.SuggestionRequestDTO;
import com.msu.DTO.SuggestionsResponseDTO;
import com.msu.entities.SectionSchedule;

public interface SectionScheduleService {
	
	
	public List<SectionSchedule> findAll();
	public void saveSectionSchedule(SectionSchedule sectionSchedule);
	public List<SectionSchedule> findBySectionId(Long sectionId); 
	public SectionSchedule findBySectionScheduledId(Long sectionScheduledId); 
	public List<ProfessorDropDownListResponseDTO> professorDropDownList(ProfessorDropDownListRequestDTO professorDropDownListRequestDTO);
	public List<ClassDropDownListResponseDTO> classDropDownList(ClassDropDownListRequestDTO professorDropDownListRequestDTO);
	public void saveSectionSchedule(SectionScheduleSaveDTO sectionScheduleSaveDTO);
	public  SuggestionsResponseDTO getSuggestions(SuggestionRequestDTO suggestionsRequestDTO);
	 public List<SectionSchedule> fetchProfessorConflctsData(SuggestionRequestDTO suggestionsRequest);
	 public List<SectionSchedule> fetchCourseConflctsData(SuggestionRequestDTO suggestionsRequest);
	 public List<SectionSchedule> fetchRoomConflctsData(SuggestionRequestDTO suggestionsRequest);
	 public SectionScheduleSaveDTO getSectionSceduleBySectionSceduleId(Long sectionId);
	 public void  deleteSectionSchedule(Long sectionId);
	 
	 

}
