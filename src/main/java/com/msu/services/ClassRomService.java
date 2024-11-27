package com.msu.services;

import java.util.List;

import com.msu.DTO.ClassroomReportResponseDTO;
import com.msu.entities.ClassRoom;

public interface ClassRomService {

	public List<ClassRoom> findAll();

	public void saveClassRoom(ClassRoom classRoom);

	public ClassRoom findByRoomId(Integer roomId);

	public ClassRoom findByRoomName(String roomName);

	public ClassroomReportResponseDTO getClassroomReportResponse(int semesterId);

}
