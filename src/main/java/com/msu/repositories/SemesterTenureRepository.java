package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.SemesterName;
import com.msu.entities.SemesterTenure;

public interface SemesterTenureRepository extends JpaRepository<SemesterTenure, Integer>{
	
	
	

}
