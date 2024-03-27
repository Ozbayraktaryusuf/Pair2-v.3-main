package com.turkcell.rentacar.business.abstracts;


import com.turkcell.rentacar.business.dtos.requests.creates.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedFuelResponse;
import com.turkcell.rentacar.entities.concretes.Fuel;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest createFuelRequest);
    List<UpdatedFuelResponse> getAll();
    UpdatedFuelResponse getById(int id);

    UpdatedFuelResponse update(int id, UpdateFuelRequest updateFuelRequest);
    void delete (int id);
    Fuel getByName(String fuelName);
}
