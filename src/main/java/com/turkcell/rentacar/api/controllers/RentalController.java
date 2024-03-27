package com.turkcell.rentacar.api.controllers;

import java.util.List;

import com.turkcell.rentacar.business.abstracts.RentalForCorporateService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateRentalforCorporateRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforCorporateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.abstracts.RentalforPersonService;
import com.turkcell.rentacar.business.abstracts.RentalforPersonService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateRentalforPersonRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforPersonResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforPersonResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rental")
public class RentalController {
private RentalforPersonService rentalforPersonService;
private RentalForCorporateService rentalForCorporateService;

@PostMapping("/startRental/forPersonCustomer")
@ResponseStatus(HttpStatus.CREATED)
public CreateRentalforPersonResponse startRental(@RequestBody CreateRentalforPersonRequest createRentalRequest){
    return this.rentalforPersonService.startRentalforPersonCustomer( createRentalRequest);
}
@GetMapping("/getall/forPersonCustomer")
public List<CreateRentalforPersonResponse> getAllForPersonCustomer(){
    return this.rentalforPersonService.getAllForPersonCustomer();
}
@PostMapping("/finishRentalForPersonCustomer/{id}")
public CreateRentalforPersonResponse finishRentalForPersonCustomer(@PathVariable int id) {
    return this.rentalforPersonService.finishRentalForPersonCustomer(id);
}
    @PostMapping("/startRental/forCorporateCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalforCorporateResponse startRental(@RequestBody CreateRentalforCorporateRequest createRentalforCorporateRequest){
        return this.rentalForCorporateService.startRentalforCorporateCustomer(createRentalforCorporateRequest);
    }
    @GetMapping("/getall/forCorporateCustomer")
    public List<CreateRentalforCorporateResponse> getAllForCorporateCustomer(){
        return this.rentalForCorporateService.getAllForCorporateCustomer();
    }
    @PostMapping("/finishRentalForCorporateCustomer/{id}")
    public CreateRentalforCorporateResponse finishRentalForCorporateCustomer(@PathVariable int id) {
        return this.rentalForCorporateService.finishRentalForCorporateCustomer(id);
    }
}
