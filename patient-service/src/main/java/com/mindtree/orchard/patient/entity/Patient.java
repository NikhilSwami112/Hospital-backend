package com.mindtree.orchard.patient.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PatientPKId.class)
public class Patient {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	
	@Id
	private int visitedDoctor;
	
	private String patientName;
	private Date dateOfVisit;
	
//	@ColumnDefault("not yet visit")
	private String prescription;
}
