package com.bharath.clinicals.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.clinicals.model.ClinicalData;
import com.bharath.clinicals.model.Patient;
import com.bharath.clinicals.repos.PatientRepository;


@RestController
@RequestMapping("api")
@CrossOrigin
public class PatientController {
	
	PatientRepository repository;
	Map<String,String> filters = new HashMap<>();
	
	@Autowired
	PatientController(PatientRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/patients")
	public List<Patient> getPatients() {
		System.out.println("== get all");
		return  repository.findAll();
	}
	
	@PostMapping(path = "/patients")
	public Patient AddPatient(@RequestBody Patient patient) {
		System.out.println("== adding");
		return  repository.save(patient);
	}
	
	@GetMapping(path = "/patients/{id}")
	public Patient getPatient(@PathVariable int id) {
		System.out.println("== get one");
		return repository.findById(id).get();
	}
	
	@PostMapping(path = "/patients/{id}")
	public Patient savePatient(@RequestBody Patient patient) {
		return repository.save(patient);
	}
	
	@GetMapping(path = "/patients/analyze/{id}")
	public Patient analyze(@PathVariable int id) {
		Patient patient = repository.findById(id).get();
		List<ClinicalData> clinicalData = patient.getClinicalData();
		ArrayList<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
		for (ClinicalData cd : duplicateClinicalData) {
			if (filters.containsKey(cd.getComponentName())) {
				clinicalData.remove(cd);
				continue;
			} else {
				filters.put(cd.getComponentName(),cd.getComponentValue());
			}
			if (cd.getComponentName().equals("hw")) {
				String[] heightAndWeight = cd.getComponentValue().split("/");
				if (heightAndWeight!=null && heightAndWeight.length == 2) {
					float heightInMeters = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
					float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMeters * heightInMeters);
					ClinicalData bmiData = new ClinicalData();
					bmiData.setComponentName("bmi");
					bmiData.setComponentValue(String.valueOf(bmi));
					bmiData.setPatient(patient);
					bmiData.setMeasuredDateTime(new Timestamp(new Date().getTime()));
					clinicalData.add(bmiData);
				}
			}
		}
		filters.clear();
		return patient;
		
	}

}
