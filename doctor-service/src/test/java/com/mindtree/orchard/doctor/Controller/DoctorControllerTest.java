package com.mindtree.orchard.doctor.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.orchard.doctor.controller.DoctorController;
import com.mindtree.orchard.doctor.entity.Doctor;
import com.mindtree.orchard.doctor.service.DoctorServiceImpl;


@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {DoctorControllerTest.class})
@ComponentScan(basePackages="com.mindtree.orchard.doctor")
public class DoctorControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@Mock
	DoctorServiceImpl impl;
	
	List<Doctor> doctors;
	
	@InjectMocks
	DoctorController doctorController;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
	}
	
	@Test
	@Order(1)
	public void test_getAllDoctors() throws Exception {
		doctors = new ArrayList<Doctor>();
		doctors.add(new Doctor(1,"gyan prakash","male",23,"dentist",0));
		doctors.add(new Doctor(2,"prakash raj","male",23,"general medicine",0));
		when(impl.getAllDoctors()).thenReturn(doctors);
		
		this.mockMvc.perform(get("/doctor/all"))
		.andExpect(status().isOk());
//		.andDo(print());
//		
//		ResponseEntity<List<Doctor>> res = doctorController.getAllDoctors();
//		
//		assertEquals(HttpStatus.FOUND,res.getStatusCode());
//		assertEquals(res.getBody().size(),2);
	}
	
	
	@Test
	@Order(2)
	public void test_getDoctor() throws Exception {
		Doctor doct = new Doctor(2,"prakash raj","male",23,"general medicine",0);
		int id=2;
		when(impl.getDoctor(id)).thenReturn(doct);
		
		this.mockMvc.perform(get("/doctor/{id}",id))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(".doctId").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".doctName").value("prakash raj"))
		.andExpect(MockMvcResultMatchers.jsonPath(".gender").value("male"))
		.andExpect(MockMvcResultMatchers.jsonPath(".age").value(23))
		.andExpect(MockMvcResultMatchers.jsonPath(".specialist").value("general medicine"))
		.andExpect(MockMvcResultMatchers.jsonPath(".totalPatientAttended").value(0));
		
//		ResponseEntity<Object> res = doctorController.getDoctor(id);
//		
//		assertEquals(HttpStatus.FOUND,res.getStatusCode());
//		assertEquals(res.getBody(),doct);
	}
	
	@Test
	@Order(3)
	public void test_addDoctor() throws Exception {
		
		Doctor doct = new Doctor(3,"ram kumar","male",23,"general surgion",0);
		when(impl.addDoctor(doct)).thenReturn(doct);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(doct);
		
		this.mockMvc.perform(post("/doctor/add")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isCreated())
		.andDo(print());
		
//		ResponseEntity<Doctor> res = doctorController.addDoctor(doct);
//		
//		assertEquals(res.getStatusCode(),HttpStatus.CREATED );
//		assertEquals(res.getBody(), doct);
//		assertEquals(res.getBody().getDoctId(),doct.getDoctId() );
	}
	
	
	@Test
	@Order(4)
	public void test_updateDoctor() throws Exception {
		Doctor doct = new Doctor(3,"kumar singh","male",24,"general surgion",2);
		int doctId=3;
		
		when(impl.updateDoctor(doctId, doct)).thenReturn(doct);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonbody = mapper.writeValueAsString(doct);
		
		this.mockMvc.perform(put("/doctor/update/{id}",doctId )
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath(".doctId").value(3))
		.andExpect(MockMvcResultMatchers.jsonPath(".doctName").value("kumar singh"))
		.andExpect(MockMvcResultMatchers.jsonPath(".gender").value("male"))
		.andExpect(MockMvcResultMatchers.jsonPath(".age").value(24))
		.andExpect(MockMvcResultMatchers.jsonPath(".specialist").value("general surgion"))
		.andExpect(MockMvcResultMatchers.jsonPath(".totalPatientAttended").value(2));
		

		
//		ResponseEntity<Doctor> res = doctorController.updateDoctor(doctId, doct);
//		
//		assertEquals(res.getStatusCode(),HttpStatus.CREATED );
//		assertEquals(res.getBody(), doct);
//		assertEquals(res.getBody().getTotalPatientAttended(),doct.getTotalPatientAttended());
		
	}
	
}
