package com.turkcell.rentacar.business.rules;

import java.util.Optional;

import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CorporateCustomerRepository;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CorporateCustomerBusinessRules {
	CorporateCustomerRepository corporateCustomerRepository;
	 public void taxIdNumberCanNotBeDuplicated(String taxIdNumber){
	        Optional<CorporateCustomer> corporateCustomer=this.corporateCustomerRepository.findByTaxIdNumber(taxIdNumber);
	        if (corporateCustomer.isPresent()){
	            throw new BusinessException("Person Exists");
	        }
	    }
	    public void idIsNotExists(int id){
	        Optional<CorporateCustomer> corporateCustomer = this.corporateCustomerRepository.findById(id);
	        if (corporateCustomer.isEmpty()){
	            throw new BusinessException("Veri tabanında bu idye karşılık bir veri mevcut değil");
	        }
	    }
}
