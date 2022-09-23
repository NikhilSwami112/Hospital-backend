package com.mindtree.orchard.patient.VO;

import java.util.List;

import com.mindtree.orchard.patient.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {
	
	private Patient patient;
	private Doctor doctor;

}
