package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.creates.CreateCarRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateCarRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedCarResponse;
import com.turkcell.rentacar.entities.concretes.Car;

import java.util.List;

public interface CarService {

    CreatedCarResponse add(CreateCarRequest createCarRequest);
    List<UpdatedCarResponse> getAll();
    UpdatedCarResponse getById(int id);
    UpdatedCarResponse update(int id, UpdateCarRequest updateCarRequest);
    void delete(int id);
    List<UpdatedCarResponse> getByModelName(String name);
    //getall , getById, delete,update
    Car getByIdForMaintenance(int id);
    Car getByIdForRental(int id);
    Car getByIdForPayment(int id);
}
