package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import com.turkcell.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer>{
	Optional<CorporateCustomer> findByTaxIdNumber(String taxIdNumber);

}
