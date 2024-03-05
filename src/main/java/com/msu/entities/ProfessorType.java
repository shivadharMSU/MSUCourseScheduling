package com.msu.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "professor_type")
public class ProfessorType {

    @Id
    private Integer id;

    @Column(name = "type")
    private String type;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
