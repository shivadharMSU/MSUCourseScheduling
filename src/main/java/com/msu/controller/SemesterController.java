package com.msu.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msu.DTO.APIResponseDTO;
import com.msu.DTO.CreateNewSemesterRequestDTO;
import com.msu.DTO.DisplayCourseAndSectionResponseDTO;
import com.msu.DTO.GetCourseAndSemesterListRequest;
import com.msu.DTO.GetSemesterResponseDTO;
import com.msu.DTO.PreviousSemesterListDTO;
import com.msu.DTO.PreviousSemesterListRequestDTO;
import com.msu.Enums.SmesterEnum;
import com.msu.entities.CopySemesterRequestDTO;
import com.msu.entities.Semester;
import com.msu.services.SemesterService;

@Controller
public class SemesterController {

	@Autowired
	private SemesterService semesterService;

	@GetMapping("/getSemesterDetails")
	public ResponseEntity<List<GetSemesterResponseDTO>> getSemeserDetails() {
		try {
			List<GetSemesterResponseDTO> semesterDetails = semesterService.getSemesterDetails();
			return ResponseEntity.ok(semesterDetails); // HTTP 200 OK for success

		} catch (Exception ex) {
			System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

	@GetMapping("/testCopySemester")
	@ResponseBody
	public void testCopySemester() {

		try {
			semesterService.testSaveSemester();
		} catch (Exception ex) {
			System.out.println("exception in GetSemesterResponseDTO controller");
		}

	}

	@PostMapping("/copySemester")
	public ResponseEntity<APIResponseDTO> copySemesterDetails(@RequestBody CopySemesterRequestDTO coursedetails) {
		semesterService.copySemester(coursedetails);
		APIResponseDTO response = new APIResponseDTO();
		response.setResponseMessage("success");
		return ResponseEntity.ok(response);

	}

	@PostMapping("/createSemester")
	public ResponseEntity<APIResponseDTO> createSemester(
			@RequestBody CreateNewSemesterRequestDTO createNewSemesterRequestDTO) {
		try {
			APIResponseDTO response = new APIResponseDTO();
			semesterService.createNewSemester(createNewSemesterRequestDTO);
			response.setResponseMessage("success");
			return ResponseEntity.ok(response); // HTTP 200 OK for success

		} catch (Exception ex) {
			System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
			APIResponseDTO response = new APIResponseDTO();
			response.setResponseMessage("failure");
			return ResponseEntity.ok(response); // HTTP 500 Internal Server Error for failure
		}
	}

	@PostMapping("/getCourseAndSemesterList")
	public ResponseEntity<List<DisplayCourseAndSectionResponseDTO>> getCourseAndSemesterList(
			@RequestBody GetCourseAndSemesterListRequest courseAndSemesterList) {
		try {
			List<DisplayCourseAndSectionResponseDTO> fetchCourseAndSemesterDetails = semesterService
					.fetchCourseAndSemesterDetails(courseAndSemesterList.getSemId());
			return ResponseEntity.ok(fetchCourseAndSemesterDetails); // HTTP 200 OK for success

		} catch (Exception ex) {
			System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/getPreviousSemList")
	public ResponseEntity<PreviousSemesterListDTO> fetchPreviousSemester(
			@RequestBody PreviousSemesterListRequestDTO previousSemesterListRequestDTO) {
		try {
			PreviousSemesterListDTO previoussemDetails = semesterService
					.getPrevioussemDetails(previousSemesterListRequestDTO);
			return ResponseEntity.ok(previoussemDetails); // HTTP 200 OK for success

		} catch (Exception ex) {
			System.out.println("Exception in GetSemesterResponseDTO controller: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping("/getSemesterDropDown")
	public ResponseEntity<HashMap<Integer, String>> getSemesterDropDown() {

		HashMap<Integer, String> map = new HashMap<Integer, String>();
		List<Semester> semesterList = semesterService.findAll();
		for (Semester sem : semesterList) {
			map.put(sem.getSemId(), SmesterEnum.getSemesterName(sem.getSemNameId()) + "-" + sem.getYear());
		}

		return ResponseEntity.ok(map);
	}

}
