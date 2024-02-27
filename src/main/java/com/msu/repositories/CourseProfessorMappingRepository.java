package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.CourseProfessorMapping;

public interface CourseProfessorMappingRepository extends JpaRepository<CourseProfessorMapping, Long> {

}
