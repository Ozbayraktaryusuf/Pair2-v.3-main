package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.PersonCustomerService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdatePersonCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPersonCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedPersonCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/person-customers")
public class PersonCustomerController {
    private PersonCustomerService personCustomerService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPersonCustomerResponse add(@Valid  @RequestBody CreatePersonCustomerRequest createPersonCustomerRequest){
        return  this.personCustomerService.add(createPersonCustomerRequest);
    }
    @GetMapping("/getAll")
    public List<UpdatedPersonCustomerResponse> getall(){
        return personCustomerService.getall();
    }
    @GetMapping("/getById/{id}")
    public UpdatedPersonCustomerResponse getById(@PathVariable  int id){
        return personCustomerService.getById(id);
    }
    @PostMapping("update/{id}")
    public UpdatedPersonCustomerResponse update(@PathVariable int id,@Valid @RequestBody UpdatePersonCustomerRequest updatePersonCustomerRequest) {
        return this.personCustomerService.update(id,updatePersonCustomerRequest);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.personCustomerService.delete(id);
    }
}
