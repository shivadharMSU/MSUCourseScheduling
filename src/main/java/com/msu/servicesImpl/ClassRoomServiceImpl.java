package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.ClassRoom;
import com.msu.repositories.ClassRoomRepository;
import com.msu.services.ClassRomService;

@Service("classRoomService")
public class ClassRoomServiceImpl implements ClassRomService{
	
     @Autowired
	ClassRoomRepository classRoomRepository;
     
	@Override
	public List<ClassRoom> findAll() {
		try{
			return classRoomRepository.findAll();
		}catch(Exception ex) {
			System.out.println("error while fetching class Rooom  "+ex);
		}
		
		return null;
	}

	@Override
	public void saveClassRoom(ClassRoom classRoom) {
		
		try {
			classRoomRepository.save(classRoom);
		}catch(Exception ex) {
			System.out.println("exception while saving class room");
		}
	}

	@Override
	public ClassRoom findByRoomId(Integer roomId) {
		
		return classRoomRepository.findByRoomId(roomId);
	}

	@Override
	public ClassRoom findByRoomName(String roomName) {
		// TODO Auto-generated method stub
		return classRoomRepository.findByRoomName(roomName);
	}

}
