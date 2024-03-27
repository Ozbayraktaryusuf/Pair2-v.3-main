package com.turkcell.rentacar.business.abstracts;


import com.turkcell.rentacar.business.dtos.requests.creates.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedTransmissionResponse;
import com.turkcell.rentacar.entities.concretes.Transmission;

import java.util.List;

public interface TransmissionService {
    CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest);
    List<UpdatedTransmissionResponse> getAll();
    UpdatedTransmissionResponse getById(int id);
    UpdatedTransmissionResponse update(int id, UpdateTransmissionRequest updateTransmissionRequest);
    void delete (int id);
    Transmission getByName(String transmissionName);
}
