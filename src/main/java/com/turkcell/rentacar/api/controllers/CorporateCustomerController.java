package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.creates.CreatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPersonCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedPersonCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/corporate-customers")
public class CorporateCustomerController {
    private CorporateCustomerService corporateCustomerService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCorporateCustomerResponse add(@Valid @RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest){
        return  this.corporateCustomerService.add(createCorporateCustomerRequest);
    }
    @GetMapping("/getAll")
    public List<UpdatedCorporateCustomerResponse> getall(){
        return corporateCustomerService.getall();
    }
    @GetMapping("/getById/{id}")
    public UpdatedCorporateCustomerResponse getById(@PathVariable  int id){
        return corporateCustomerService.getById(id);
    }
    @PostMapping("update/{id}")
    public UpdatedCorporateCustomerResponse update(@PathVariable int id,@Valid @RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest)
    {
        return this.corporateCustomerService.update(id,updateCorporateCustomerRequest);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.corporateCustomerService.delete(id);
    }
}

