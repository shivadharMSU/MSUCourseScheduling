package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

}
