package com.mindtree.orchard.patient.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.orchard.patient.VO.Doctor;
import com.mindtree.orchard.patient.VO.ResponseTemplate;
import com.mindtree.orchard.patient.contoller.PatientController;
import com.mindtree.orchard.patient.entity.Patient;
import com.mindtree.orchard.patient.service.PatientServiceImpl;

@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { PatientControllerTest.class })
@ComponentScan(basePackages = "com.mindtree.orchard.patient")
public class PatientControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	PatientServiceImpl impl;

	List<Patient> patients;
	
	List<ResponseTemplate> responseTemplate;

	@InjectMocks
	PatientController patientController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
	}

	@Test
	@Order(1)
	public void test_getAllPatients() throws Exception {
		patients = new ArrayList<Patient>();
		patients.add(new Patient(1, 1, "gya prakash", new Date(2022 - 01 - 04), "yet to visit"));
		patients.add(new Patient(1, 2, "gya prakash", new Date(2022 - 01 - 04), "yet to visit"));
		patients.add(new Patient(1, 3, "gya prakash", new Date(2022 - 01 - 04), "yet to visit"));
		patients.add(new Patient(2, 1, "prakash raj", new Date(2021 - 01 - 04), "yet to visit"));

		when(impl.getAllPatients()).thenReturn(patients);

		this.mockMvc.perform(get("/patient/all"))
		.andExpect(status().isOk());

	}

	@Test
	@Order(2)
	public void test_getPatient() throws Exception {
		patients = new ArrayList<Patient>();
		patients.add(new Patient(1, 1, "gya prakash", new Date(2022 - 01 - 04), "yet to visit"));
		patients.add(new Patient(1, 2, "gya prakash", new Date(2022 - 01 - 04), "yet to visit"));
		patients.add(new Patient(1, 3, "gya prakash", new Date(2022 - 01 - 04), "yet to visit"));

		int id = 1;

		when(impl.getPatient(id)).thenReturn(patients);

		this.mockMvc.perform(get("/patient/{id}",id))
//		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(3)
	public void test_getPatientDoctor() throws Exception {
		responseTemplate= new ArrayList<>();
		ResponseTemplate rest = new ResponseTemplate();
		rest.setDoctor(new Doctor(1,"gyan prakash","male",23,"dentist",0));
		rest.setPatient(new Patient(1, 1, "gya prakash", new Date(2022 - 01 - 04), "yet to visit"));
		responseTemplate.add(rest);
		
		int id=1;
		when(impl.getPatientDoctor(id)).thenReturn(responseTemplate);
		
		this.mockMvc.perform(get("/patient/doctor/{id}",id))
//		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	public void test_addPatient() throws Exception {
		
		Patient pat = new Patient(2, 1, "gya prakash", new Date(2022 - 01 - 04), "yet to visit");
		when(impl.addPatient(pat)).thenReturn(pat);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(pat);
		
		this.mockMvc.perform(post("/patient/add")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isCreated())
//		.andDo(print())
		.andExpect(MockMvcResultMatchers.jsonPath(".patientId").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".visitedDoctor").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath(".patientName").value("gya prakash"))
		.andExpect(MockMvcResultMatchers.jsonPath(".dateOfVisit").value(2022-01-04))
		.andExpect(MockMvcResultMatchers.jsonPath(".prescription").value("yet to visit"));
		
	}
	
	@Test
	@Order(5)
	public void test_updatePatient() throws Exception {
		
		Patient pat = new Patient(2, 1, "prakash kumar", new Date(2022 - 01 - 04), "visited");
		when(impl.addPatient(pat)).thenReturn(null);
		
		int id =2;
		int doctId=3;
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(pat);
		
		this.mockMvc.perform(put("/patient/update/{id}/doctor/{doctId}",id,doctId)
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isCreated());
		
	}

}
