package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.creates.CreateBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedBrandResponse;
import com.turkcell.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);
    List<UpdatedBrandResponse> getAll();
    UpdatedBrandResponse getById(int id);
    UpdatedBrandResponse update(int id, UpdateBrandRequest updateBrandRequest);
    void delete (int id);
    Brand getByName(String brandName);
}
