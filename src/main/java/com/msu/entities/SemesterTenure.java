package com.msu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "semester_tenure")
public class SemesterTenure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenure_id")
	private Integer tenureId;
	
    @Column(name = "weekly_tenure")
	private Integer weeklyTenure;
	
	public Integer getTenureId() {
		return tenureId;
	}
	public void setTenureId(Integer tenureId) {
		this.tenureId = tenureId;
	}
	public Integer getWeeklyTenure() {
		return weeklyTenure;
	}
	public void setWeeklyTenure(Integer weeklyTenure) {
		this.weeklyTenure = weeklyTenure;
	}
	
	
	
	

}
