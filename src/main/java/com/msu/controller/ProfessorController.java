package com.msu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msu.DTO.CreateNewSemesterRequestDTO;
import com.msu.DTO.SaveOrUpdateProfessorRequestDTO;
import com.msu.DTO.getProfessorResponseDTO;
import com.msu.DTO.getProfessorTypeResponseDTO;
import com.msu.services.ProfessorService;

@Controller
public class ProfessorController {

	@Autowired
	public ProfessorService professorService;

	@GetMapping("/getProfessors")
	@ResponseBody
	public List<getProfessorResponseDTO> getProfessorList(Model model) {

		return professorService.findAllProfessorsWithDetails();
	}

	@GetMapping("/getProfessorType")
	@ResponseBody
	public List<getProfessorTypeResponseDTO> getProfessorTypesList(Model model) {

		return professorService.findAllProfessorType();
	}
	
	@PostMapping("/saveProfessor")
	public ResponseEntity<String> saveOrUpdateProfessor(@RequestBody SaveOrUpdateProfessorRequestDTO saveOrUpdateProfessorRequestDTO) {
		try {
			professorService.saveOrUpdateProfessor(saveOrUpdateProfessorRequestDTO);
			return ResponseEntity.ok("success"); // HTTP 200 OK for success

		} catch (Exception ex) {
			System.out.println("Exception in saveOrUpdateProfessor controller: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // HTTP 500 Internal Server Error
																						// for failure
		}
	}

}
