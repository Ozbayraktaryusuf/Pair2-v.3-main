package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedTransmissionResponse;
import com.turkcell.rentacar.business.rules.TransmissionBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Transmission;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionManager implements TransmissionService {
    private  TransmissionRepository transmissionRepository;
    private  ModelMapperService modelMapperService;
    private  TransmissionBusinessRules transmissionBusinessRules;

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(createTransmissionRequest.getName());

        Transmission transmission=this.modelMapperService.forRequest().map(createTransmissionRequest,Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission exitsTransmisson=this.transmissionRepository.save(transmission);

        CreatedTransmissionResponse createdTransmissionResponse=this.modelMapperService.forResponse().map(exitsTransmisson,CreatedTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public List<UpdatedTransmissionResponse> getAll() {
        List<UpdatedTransmissionResponse> updatedTransmissionResponseList = new ArrayList<>();
        List<Transmission> transmissions = this.transmissionRepository.findAll();

        for (Transmission transmission:transmissions){
            UpdatedTransmissionResponse updatedTransmissionResponse = this.modelMapperService.forResponse().map(transmission,UpdatedTransmissionResponse.class);
            updatedTransmissionResponseList.add(updatedTransmissionResponse);
        }
        return updatedTransmissionResponseList;
    }

    @Override
    public UpdatedTransmissionResponse getById(int id) {
        this.transmissionBusinessRules.idIsNotExists(id);
        Transmission transmission = this.transmissionRepository.findById(id).get();
        UpdatedTransmissionResponse updatedTransmissionResponse = this.modelMapperService.forResponse().map(transmission,UpdatedTransmissionResponse.class);
        return updatedTransmissionResponse;
    }

    @Override
    @Transactional
    public UpdatedTransmissionResponse update(int id, UpdateTransmissionRequest updateTransmissionRequest) {
        this.transmissionBusinessRules.idIsNotExists(id);
        this.transmissionBusinessRules.transmissionNameCanNotBeDuplicated(updateTransmissionRequest.getName());

        Transmission existsTransmission = this.transmissionRepository.findById(id).get();
        existsTransmission.setName(updateTransmissionRequest.getName());
        existsTransmission.setUpdatedDate(LocalDateTime.now());
        Transmission updatedTransmission = this.transmissionRepository.save(existsTransmission);
        UpdatedTransmissionResponse updatedTransmissionResponse = this.modelMapperService.forResponse().map(updatedTransmission,UpdatedTransmissionResponse.class);
        return updatedTransmissionResponse;
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.transmissionBusinessRules.idIsNotExists(id);
        Transmission transmission = this.transmissionRepository.findById(id).get();
        this.transmissionRepository.delete(transmission);
    }

    @Override
    public Transmission getByName(String transmissionName) {
        this.transmissionBusinessRules.transmissionNameIsNotExists(transmissionName);
        Transmission transmission = this.transmissionRepository.findByNameIgnoreCase(transmissionName).get();
        return transmission;
    }
}
