package com.msu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msu.DTO.ClassroomDTO;
import com.msu.DTO.ClassroomReportResponseDTO;
import com.msu.services.ClassRomService;

@RestController
@RequestMapping("/classrooms")
public class ClassRoomController {

	@Autowired
	private ClassRomService classroomService;

	@GetMapping("/report/{semesterId}")
	public ResponseEntity<ClassroomReportResponseDTO> getClassroomReport(@PathVariable int semesterId) {
		ClassroomReportResponseDTO response = classroomService.getClassroomReportResponse(semesterId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/allclassrooms")
	public ResponseEntity<List<ClassroomDTO>> getAllClassrooms() {
		List<ClassroomDTO> response = classroomService.getAllClassrooms();
		return ResponseEntity.ok(response);
	}

	@PostMapping("/saveClassroom")
	public ResponseEntity<String> saveClassroom(@RequestBody ClassroomDTO classroomDTO) {
		classroomService.saveClassroom(classroomDTO);
		return ResponseEntity.ok("Classroom saved successfully!");
	}

}
