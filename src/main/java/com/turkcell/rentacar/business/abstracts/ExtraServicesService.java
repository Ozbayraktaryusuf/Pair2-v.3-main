package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.creates.CreateExtraServiceRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateExtraServicesRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.getAlls.GetAllExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.getById.GetByIdExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdateExtraServicesResponse;
import com.turkcell.rentacar.entities.concretes.ExtraServices;

import java.util.List;
import java.util.Optional;

public interface ExtraServicesService {
    CreateExtraServicesResponse add(CreateExtraServiceRequest createExtraServiceRequest);

    List<GetAllExtraServicesResponse> getAll();

    GetByIdExtraServicesResponse getById(int id);

    UpdateExtraServicesResponse update(int id,UpdateExtraServicesRequest UpdateExtraServicesRequest);

    void delete(int id);

    ExtraServices getByIdForPayment(int id);
}
