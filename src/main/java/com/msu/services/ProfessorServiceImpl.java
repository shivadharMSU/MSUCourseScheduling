package com.msu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.Professor;
import com.msu.repositories.ProfessorRepository;



@Service("professorService")
public class ProfessorServiceImpl implements ProfessorService{
	
	@Autowired
	private ProfessorRepository professorRegistry;

	@Override
	public Iterable<Professor> findAll() {
		// TODO Auto-generated method stub
		Iterable<Professor> all = professorRegistry.findAll();
	return all;
	}

	@Override
	public void save(Professor professor) {
		professor.setId(2);
		professorRegistry.save(professor);
		
	}

	
}
