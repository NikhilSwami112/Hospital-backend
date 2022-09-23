package com.mindtree.orchard.patient.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.orchard.patient.VO.ResponseTemplate;
import com.mindtree.orchard.patient.entity.Patient;
import com.mindtree.orchard.patient.service.PatientServiceImpl;

@RequestMapping("/patient")
@RestController
public class PatientController {

	@Autowired
	private PatientServiceImpl impl;

	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> allPatients = this.impl.getAllPatients();
		if (allPatients.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(allPatients);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Patient>> getPatient(@PathVariable("id") int id) {
		List<Patient> patient = this.impl.getPatient(id);
		if (patient.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}

	@GetMapping("/doctor/{id}")
	public ResponseEntity<List<ResponseTemplate>> getPatientDoctor(@PathVariable("id") int id) {
		List<ResponseTemplate> patientDoctor = this.impl.getPatientDoctor(id);
		if (patientDoctor.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(patientDoctor);
	}

	@PostMapping("/add")
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
		try {
			Patient addPatient = this.impl.addPatient(patient);
			return ResponseEntity.status(HttpStatus.CREATED).body(addPatient);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	@PutMapping("/update/{id}/doctor/{doctId}")
	public ResponseEntity<Void> updatePatient(@PathVariable("id") int id, @PathVariable("doctId") int doctId,
			@RequestBody Patient pat) {
		try {			
			this.impl.updatePatient(id, doctId, pat);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	@DeleteMapping("/delete/{id}")
//	public void deletePatient(@PathVariable("id") int id) {
//		this.impl.deletePatient(id);
//	}
}
