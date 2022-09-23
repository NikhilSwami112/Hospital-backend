package com.mindtree.orchard.patient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.orchard.patient.VO.Doctor;
import com.mindtree.orchard.patient.VO.ResponseTemplate;
import com.mindtree.orchard.patient.entity.Patient;
import com.mindtree.orchard.patient.repository.PatientRepository;

@Service
public class PatientServiceImpl {
	
	@Autowired
	private PatientRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;

	public List<Patient> getAllPatients() {
		// TODO Auto-generated method stub
		return this.repository.findAll();
	}

	public List<Patient> getPatient(int id) {
		// TODO Auto-generated method stub
		return this.repository.findAllByPatientId(id);
	}

	public void updatePatient(int id, int doctId, Patient pat) {
		pat.setPatientId(id);
		pat.setVisitedDoctor(id);
		String prescript= pat.getPrescription();
		// TODO Auto-generated method stub
		this.repository.update(id, doctId, prescript);
	}

//	public void deletePatient(int id) {
//		// TODO Auto-generated method stub
//		this.repository.deleteById(id);
//	}

	public Patient addPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.repository.saveAndFlush(patient);
	}

	public List<ResponseTemplate> getPatientDoctor(int id) {
		// TODO Auto-generated method stub
		List<ResponseTemplate> rest = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		
		List doctor = restTemplate.getForObject("http://localhost:8787/doctor/all", List.class);
		
		List<Doctor> listDoctor = mapper.convertValue(doctor,new TypeReference<List<Doctor>>() {
		});
		
		
		
		List<Patient> patient = this.getPatient(id);
		
		for(int i=0;i<patient.size();i++) {
			for(int j=0;j<listDoctor.size();j++) {
				if(listDoctor.get(j).getDoctId()==patient.get(i).getVisitedDoctor()) {
					rest.add(new ResponseTemplate(patient.get(i),listDoctor.get(j)));
				}
			}
		}
		
//		for(int i=0;i<listDoctor.size();i++) {
//			for(int j=0;j<patient.size();j++) {
//				if(listDoctor.get(i).getDoctId()==patient.get(j).getVisitedDoctor()) {
//					newListDoctor.add(listDoctor.get(i));
//				}
//			}
//		}
		
//		listDoctor = listDoctor.stream().filter((doct)-> doct.getDoctId() == patient.get(0).getVisitedDoctor()).collect(Collectors.toList());
		
//		rest.setPatient(patient.get(0));
//		rest.setDcotor(listDoctor);
		
		return rest;
	}

}
