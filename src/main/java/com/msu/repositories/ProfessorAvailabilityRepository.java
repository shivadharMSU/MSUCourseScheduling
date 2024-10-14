package com.msu.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.msu.entities.ProfessorAvailability;

public interface ProfessorAvailabilityRepository extends JpaRepository<ProfessorAvailability, Long> {

    List<ProfessorAvailability> findByProfessorId(Long professorId);

    List<ProfessorAvailability> findBySemNameId(Long semNameId);

    List<ProfessorAvailability> findByProfessorIdAndSemNameId(Long professorId, Long semNameId);

    List<ProfessorAvailability> findByProfessorIdAndSemNameIdAndDayOfWeek(Long professorId, Long semNameId, Integer dayOfWeek);

    void deleteAllByProfessorId(Long professorId); // This is a more specific delete method for professor availabilities
}
