package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforPersonResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPersonCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedPersonCustomerResponse;
import com.turkcell.rentacar.business.rules.CorporateCustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CorporateCustomerRepository;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;
import com.turkcell.rentacar.entities.concretes.PersonalCustomer;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private ModelMapperService modelMapperService;
    private CorporateCustomerRepository corporateCustomerRepository;
    private CorporateCustomerBusinessRules corporateCustomerBusinessRules;
    @Override
    public CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        this.corporateCustomerBusinessRules.taxIdNumberCanNotBeDuplicated(createCorporateCustomerRequest.getTaxIdNumber());
        CorporateCustomer corporateCustomer =this.modelMapperService.forRequest().map(createCorporateCustomerRequest,CorporateCustomer.class);
        corporateCustomer.setTaxIdNumber(createCorporateCustomerRequest.getTaxIdNumber());
        corporateCustomer.setCreatedDate(LocalDateTime.now());
        CorporateCustomer createdCorporateCustomer =this.corporateCustomerRepository.save(corporateCustomer);

        CreatedCorporateCustomerResponse createdPersonCustomerResponse=this.modelMapperService.forResponse().map(createdCorporateCustomer,CreatedCorporateCustomerResponse.class);
        return createdPersonCustomerResponse;
    }

    @Override
    public List<UpdatedCorporateCustomerResponse> getall() {
        List<UpdatedCorporateCustomerResponse> updatedCorporateCustomerResponseList =new ArrayList<>();
        List<CorporateCustomer> corporateCustomerList =this.corporateCustomerRepository.findAll();
        for (CorporateCustomer corporateCustomer:corporateCustomerList){
            UpdatedCorporateCustomerResponse updatedCorporateCustomerResponse=this.modelMapperService.forResponse().map(corporateCustomer,UpdatedCorporateCustomerResponse.class);
            updatedCorporateCustomerResponseList.add(updatedCorporateCustomerResponse);
        }
        return updatedCorporateCustomerResponseList;
    }

    @Override
    public UpdatedCorporateCustomerResponse getById(int id) {
        this.corporateCustomerBusinessRules.idIsNotExists(id);
        return this.modelMapperService.forResponse().map(this.corporateCustomerRepository.findById(id),UpdatedCorporateCustomerResponse.class);
    }

    @Override
    public void delete(int id) {
        this.corporateCustomerBusinessRules.idIsNotExists(id);
        this.corporateCustomerRepository.deleteById(id);
    }

    @Override
    public UpdatedCorporateCustomerResponse update(int id,UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        this.corporateCustomerBusinessRules.idIsNotExists(id);
        CorporateCustomer existsCorporateCustomer =this.corporateCustomerRepository.findById(id).get();
        existsCorporateCustomer.setTaxIdNumber(updateCorporateCustomerRequest.getTaxIdNumber());
        existsCorporateCustomer.setCorporateName(updateCorporateCustomerRequest.getCorporateName());
        existsCorporateCustomer.setUpdatedDate(LocalDateTime.now());
        CorporateCustomer corporateCustomer=this.corporateCustomerRepository.save(existsCorporateCustomer);

        UpdatedCorporateCustomerResponse updatedCorporateCustomerResponse=this.modelMapperService.forResponse().map(corporateCustomer,UpdatedCorporateCustomerResponse.class);
        return updatedCorporateCustomerResponse;
    }

    @Override
    public CorporateCustomer getByIdForCorporateRental(int id) {
        CorporateCustomer corporateCustomer=this.corporateCustomerRepository.findById(id).get();
        return corporateCustomer;
    }

    @Override
    public Optional<CorporateCustomer> getByTaxIdNumberForPayment(String taxIdNumber) {
        Optional<CorporateCustomer> corporateCustomer=this.corporateCustomerRepository.findByTaxIdNumber(taxIdNumber);
        return corporateCustomer;
    }

}
