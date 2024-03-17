package com.msu.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.entities.SectionSchedule;
import com.msu.repositories.SectionScheduleRepository;
import com.msu.services.SectionScheduleService;

@Service("SectionSchedule")
public class SectionScheduleServiceImpl implements SectionScheduleService {

	@Autowired
	SectionScheduleRepository sectionScheduleRepository;
	
	@Override
	public List<SectionSchedule> findAll() {
		
		return sectionScheduleRepository.findAll();
	}

	@Override
	public void saveSectionSchedule(SectionSchedule sectionSchedule) {
		sectionScheduleRepository.save(sectionSchedule);
		
	}

	@Override
	public List<SectionSchedule> findBySectionId(Long sectionId) {
		return sectionScheduleRepository.findBySectionId(sectionId);
		
	}
	
	

}
