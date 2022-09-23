package com.mindtree.orchard.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.orchard.doctor.entity.Doctor;
import com.mindtree.orchard.doctor.repository.DoctorRepository;

@Service
public class DoctorServiceImpl {
	
	@Autowired
	private DoctorRepository doctorRepository;

	public List<Doctor> getAllDoctors() {
		// TODO Auto-generated method stub
		return this.doctorRepository.findAll();
	}

	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		return this.doctorRepository.findByDoctId(id);
	}

	public Doctor addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		doctor.setDoctName("Dr."+doctor.getDoctName());
		return this.doctorRepository.saveAndFlush(doctor);
	}

	public Doctor updateDoctor(int id, Doctor doct) {
		// TODO Auto-generated method stub
		doct.setDoctId(id);
		return this.doctorRepository.save(doct);
	}

	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub
		this.doctorRepository.deleteById(id);
	}

//	public List<Doctor> getDoctorOfPatient(int patId) {
//		// TODO Auto-generated method stub
//		return this.doctorRepository.findAllByPatientId(patId);
//	}

}
