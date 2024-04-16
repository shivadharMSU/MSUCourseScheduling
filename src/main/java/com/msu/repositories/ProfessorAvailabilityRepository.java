package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.ProfessorAvailability;

public interface ProfessorAvailabilityRepository extends JpaRepository<ProfessorAvailability, Long> {
   public  List<ProfessorAvailability> findByProfessorId(Long professorId);
   public  List<ProfessorAvailability> findBySemNameId(Long semNameId);
   public  List<ProfessorAvailability> findByProfessorIdAndSemNameId(Long professorId,Integer semNameId);
   public  List<ProfessorAvailability> findByProfessorIdAndSemNameIdAndDayOfWeek(Long professorId,Integer semNameId,Integer dayOfWeek);
   //dayOfWeek
    
}
