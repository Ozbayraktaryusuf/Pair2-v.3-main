package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.PersonCustomerService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPersonCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedPersonCustomerResponse;
import com.turkcell.rentacar.business.rules.PersonCustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.PersonCustomerRepository;
import com.turkcell.rentacar.entities.concretes.PersonalCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonCustomerManager implements PersonCustomerService {
    private PersonCustomerRepository personCustomerRepository;
    private ModelMapperService modelMapperService;
    private PersonCustomerBusinessRules personCustomerBusinessRules;

    @Override
    public CreatedPersonCustomerResponse add(CreatePersonCustomerRequest createPersonCustomerRequest) {
        this.personCustomerBusinessRules.nationalIdCanNotBeDuplicated(createPersonCustomerRequest.getNationalNumber());
        PersonalCustomer personalCustomer =this.modelMapperService.forRequest().map(createPersonCustomerRequest,PersonalCustomer.class);
        personalCustomer.setNationalNumber(createPersonCustomerRequest.getNationalNumber());
        personalCustomer.setCreatedDate(LocalDateTime.now());
        PersonalCustomer createdPersonalCustomer=this.personCustomerRepository.save(personalCustomer);

        CreatedPersonCustomerResponse createdPersonCustomerResponse=this.modelMapperService.forResponse().map(createdPersonalCustomer,CreatedPersonCustomerResponse.class);
        return createdPersonCustomerResponse;
    }

    @Override
    public List<UpdatedPersonCustomerResponse> getall() {
        List<UpdatedPersonCustomerResponse> updatedPersonCustomerResponseList =new ArrayList<>();
        List<PersonalCustomer> personalCustomerList =this.personCustomerRepository.findAll();
        for (PersonalCustomer personCustomer:personalCustomerList){
            UpdatedPersonCustomerResponse updatedPersonCustomerResponse=this.modelMapperService.forResponse().map(personCustomer,UpdatedPersonCustomerResponse.class);
            updatedPersonCustomerResponseList.add(updatedPersonCustomerResponse);
        }
        return updatedPersonCustomerResponseList;
    }

    @Override
    public UpdatedPersonCustomerResponse getById(int id) {
        this.personCustomerBusinessRules.idIsNotExists(id);
        return this.modelMapperService.forResponse().map(this.personCustomerRepository.findById(id),UpdatedPersonCustomerResponse.class);
    }

    @Override
    public UpdatedPersonCustomerResponse update(int id,UpdatePersonCustomerRequest updatePersonCustomerRequest) {
        this.personCustomerBusinessRules.idIsNotExists(id);
        PersonalCustomer existsPersonCustomer =this.personCustomerRepository.findById(id).get();
        existsPersonCustomer.setFirstName(updatePersonCustomerRequest.getFirstName());
        existsPersonCustomer.setLastName(updatePersonCustomerRequest.getLastName());
        existsPersonCustomer.setUpdatedDate(LocalDateTime.now());
        PersonalCustomer updatedPersonCustomer=this.personCustomerRepository.save(existsPersonCustomer);

        UpdatedPersonCustomerResponse updatedPersonCustomerResponse=this.modelMapperService.forResponse().map(updatedPersonCustomer,UpdatedPersonCustomerResponse.class);
        return updatedPersonCustomerResponse;

    }

    @Override
    public void delete(int id) {
        this.personCustomerBusinessRules.idIsNotExists(id);
        this.personCustomerRepository.deleteById(id);
    }

	@Override
	public PersonalCustomer getByIdForPersonRental(int id) {
		PersonalCustomer personalCustomer =this.personCustomerRepository.findById(id).get();
		return personalCustomer;
	}

    @Override
    public Optional<PersonalCustomer> getByNationalNumberForPayment(String nationalNumber) {
        Optional<PersonalCustomer> personalCustomer =this.personCustomerRepository.findByNationalNumber(nationalNumber);
        return personalCustomer;
    }

}
