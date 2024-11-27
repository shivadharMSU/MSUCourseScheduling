package com.msu.servicesImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.DTO.Occupency;
import com.msu.DTO.ProfessorReportSchedule;
import com.msu.DTO.ProfessorReportScheduleList;
import com.msu.DTO.Schedule;
import com.msu.DTO.SectionReportScedule;
import com.msu.DTO.SectionReportSceduleList;
import com.msu.Enums.WeekEnum;
import com.msu.entities.ClassRoom;
import com.msu.entities.CourseDetails;
import com.msu.entities.CourseSemesterMapping;
import com.msu.entities.ProfessorDetails;
import com.msu.entities.Section;
import com.msu.entities.SectionSchedule;
import com.msu.services.ClassRomService;
import com.msu.services.CourseDetailsService;
import com.msu.services.CourseSemesterMappingService;
import com.msu.services.ProfessorService;
import com.msu.services.ReportService;
import com.msu.services.SectionScheduleService;
import com.msu.services.SectionService;

@Service("ReportServiceImpl")
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private SectionScheduleService sectionScheduleService;

	@Autowired
	private CourseSemesterMappingService courseSemesterMappingService;

	@Autowired
	private CourseDetailsService courseDetailsService;

	@Autowired
	private ClassRomService classRomService;

	@Override
	public ProfessorReportScheduleList getProfessorReportScheduleListService(Integer semId) {

		ProfessorReportScheduleList professorScheduleList = new ProfessorReportScheduleList();

		List<ProfessorDetails> professorList = professorService.findAll();
		List<Section> sectionList = sectionService.findAll();
		List<CourseSemesterMapping> courseSemesterMappingList = courseSemesterMappingService.findBySemesterId(semId);

		List<ProfessorReportSchedule> professorReportScheduleList = new ArrayList<ProfessorReportSchedule>();
		for (ProfessorDetails profDetails : professorList) {
			ProfessorReportSchedule profReportSedule = new ProfessorReportSchedule();
			profReportSedule.setId(profDetails.getProfessorId());
			profReportSedule.setName(profDetails.getName());
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (CourseSemesterMapping courseSemesterMapping : courseSemesterMappingList) {
				List<Section> sectionbyProfessorAndSemester = sectionService
						.findByProfessorIdAndCourseSemesterMappingId(profDetails.getProfessorId(),
								courseSemesterMapping.getCourseSemesterMappingId());
				for (Section section : sectionbyProfessorAndSemester) {
					List<SectionSchedule> sectionScedule = sectionScheduleService
							.findBySectionId(section.getSectionId());
					for (SectionSchedule sectionSection : sectionScedule) {
						String weekValue = WeekEnum.getWeekByValue(sectionSection.getWeekDay());
						CourseDetails courseDetails = courseDetailsService
								.findCourseDetailsByCourseId(courseSemesterMapping.getCourseId());
						LocalTime time = sectionSection.getStartTime().now(); // Example LocalTime
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

						String formattedStartedTime = time.format(formatter);

						LocalTime endtime = sectionSection.getEndTime().now(); // Example LocalTime
						DateTimeFormatter endTimeformatter = DateTimeFormatter.ofPattern("hh:mm a");

						String endFormattedStartedTime = endtime.format(endTimeformatter);
						map.computeIfAbsent(weekValue, k -> new ArrayList<>()).add(courseDetails.getCourseName() + "-"
								+ formattedStartedTime + "-" + endFormattedStartedTime);
					}

				}
			}

			Schedule schedule = new Schedule();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				String key = entry.getKey();
				List<String> values = entry.getValue();
				System.out.println(WeekEnum.getWeekByValue(1));
				if (key.equals(WeekEnum.getWeekByValue(1))) {
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setMon(occupency);
				}
				if (key.equals(WeekEnum.getWeekByValue(2))) {
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setTues(occupency);
				}
				if (key.equals(WeekEnum.getWeekByValue(3))) {
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setWed(occupency);
				}
				if (key.equals(WeekEnum.getWeekByValue(4))) {
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setThurs(occupency);
				}
				if (key.equals(WeekEnum.getWeekByValue(5))) {
					Occupency occupency = new Occupency();
					occupency.setOccupied(String.join(",", values));
					schedule.setFri(occupency);
				}

			}
			profReportSedule.setSchedule(schedule);

			professorReportScheduleList.add(profReportSedule);
		}
		// get all professorList

		professorScheduleList.setProfessorReportSchedule(professorReportScheduleList);

		return professorScheduleList;
	}

	@Override
	public SectionReportSceduleList getSectionReportScheduleListService(Integer semId) {
		// TODO Auto-generated method stub

		SectionReportSceduleList sectionReportSceduleList = new SectionReportSceduleList();
		ArrayList<SectionReportScedule> list = new ArrayList<SectionReportScedule>();

		List<ProfessorDetails> professorList = professorService.findAll();
		List<Section> sectionList = sectionService.findAll();
		List<CourseSemesterMapping> courseSemesterMappingList = courseSemesterMappingService.findBySemesterId(semId);

		List<ProfessorReportSchedule> professorReportScheduleList = new ArrayList<ProfessorReportSchedule>();

		for (CourseSemesterMapping courseSemMapping : courseSemesterMappingList) {

			List<Section> sectionListByCourseMappingId = sectionList.stream()
					.filter(c -> c.getCourseSemesterMappingId() == courseSemMapping.getCourseSemesterMappingId())
					.collect(Collectors.toList());

			for (Section section : sectionListByCourseMappingId) {
				List<SectionSchedule> sectionSceduleList = sectionScheduleService
						.findBySectionId(section.getSectionId());
				String time = "";

				SectionReportScedule sectionReportScedule = new SectionReportScedule();
				CourseDetails courseDetails = courseDetailsService
						.findCourseDetailsByCourseId(courseSemMapping.getCourseId());
				sectionReportScedule.setCourseName(courseDetails.getCourseName());
				ProfessorDetails professor = professorService.findByProfessorId(section.getProfessorId());
				sectionReportScedule.setProdessorName(professor.getName());
				sectionReportScedule.setSectionNo(section.getSectionNo());
				ClassRoom classRoom = classRomService.findByRoomId(section.getRoomId());
				sectionReportScedule.setClassRoom(classRoom.getRoomName());

				for (SectionSchedule sectionSchdule : sectionSceduleList) {
					sectionSchdule.getWeekDay();
					sectionSchdule.getStartTime();
					sectionSchdule.getEndTime();
					String weekEnum = WeekEnum.getWeekEnum(sectionSchdule.getWeekDay());
					time = time + " " + weekEnum + " " + sectionSchdule.getStartTime() + "-"
							+ sectionSchdule.getEndTime();
					sectionReportScedule.setTime(time);

				}
				list.add(sectionReportScedule);
			}
		}
		sectionReportSceduleList.setSectionReportDchedule(list);
		return sectionReportSceduleList;
	}

}
