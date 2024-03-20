package com.msu.services;

import java.util.List;

import com.msu.DTO.ClassDropDownListRequestDTO;
import com.msu.DTO.ClassDropDownListResponseDTO;
import com.msu.DTO.ProfessorDropDownListRequestDTO;
import com.msu.DTO.ProfessorDropDownListResponseDTO;
import com.msu.DTO.SectionScheduleSaveDTO;
import com.msu.entities.SectionSchedule;

public interface SectionScheduleService {
	
	
	public List<SectionSchedule> findAll();
	public void saveSectionSchedule(SectionSchedule sectionSchedule);
	public List<SectionSchedule> findBySectionId(Long sectionId); 
	public SectionSchedule findBySectionScheduledId(Long sectionScheduledId); 
	public List<ProfessorDropDownListResponseDTO> professorDropDownList(ProfessorDropDownListRequestDTO professorDropDownListRequestDTO);
	public List<ClassDropDownListResponseDTO> classDropDownList(ClassDropDownListRequestDTO professorDropDownListRequestDTO);
	public void saveSectionSchedule(SectionScheduleSaveDTO sectionScheduleSaveDTO);

}
