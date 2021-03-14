package com.bharath.springdata.patientscheduling;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springdata.patientscheduling.entities.Appointment;
import com.bharath.springdata.patientscheduling.entities.Doctor;
import com.bharath.springdata.patientscheduling.entities.Insurance;
import com.bharath.springdata.patientscheduling.entities.Patient;
import com.bharath.springdata.patientscheduling.repos.AppointmentRepository;
import com.bharath.springdata.patientscheduling.repos.DoctorRepository;
import com.bharath.springdata.patientscheduling.repos.PatientRepository;

@SpringBootTest
class PatientschedulingApplicationTests {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Test
	public void testCreateDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("John");
		doctor.setLastName("Blues");
		doctor.setSpeciality("all");
		doctorRepository.save(doctor);
	}
	
	@Test
	public void testCreatePatient() {
		Patient patient = new Patient();
		patient.setFirstName("Doug");
		patient.setLastName("Smithers");
		patient.setPhone("5155552222");
		
		Insurance insurance = new Insurance();
		insurance.setCopay(20);
		insurance.setProviderName("Blue Cross");
		patient.setInsurance(insurance);
		
		Doctor doctor = doctorRepository.findById(1L).get();
		patient.getDoctors().add(doctor);
		
		patientRepository.save(patient);	
	}
	@Test
	public void testCreateAppointment() {
		Doctor doctor = doctorRepository.findById(1L).get();
		Patient patient = patientRepository.findById(1L).get();
		Appointment appointment = new Appointment();
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setAppointmentTime(new Timestamp(new Date().getTime()));
		appointment.setReason("I have a problem");
		appointment.setStarted(true);
		appointmentRepository.save(appointment);
	}
}
