package com.bharath.springdata.associations.onetoone.repos;

import org.springframework.data.repository.CrudRepository;

import com.bharath.springdata.associationsonetoone.entities.License;

public interface LicenseRepository extends CrudRepository<License, Long> {

}
