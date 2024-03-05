package com.msu.repositories;

import com.msu.entities.ProfessorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorTypeRepository extends JpaRepository<ProfessorType, Integer> {
    // You can define custom query methods here if needed
    
}
