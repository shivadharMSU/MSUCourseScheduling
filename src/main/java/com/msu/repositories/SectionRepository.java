package com.msu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

	public List<Section> findByCourseSemesterMappingId(Long courseSemesterMappingId);
}
