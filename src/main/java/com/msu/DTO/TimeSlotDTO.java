package com.msu.DTO;

import java.time.LocalTime;

public class TimeSlotDTO {

    private int days;
    //	private String startTime;
//	private String endTime;
    private LocalTime startTime;
    private LocalTime endTime;


    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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
}
