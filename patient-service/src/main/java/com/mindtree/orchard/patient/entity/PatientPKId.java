package com.mindtree.orchard.patient.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PatientPKId implements Serializable{
	
	private int patientId;
	private int visitedDoctor;

}
