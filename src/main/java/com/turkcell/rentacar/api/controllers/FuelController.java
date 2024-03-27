package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedFuelResponse;
import com.turkcell.rentacar.entities.concretes.Fuel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/fuels")
@AllArgsConstructor
public class FuelController {
    private FuelService fuelService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@Valid @RequestBody CreateFuelRequest createFuelRequest){
        return this.fuelService.add(createFuelRequest);
    }

    @GetMapping("/getById/{id}")
    public UpdatedFuelResponse getById(@PathVariable int id){
        return this.fuelService.getById(id);
    }

    @GetMapping("/getAll")
    public List<UpdatedFuelResponse> getAll(){
        return this.fuelService.getAll();
    }

    @PostMapping("/update/{id}")
    @Transactional
    public UpdatedFuelResponse update(@PathVariable int id, @RequestBody UpdateFuelRequest updateFuelRequest){
        return this.fuelService.update(id, updateFuelRequest);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void delete(int id){
        this.fuelService.delete(id);
    }
}

