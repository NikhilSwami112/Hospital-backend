package com.mindtree.orchard.doctor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mindtree.orchard.doctor.entity.Doctor;
import com.mindtree.orchard.doctor.service.DoctorServiceImpl;

@RequestMapping("/doctor")
@RestController
public class DoctorController {
	
	@Autowired
	private DoctorServiceImpl impl;
	
	@GetMapping("/all")
	public ResponseEntity<List<Doctor>> getAllDoctors(){
		List<Doctor> allDoctors = this.impl.getAllDoctors();
		if(allDoctors.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(allDoctors);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Doctor> getDoctor(@PathVariable("id") int id) {
		Doctor doctor = this.impl.getDoctor(id);
		if (doctor == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}
	
//	@GetMapping("/patient/{patId}")
//	public List<Doctor> getDoctorOfPatient(@PathVariable("patId") int patId){
//		return this.impl.getDoctorOfPatient(patId);
//	}
	
	
	@PostMapping("/add")
	public  ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		try {
			Doctor addDoctor = this.impl.addDoctor(doctor);
			return ResponseEntity.status(HttpStatus.CREATED).body(addDoctor);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") int id,@RequestBody Doctor doct) {
		Doctor updateDoctor = this.impl.updateDoctor(id,doct);
		if(updateDoctor==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(updateDoctor);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public void deleteDoctor(@PathVariable("id") int id) {
//		this.impl.deleteDoctor(id);
//	}
}
