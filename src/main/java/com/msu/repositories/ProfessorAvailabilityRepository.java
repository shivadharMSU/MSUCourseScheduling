package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.ProfessorAvailability;

public interface ProfessorAvailabilityRepository extends JpaRepository<ProfessorAvailability, Long> {
    List<ProfessorAvailability> findByProfessorId(Long professorId);
}
