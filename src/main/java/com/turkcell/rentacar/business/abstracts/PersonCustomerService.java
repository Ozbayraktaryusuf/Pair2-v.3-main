package com.turkcell.rentacar.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.turkcell.rentacar.business.dtos.requests.creates.CreatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPersonCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedPersonCustomerResponse;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.PersonalCustomer;

public interface PersonCustomerService {
	CreatedPersonCustomerResponse add(CreatePersonCustomerRequest createPersonCustomerRequest);
	List<UpdatedPersonCustomerResponse> getall();
	UpdatedPersonCustomerResponse getById(int id);
	void delete(int id);
	UpdatedPersonCustomerResponse update(int id,UpdatePersonCustomerRequest updatePersonCustomerRequest);
	PersonalCustomer getByIdForPersonRental(int id);
	Optional<PersonalCustomer> getByNationalNumberForPayment(String nationaNumber);
}
