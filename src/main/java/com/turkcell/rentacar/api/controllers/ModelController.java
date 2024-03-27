package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedModelResponse;
import com.turkcell.rentacar.entities.concretes.Model;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelController {
    private ModelService modelService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@Valid @RequestBody CreateModelRequest createModelRequest){
       return this.modelService.add(createModelRequest);
    }
    @GetMapping("/getById/{id}")
    public UpdatedModelResponse getById(@PathVariable int id){
        return this.modelService.getById(id);
    }

    @GetMapping("/getAll")
    public List<UpdatedModelResponse> getAll(){
        return this.modelService.getAll();
    }

    @PostMapping("/update/{id}")
    public UpdatedModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest updateModelRequest){
        return this.modelService.update(id, updateModelRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(int id){
        this.modelService.delete(id);
    }


}
