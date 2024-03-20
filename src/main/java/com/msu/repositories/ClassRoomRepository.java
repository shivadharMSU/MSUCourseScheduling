package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer>{

	
         public ClassRoom findByRoomId(Integer roomId);
}
