package com.msu.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class_room_schdule")
public class ClassRoomSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_schedule_id")
    private Long roomScheduleId;

    @Column(name="room_id")
    private int room_id;

    @Column(name="sem_id")
    private int sem_id;

    @Column(name = "schedule", columnDefinition = "TEXT")
    private String schedule;

	public Long getRoomScheduleId() {
		return roomScheduleId;
	}

	public void setRoomScheduleId(Long roomScheduleId) {
		this.roomScheduleId = roomScheduleId;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getSem_id() {
		return sem_id;
	}

	public void setSem_id(int sem_id) {
		this.sem_id = sem_id;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

    
}
