package com.msu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@DeleteMapping("/delete/{professorId}")
	public ResponseEntity<String> deleteProfessor(@PathVariable Long professorId) {
		try {
			boolean isDeleted = professorService.deleteProfessor(professorId);
			if (isDeleted) {
				return ResponseEntity.ok("Professor deleted successfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found");
			}
		} catch (Exception ex) {
			System.out.println("Exception in deleteProfessor controller: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting professor");
		}
	}

	@PostMapping("/saveProfessor")
	public ResponseEntity<Map<String, String>> saveOrUpdateProfessor(
			@RequestBody SaveOrUpdateProfessorRequestDTO saveOrUpdateProfessorRequestDTO) {
		try {
			professorService.saveOrUpdateProfessor(saveOrUpdateProfessorRequestDTO);

			Map<String, String> response = new HashMap<>();
			response.put("message", "Professor details saved successfully");

			// Return a ResponseEntity with a JSON object
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			Map<String, String> response = new HashMap<>();
			response.put("error", "Error saving professor details");

			// Return a ResponseEntity with a JSON object in case of error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
