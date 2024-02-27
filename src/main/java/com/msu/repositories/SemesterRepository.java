package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {

}
