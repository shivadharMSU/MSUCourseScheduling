package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.CourseDetailsDTO;
import com.msu.DTO.CourseFullDetailsDTO;
import com.msu.DTO.CourseProfessorMappingDTO;
import com.msu.DTO.CourseResponseDto;
import com.msu.DTO.CourseSemesterMappingDTO;
import com.msu.DTO.SemesterTenureDTO;
import com.msu.entities.CourseDetails;
import com.msu.entities.CourseProfessorMapping;
import com.msu.entities.CourseSemesterMapping;
import com.msu.entities.Semester;
import com.msu.entities.SemesterName;
import com.msu.entities.SemesterTenure;
import com.msu.repositories.CourseProfessorMappingRepository;
import com.msu.repositories.CourseSemesterMappingRepository;
import com.msu.repositories.CoursedetailsRepository;
import com.msu.repositories.SemesterRepository;
import com.msu.repositories.SemesterTenureRepository;
import com.msu.services.CourseDetailsService;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {
	
	@Autowired
	private CoursedetailsRepository coursedetailsRepository;

    @Autowired
    private CourseProfessorMappingRepository courseProfessorMappingRepository;

    @Autowired
    private CourseSemesterMappingRepository courseSemesterMappingRepository;

    @Autowired
    private CourseProfessorMappingServiceImpl courseProfessorMappingServiceImpl;
    
    @Autowired
    private SemesterTenureRepository semesterTenureRepository;

	@Override
	public List<CourseDetails> getcourselist() {
	    List<CourseDetails> courseDetailsList = coursedetailsRepository.findAll();

	    return courseDetailsList;
	}
	
    @Override
    public List<CourseFullDetailsDTO> getFullCoursesDetails() {
        List<CourseDetails> courses = coursedetailsRepository.findAll();
        List<CourseFullDetailsDTO> courseDTOs = new ArrayList<>();

        for (CourseDetails course : courses) {
            List<CourseProfessorMapping> professorMappings = courseProfessorMappingRepository.findCourseProfessorMappingByCourse(course.getCourseId());
            List<CourseSemesterMapping> semesterMappings = courseSemesterMappingRepository.findByCourseId(course.getCourseId());
            
            // Convert the course, professor mappings, and semester mappings into DTO
            CourseFullDetailsDTO courseDTO = mapToFullDetailsDTO(course, professorMappings, semesterMappings);
            courseDTOs.add(courseDTO);
        }

        return courseDTOs;
    }
    
    // Helper method for mapping (same as before)
    private CourseFullDetailsDTO mapToFullDetailsDTO(CourseDetails courseDetails, List<CourseProfessorMapping> professorMappings, List<CourseSemesterMapping> semesterMappings) {
        CourseFullDetailsDTO dto = new CourseFullDetailsDTO();

        dto.setCourseDetails(new CourseDetailsDTO(
                courseDetails.getCourseId(),
                courseDetails.getCourseNumber(),
                courseDetails.getCourseName(),
                courseDetails.getCredits(),
                courseDetails.isComputerRequired()
        ));

     // Map professor mappings to DTOs
        List<CourseProfessorMappingDTO> professorDTOs = new ArrayList<>();
        for (CourseProfessorMapping mapping : professorMappings) {
            CourseProfessorMappingDTO cpdto = new CourseProfessorMappingDTO(
                mapping.getId(),
                mapping.getCourse(),
                mapping.getProfessor()
            );
            professorDTOs.add(cpdto);
        }
        dto.setProfessorMappings(professorDTOs);

        // Map semester mappings to DTOs
        List<CourseSemesterMappingDTO> semesterDTOs = new ArrayList<>();
        for (CourseSemesterMapping mapping : semesterMappings) {
            CourseSemesterMappingDTO csdto = new CourseSemesterMappingDTO(
                mapping.getCourseSemesterMappingId(),
                mapping.getCourseId(),
                mapping.getSemesterId(),
                mapping.getTenure(),
                mapping.getTermTenure()
            );
            semesterDTOs.add(csdto);
        }
        dto.setSemesterMappings(semesterDTOs);


        return dto;
    }
    
    // Method to save a new course
    @Override
	public void saveCourseDetails(CourseResponseDto courseResponseDto) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourseName(courseResponseDto.getCourseName());
        courseDetails.setCourseNumber((int) courseResponseDto.getCourseNumber());
        courseDetails.setCredits(courseResponseDto.getCredits());
        courseDetails.setComputerRequired(courseResponseDto.isComputerRequired());
    
        // Save the new course to the database
        CourseDetails savedCourse = coursedetailsRepository.save(courseDetails);

        courseProfessorMappingServiceImpl.processMethodForSaveMappingCourses(savedCourse.getCourseId(), courseResponseDto.getProfessors());
    }
    
    // Method to update an existing course
    @Override
	public void updateCourseDetails(CourseResponseDto courseResponseDto) {
        // Fetch the existing course by its ID
        CourseDetails existingCourse = coursedetailsRepository
                .findById(courseResponseDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
    
        // Update the course details
        existingCourse.setCourseName(courseResponseDto.getCourseName());
        existingCourse.setCourseNumber(courseResponseDto.getCourseNumber());
        existingCourse.setCredits(courseResponseDto.getCredits());
        existingCourse.setComputerRequired(courseResponseDto.isComputerRequired());
    
        // Save the updated course to the database
        coursedetailsRepository.save(existingCourse);
        
        courseProfessorMappingServiceImpl.processMethodForSaveMappingCourses(courseResponseDto.getCourseId(), courseResponseDto.getProfessors());
    }

	@Override
	public CourseDetails findCourseDetailsByCourseId(Long courseId) {
		return coursedetailsRepository.findCourseDetailsByCourseId(courseId);
		
	}

	@Override
	public List<SemesterTenureDTO> getSemesterTenureList() {
		List<SemesterTenure> semesterTenureList = semesterTenureRepository.findAll();
		 List<SemesterTenureDTO> semesterDtoList = new ArrayList<SemesterTenureDTO>();
		 for(SemesterTenure semesterList: semesterTenureList) {
			 SemesterTenureDTO semesterDto = new SemesterTenureDTO();
			 semesterDto.setTenureId(semesterList.getTenureId());
			 semesterDto.setWeeklyTenure(semesterList.getWeeklyTenure()+" weeks");
			 semesterDtoList.add(semesterDto);
			 
		 }
		 return semesterDtoList;
		
	}


	

	

	

}
