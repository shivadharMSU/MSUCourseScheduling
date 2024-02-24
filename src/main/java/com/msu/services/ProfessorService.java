package com.msu.services;

import com.msu.entities.Professor;

public interface ProfessorService {
	
	public Iterable<Professor> findAll();
	public void save(Professor professor);
	

}
