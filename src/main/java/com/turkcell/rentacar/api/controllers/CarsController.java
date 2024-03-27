package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateCarRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateCarRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @AllArgsConstructor
    @RequestMapping("api/v1/cars")
    public class CarsController {
        private CarService carService;

        @PostMapping("/add")
        public CreatedCarResponse add(@Valid @RequestBody CreateCarRequest createCarRequest){
            return this.carService.add(createCarRequest);
        }

        @GetMapping("/getAll")
        public List<UpdatedCarResponse> getAll(){
            return this.carService.getAll();
        }

        @GetMapping("/getById/{id}")
        public UpdatedCarResponse getById(@PathVariable int id){
            return this.carService.getById(id);
        }

        @PostMapping("/update/{id}")
        public UpdatedCarResponse update(@PathVariable int id,@Valid @RequestBody UpdateCarRequest updatedCarRequest){
            return this.carService.update(id, updatedCarRequest);
        }

        @DeleteMapping("/delete/{id}")
        public void delete(@PathVariable int id){
            this.carService.delete(id);
        }

        @GetMapping("/getByModelName/{name}")
        public List<UpdatedCarResponse> getByModelName(@PathVariable String name){
            return this.carService.getByModelName(name);
        }
    }
