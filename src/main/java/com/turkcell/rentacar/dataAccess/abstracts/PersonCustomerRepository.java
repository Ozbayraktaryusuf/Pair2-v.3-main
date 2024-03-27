package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entities.concretes.PersonalCustomer;

public interface PersonCustomerRepository extends JpaRepository<PersonalCustomer, Integer>{
	Optional<PersonalCustomer> findByNationalNumber(String nationalNumber);

}
