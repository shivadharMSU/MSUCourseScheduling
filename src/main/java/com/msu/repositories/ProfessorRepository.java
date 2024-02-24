package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msu.entities.Professor;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}