package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.ExtraServicesService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateExtraServiceRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateExtraServicesRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.getAlls.GetAllExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.getById.GetByIdExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdateExtraServicesResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/extra-services")
public class ExtraServicesController {
    private ExtraServicesService extraServicesService;
    @PostMapping("/add")
    public CreateExtraServicesResponse add(@Valid @RequestBody CreateExtraServiceRequest createExtraServiceRequest){
        return this.extraServicesService.add(createExtraServiceRequest);
    }
    @GetMapping("/getAll")
    public List<GetAllExtraServicesResponse> getAll(){
        return this.extraServicesService.getAll();
    }
    @GetMapping("/getById/{id}")
    public GetByIdExtraServicesResponse getById(@PathVariable int id){
        return this.extraServicesService.getById(id);
    }
    @PostMapping("/update/{id}")
    public UpdateExtraServicesResponse update(@PathVariable int id, @Valid @RequestBody UpdateExtraServicesRequest updateExtraServicesRequest){
        return this.extraServicesService.update(id,updateExtraServicesRequest);
    }
    @DeleteMapping("/delete/{id}")
    public  void delete(@PathVariable int id){
        this.extraServicesService.delete(id);
    }
}
