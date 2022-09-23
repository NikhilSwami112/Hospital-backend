package com.mindtree.orchard.patient.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.orchard.patient.entity.Patient;
import com.mindtree.orchard.patient.entity.PatientPKId;


@Transactional
@Repository
public interface PatientRepository extends JpaRepository<Patient, PatientPKId> {

	public List<Patient> findAllByPatientId(int id);
	
	@Modifying
	@Query(value="update patient set prescription=:p where patient_id=:id and visited_doctor=:doctId",nativeQuery = true)
	public void update(@Param("id")int id,@Param("doctId") int doctId,@Param("p") String p);
}
