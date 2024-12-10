package com.msu.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.ClassroomDTO;
import com.msu.DTO.ClassroomReportResponseDTO;
import com.msu.entities.ClassRoom;
import com.msu.entities.CourseDetails;
import com.msu.entities.CourseSemesterMapping;
import com.msu.entities.ProfessorDetails;
import com.msu.entities.Section;
import com.msu.entities.SectionSchedule;
import com.msu.repositories.ClassRoomRepository;
import com.msu.repositories.CourseSemesterMappingRepository;
import com.msu.repositories.CoursedetailsRepository;
import com.msu.repositories.ProfessorDetailsRepository;
import com.msu.repositories.SectionRepository;
import com.msu.repositories.SectionScheduleRepository;
import com.msu.services.ClassRomService;

@Service("classRoomService")
public class ClassRoomServiceImpl implements ClassRomService {

	@Autowired
	ClassRoomRepository classRoomRepository;

	@Autowired
	private SectionRepository sectionRepository;

	@Autowired
	private ProfessorDetailsRepository professorDetailsRepository;

	@Autowired
	private CourseSemesterMappingRepository courseSemesterMappingRepository;

	@Autowired
	private SectionScheduleRepository sectionScheduleRepository;

	@Autowired
	private CoursedetailsRepository coursedetailsRepository;

	@Override
	public List<ClassRoom> findAll() {
		try {
			return classRoomRepository.findAll();
		} catch (Exception ex) {
			System.out.println("error while fetching class Rooom  " + ex);
		}

		return null;
	}

	@Override
	public void saveClassRoom(ClassRoom classRoom) {

		try {
			classRoomRepository.save(classRoom);
		} catch (Exception ex) {
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

	public ClassroomReportResponseDTO getClassroomReportResponse(int semesterId) {
		// Fetch all classrooms
		List<ClassRoom> classrooms = classRoomRepository.findAll();

		// Initialize the list for the final response
		List<ClassroomReportResponseDTO.ClassroomDTO> classroomDTOList = new ArrayList<>();

		for (ClassRoom classroom : classrooms) {
			// Fetch sections associated with the room and semester
			List<Section> sections = sectionRepository.findByRoomIdAndSemesterId(classroom.getRoomId(), semesterId);

			// Process schedules for the sections
			List<ClassroomReportResponseDTO.ScheduleDTO> scheduleDTOList = sections.stream().flatMap(section -> {
				List<SectionSchedule> schedules = sectionScheduleRepository.findBySectionId(section.getSectionId());
				return schedules.stream().map(schedule -> {
					// Fetch course details
					CourseSemesterMapping courseMapping = courseSemesterMappingRepository
							.findById(section.getCourseSemesterMappingId()).orElse(null);

					String courseName = "Unknown";
					int courseId = 0;
					int courseNumber = 0;
					if (courseMapping != null && courseMapping.getCourseId() != null) {
						CourseDetails courseDetails = coursedetailsRepository.findById(courseMapping.getCourseId())
								.orElse(null);

						courseName = (courseDetails != null) ? courseDetails.getCourseName() : "Unknown";
						courseId = (int) ((courseDetails != null) ? courseDetails.getCourseId() : 0); // Assuming
																										// courseId is
																										// an int
						courseNumber = (courseDetails != null) ? courseDetails.getCourseNumber() : 0; // Assuming
																										// courseNumber
																										// is an int
					}

					// Fetch professor details
					Long professorId = null;
					ProfessorDetails professor = professorDetailsRepository.findById(section.getProfessorId())
							.orElse(null);
					String professorName = (professor != null) ? professor.getName() : "Unknown";
					professorId = professor.getProfessorId();

					// Create ScheduleDTO
					return new ClassroomReportResponseDTO.ScheduleDTO(schedule.getWeekDay().toString(),
							schedule.getStartTime(), schedule.getEndTime(), courseId, courseNumber, courseName,
							section.getSectionNo(), professorId, professorName);
				});
			}).collect(Collectors.toList());

			// Create ClassroomDTO
			ClassroomReportResponseDTO.ClassroomDTO classroomDTO = new ClassroomReportResponseDTO.ClassroomDTO(
					(long) classroom.getRoomId(), classroom.getRoomName(), scheduleDTOList);

			classroomDTOList.add(classroomDTO);
		}

		// Build the final response DTO
		return new ClassroomReportResponseDTO(semesterId, classroomDTOList);
	}

	@Override
	public List<ClassroomDTO> getAllClassrooms() {
		List<ClassRoom> classrooms = classRoomRepository.findAll();
		return classrooms.stream().map(classroom -> new ClassroomDTO(classroom.getRoomId(), classroom.getRoomName()))
				.collect(Collectors.toList());
	}

	@Override
    public void saveClassroom(ClassroomDTO classroomDTO) {
        if (classroomDTO.getId() == null || classroomDTO.getId() == 0) {
            ClassRoom newClassroom = new ClassRoom();
            newClassroom.setRoomName(classroomDTO.getRoomName());
            classRoomRepository.save(newClassroom);
        } else {
            ClassRoom existingClassroom = classRoomRepository.findById(classroomDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Classroom not found with ID: " + classroomDTO.getId()));
            existingClassroom.setRoomName(classroomDTO.getRoomName());
            classRoomRepository.save(existingClassroom);
        }
    }

}
