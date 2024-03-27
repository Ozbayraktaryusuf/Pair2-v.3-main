package com.turkcell.rentacar.business.rules;

import java.util.Optional;

import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.PersonCustomerRepository;
import com.turkcell.rentacar.entities.concretes.PersonalCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonCustomerBusinessRules {
	PersonCustomerRepository personCustomerRepository;
	
	 public void nationalIdCanNotBeDuplicated(String nationalId){
	        Optional<PersonalCustomer> personCustomer=this.personCustomerRepository.findByNationalNumber(nationalId);
	        if (personCustomer.isPresent()){
	            throw new BusinessException("Person Exists");
	        }
	    }
	    public void idIsNotExists(int id){
	        Optional<PersonalCustomer> personCustomer = this.personCustomerRepository.findById(id);
	        if (personCustomer.isEmpty()){
	            throw new BusinessException("Veri tabanında bu idye karşılık bir veri mevcut değil");
	        }
	    }
}
