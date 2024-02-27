package com.msu.services;

import java.util.List;

import com.msu.entities.Semester;

public interface SemesterService {

	public List<Semester> findAll();
    public void saveSemester(Semester semesterName);
}
