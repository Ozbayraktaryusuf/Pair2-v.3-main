package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateModelRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateModelRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedModelResponse;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Model;
import com.turkcell.rentacar.entities.concretes.Transmission;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;
    private final ModelBusinessRules modelBusinessRules;

    private final BrandService brandService;
    private final FuelService fuelService;
    private final TransmissionService transmissionService;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {

        this.modelBusinessRules.modelNameCanNotBeDuplicated(createModelRequest.getName());
        Brand brand = this.brandService.getByName(createModelRequest.getBrandName());
        Fuel fuel = this.fuelService.getByName(createModelRequest.getFuelName());
        Transmission transmission = this.transmissionService.getByName(createModelRequest.getTransmissionName());

        Model model = this.modelMapperService.forRequest().map(createModelRequest,Model.class);
        model.setCreatedDate(LocalDateTime.now());
        model.setBrand(brand);
        model.setFuel(fuel);
        model.setTransmission(transmission);
        this.modelRepository.save(model);
        CreatedModelResponse createdModelResponse = this.modelMapperService.forResponse().map(model,CreatedModelResponse.class);

        return createdModelResponse;
    }

    @Override
    public List<UpdatedModelResponse> getAll() {
        List<UpdatedModelResponse> updatedModelResponseList = new ArrayList<>();
        List<Model> models = this.modelRepository.findAll();

        for (Model model:models){
            UpdatedModelResponse updatedModelResponse = this.modelMapperService.forResponse().map(model,UpdatedModelResponse.class);
            updatedModelResponseList.add(updatedModelResponse);
        }
        return updatedModelResponseList;
    }

    @Override
    public UpdatedModelResponse getById(int id) {
        this.modelBusinessRules.idIsNotExists(id);
        Model model = this.modelRepository.findById(id).get();
        UpdatedModelResponse updatedModelResponse = this.modelMapperService.forResponse().map(model,UpdatedModelResponse.class);
        return updatedModelResponse;
    }

    @Override
    @Transactional
    public UpdatedModelResponse update(int id, UpdateModelRequest updateModelRequest) {
        this.modelBusinessRules.idIsNotExists(id);
        this.modelBusinessRules.modelNameCanNotBeDuplicated(updateModelRequest.getName());
        this.modelBusinessRules.modelNameCheck(id,updateModelRequest.getName());

        Brand brand = this.brandService.getByName(updateModelRequest.getBrandName());

        Model existsModel = this.modelRepository.findById(id).get();
        existsModel.setName(updateModelRequest.getName());
        existsModel.setUpdatedDate(LocalDateTime.now());
        existsModel.setBrand(brand);
        Model updatedModel = this.modelRepository.save(existsModel);
        UpdatedModelResponse updatedModelResponse = this.modelMapperService.forResponse().map(updatedModel,UpdatedModelResponse.class);
        return updatedModelResponse;
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.modelBusinessRules.idIsNotExists(id);
        Model model = this.modelRepository.findById(id).get();
        this.modelRepository.delete(model);
    }

    @Override
    public Model getByName(String name) {
        this.modelBusinessRules.modelNameIsNotExists(name);
        Model model = this.modelRepository.findByNameIgnoreCase(name).get();
        return model;
    }
}