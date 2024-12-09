package com.msu.DTO;
public class ClassroomDTO {
    private Integer id;
    private String roomName;

    public ClassroomDTO(Integer id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
