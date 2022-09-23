package com.mindtree.orchard.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.orchard.doctor.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	public Doctor findByDoctId(int id);
//	public List<Doctor> findAllByPatientId(int id);

}
