package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedBrandResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandController {
    private BrandService brandService; //Ioc

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest){
        return  this.brandService.add(createBrandRequest);
    }

    @GetMapping("/getById/{id}")
    public UpdatedBrandResponse getById(@PathVariable int id){
        return this.brandService.getById(id);
    }

    @GetMapping("/getAll")
    public List<UpdatedBrandResponse> getAll(){
        return this.brandService.getAll();
    }

    @PostMapping("/update/{id}")
    public UpdatedBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest updateBrandRequest){
        return this.brandService.update(id, updateBrandRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(int id){
        this.brandService.delete(id);
    }
}
