package com.msu.DTO;

import java.time.LocalTime;
import java.util.List;

public class ClassroomReportResponseDTO {

    private int semesterId;
    private List<ClassroomDTO> classrooms;

    // Constructor
    public ClassroomReportResponseDTO(int semesterId, List<ClassroomDTO> classrooms) {
        this.semesterId = semesterId;
        this.classrooms = classrooms;
    }

    // Getters and Setters
    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public List<ClassroomDTO> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<ClassroomDTO> classrooms) {
        this.classrooms = classrooms;
    }

    // Nested DTO for Classroom
    public static class ClassroomDTO {
        private Long roomId;
        private String roomName;
        private List<ScheduleDTO> schedule;

        // Constructor
        public ClassroomDTO(Long roomId, String roomName, List<ScheduleDTO> schedule) {
            this.roomId = roomId;
            this.roomName = roomName;
            this.schedule = schedule;
        }

        // Getters and Setters
        public Long getRoomId() {
            return roomId;
        }

        public void setRoomId(Long roomId) {
            this.roomId = roomId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public List<ScheduleDTO> getSchedule() {
            return schedule;
        }

        public void setSchedule(List<ScheduleDTO> schedule) {
            this.schedule = schedule;
        }
    }

    // Nested DTO for Schedule
    public static class ScheduleDTO {
        private String weekDay;
        private LocalTime startTime;
        private LocalTime endTime;
        private String courseName;
        private String sectionNo;
        private String professorName;

        // Constructor
        public ScheduleDTO(String weekDay, LocalTime startTime, LocalTime endTime,
                           String courseName, String sectionNo, String professorName) {
            this.weekDay = weekDay;
            this.startTime = startTime;
            this.endTime = endTime;
            this.courseName = courseName;
            this.sectionNo = sectionNo;
            this.professorName = professorName;
        }

        // Getters and Setters
        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getSectionNo() {
            return sectionNo;
        }

        public void setSectionNo(String sectionNo) {
            this.sectionNo = sectionNo;
        }

        public String getProfessorName() {
            return professorName;
        }

        public void setProfessorName(String professorName) {
            this.professorName = professorName;
        }
    }
}
