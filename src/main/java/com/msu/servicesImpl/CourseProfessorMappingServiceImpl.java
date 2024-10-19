package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.CourseProfessorMapping;
import com.msu.repositories.CourseProfessorMappingRepository;
import com.msu.services.CourseProfessorMappingService;

@Service("courseProfessorMapping")
public class CourseProfessorMappingServiceImpl implements CourseProfessorMappingService {

	@Autowired
	private CourseProfessorMappingRepository courseProfessorMappingRepository;
	
	@Override
	public List<CourseProfessorMapping> findAll() {
		try {
			return courseProfessorMappingRepository.findAll();
		}catch(Exception ex) {
			System.out.println("error while fetaching CourseProfessorMapping");
		}
		
		return null;
	}

	@Override
	public void processMethodForSave(Long professorId, List<Long> courses) {
	    try {
	    	deleteExistingMappingsForProfessor(professorId);

	        // Create and save the mappings
	        for (Long courseId : courses) {
	            CourseProfessorMapping mapping = new CourseProfessorMapping();
	            mapping.setProfessor(professorId);  // Set professor ID
	            mapping.setCourse(courseId);  // Set course ID directly since it's a Long
	            saveCourseProfessorMapping(mapping);  // Save the mapping
	        }
	    } catch (Exception ex) {
	        System.err.println("Error while saving CourseProfessorMapping: " + ex.getMessage());
	    }
	}

	@Override
	public void processMethodForSaveMappingCourses(Long courseId, List<Long> professorIds) {
	    try {
	    	deleteExistingMappingsForCourse(courseId);

	        // Create and save the mappings
	        for (Long professorId : professorIds) {
	            CourseProfessorMapping mapping = new CourseProfessorMapping();
	            mapping.setProfessor(professorId);  // Set professor ID
	            mapping.setCourse(courseId);  // Set course ID directly since it's a Long
	            saveCourseProfessorMapping(mapping);  // Save the mapping
	        }
	    } catch (Exception ex) {
	        System.err.println("Error while saving CourseProfessorMapping: " + ex.getMessage());
	    }
	}

	@Override
	public void deleteExistingMappingsForCourse(Long courseId) {
	    try {
	        // Find all mappings for the given CourseId
	        List<CourseProfessorMapping> mappings = findCourseProfessorMappingByCourse(courseId);
	        
	        // Delete all found mappings
	        courseProfessorMappingRepository.deleteAll(mappings);

	        System.out.println("Deleted existing mappings for CourseId: " + courseId);
	    } catch (Exception ex) {
	        System.err.println("Error while deleting CourseProfessorMapping for CourseId " + courseId + ": " + ex.getMessage());
	    }
	}

	@Override
	public void deleteExistingMappingsForProfessor(Long professorId) {
	    try {
	        // Find all mappings for the given professorId
	        List<CourseProfessorMapping> mappings = findByProfessor(professorId);
	        
	        // Delete all found mappings
	        courseProfessorMappingRepository.deleteAll(mappings);

	        System.out.println("Deleted existing mappings for professorId: " + professorId);
	    } catch (Exception ex) {
	        System.err.println("Error while deleting CourseProfessorMapping for professorId " + professorId + ": " + ex.getMessage());
	    }
	}

	@Override
	public List<CourseProfessorMapping> findByProfessor(Long professorId) {
		return courseProfessorMappingRepository.findByProfessor(professorId);
	}

	@Override
	public void saveCourseProfessorMapping(CourseProfessorMapping courseProfessorMapping) {
		try {
			courseProfessorMappingRepository.save(courseProfessorMapping);
		}catch(Exception ex) {
			System.out.println("error while saving CourseProfessorMapping");

		}
		
	}

	@Override
	public List<CourseProfessorMapping> findCourseProfessorMappingByCourse(Long course) {
	
		return courseProfessorMappingRepository.findCourseProfessorMappingByCourse(course);
	}

}
