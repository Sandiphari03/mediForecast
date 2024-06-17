package com.HealthCare.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.app.model.Doctor;
import com.HealthCare.app.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> findDoctorsByCity(String city) {
        return doctorRepository.findDoctorsByCity(city);
    }
}

