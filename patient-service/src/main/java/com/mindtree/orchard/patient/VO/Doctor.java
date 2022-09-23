package com.mindtree.orchard.patient.VO;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	
	private int doctId;
	private String doctName;
	private String gender;
	private int age;
	private String specialist;
	private int totalPatientAttended;
	
}
