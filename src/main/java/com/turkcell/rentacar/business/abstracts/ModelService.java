package com.turkcell.rentacar.business.abstracts;


import com.turkcell.rentacar.business.dtos.requests.creates.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedModelResponse;
import com.turkcell.rentacar.entities.concretes.Model;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreateModelRequest createModelRequest);
    List<UpdatedModelResponse> getAll();
    UpdatedModelResponse getById(int id);
    UpdatedModelResponse update(int id, UpdateModelRequest updateModelRequest);
    void delete (int id);
    Model getByName(String name);
}

