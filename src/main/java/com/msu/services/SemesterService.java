package com.msu.services;

import java.util.List;

import com.msu.DTO.CreateNewSemesterRequestDTO;
import com.msu.DTO.DisplayCourseAndSectionResponseDTO;
import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.entities.CopySemesterRequestDTO;
import com.msu.entities.Semester;

public interface SemesterService {

	public List<Semester> findAll();
    public void saveSemester(Semester semesterName);
    public List<GetSemesterResponseDTO> getSemesterDetails();
    public void testSaveSemester();
    public void copySemester(CopySemesterRequestDTO coursedetails);
    public boolean createNewSemester(CreateNewSemesterRequestDTO createnEWSmesterRequestDTO);
    public List<DisplayCourseAndSectionResponseDTO> fetchCourseAndSemesterDetails(Integer semId);
    public Semester findBySemId(Integer semId);
	public Semester findBySemNameId(Integer semNameId);
}
