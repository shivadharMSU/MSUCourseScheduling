package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.CourseSchedule;

public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {

}
