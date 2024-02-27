package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.SemesterName;

public interface SemesterNameRepository extends JpaRepository<SemesterName, Integer>{

}
