package com.turkcell.rentacar.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.turkcell.rentacar.business.dtos.requests.creates.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedCorporateCustomerResponse;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerService {
	CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	List<UpdatedCorporateCustomerResponse> getall();
	UpdatedCorporateCustomerResponse getById(int id);
	void delete(int id);
	UpdatedCorporateCustomerResponse update(int id,UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	CorporateCustomer getByIdForCorporateRental(int id);
	Optional<CorporateCustomer> getByTaxIdNumberForPayment(String taxIdNumber);
}
