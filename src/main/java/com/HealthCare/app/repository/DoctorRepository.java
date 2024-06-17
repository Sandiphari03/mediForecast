package com.HealthCare.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.app.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findDoctorsByCity(String city);
}

