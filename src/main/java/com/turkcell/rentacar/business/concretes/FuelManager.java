package com.turkcell.rentacar.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedFuelResponse;
import com.turkcell.rentacar.business.rules.FuelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
    private  FuelRepository fuelRepository;
    private  ModelMapperService modelMapperService;
    private  FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest)
    {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(createFuelRequest.getName());
        Fuel fuel=this.modelMapperService.forRequest().map(createFuelRequest,Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel existsFuel=this.fuelRepository.save(fuel);
        CreatedFuelResponse createdFuelResponse=this.modelMapperService.forResponse().map(existsFuel,CreatedFuelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public UpdatedFuelResponse getById(int id) {
        this.fuelBusinessRules.idIsNotExists(id);
        Fuel fuel = this.fuelRepository.findById(id).get();
        UpdatedFuelResponse updatedFuelResponse = this.modelMapperService.forResponse().map(fuel,UpdatedFuelResponse.class);
        return updatedFuelResponse;
    }

    @Override
    public List<UpdatedFuelResponse> getAll() {
        List<UpdatedFuelResponse> fuelResponseList = new ArrayList<>();
        List<Fuel> fuels = this.fuelRepository.findAll();
        for (Fuel fuel:fuels){
            UpdatedFuelResponse updatedFuelResponse = this.modelMapperService.forResponse().map(fuel,UpdatedFuelResponse.class);
            fuelResponseList.add(updatedFuelResponse);
        }
        return fuelResponseList;
    }

    @Override
    @Transactional
    public UpdatedFuelResponse update(int id, UpdateFuelRequest updateFuelRequest) {
        this.fuelBusinessRules.idIsNotExists(id);
        this.fuelBusinessRules.fuelNameCanNotBeDuplicated(updateFuelRequest.getName());

        Fuel existsFuel = this.fuelRepository.findById(id).get();
        existsFuel.setName(updateFuelRequest.getName());
        existsFuel.setUpdatedDate(LocalDateTime.now());
        Fuel updatedFuel = this.fuelRepository.save(existsFuel);
        UpdatedFuelResponse updatedFuelResponse = this.modelMapperService.forResponse().map(updatedFuel,UpdatedFuelResponse.class);
        return updatedFuelResponse;
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.fuelBusinessRules.idIsNotExists(id);
        Optional<Fuel> fuel = this.fuelRepository.findById(id);
        this.fuelRepository.delete(fuel.get());
    }

    @Override
    public Fuel getByName(String fuelName) {
        this.fuelBusinessRules.fuelNameIsNotExists(fuelName);
        Fuel fuel = this.fuelRepository.findByNameIgnoreCase(fuelName).get();
        return fuel;
    }
}
