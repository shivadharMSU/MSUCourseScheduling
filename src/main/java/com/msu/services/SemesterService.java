package com.msu.services;

import java.util.List;

import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.entities.Semester;

public interface SemesterService {

	public List<Semester> findAll();
    public void saveSemester(Semester semesterName);
    public List<GetSemesterResponseDTO> getSemesterDetails();
    public void testSaveSemester();
}
