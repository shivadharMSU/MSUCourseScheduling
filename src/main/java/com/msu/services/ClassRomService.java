package com.msu.services;

import java.util.List;

import com.msu.entities.ClassRoom;

public interface ClassRomService {
	
	public List<ClassRoom> findAll();
    public void saveClassRoom(ClassRoom classRoom);

}
