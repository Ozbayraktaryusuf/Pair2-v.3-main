package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ExtraServicesService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateExtraServiceRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateExtraServicesRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.getAlls.GetAllExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.getById.GetByIdExtraServicesResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdateExtraServicesResponse;
import com.turkcell.rentacar.business.rules.ExtraServicesBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ExtraServicesRepository;
import com.turkcell.rentacar.entities.concretes.ExtraServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExtraServiceManager implements ExtraServicesService {
    private ExtraServicesRepository extraServicesRepository;
    private ModelMapperService modelMapperService;
    private ExtraServicesBusinessRules extraServicesBusinessRules;
    @Override
    public CreateExtraServicesResponse add(CreateExtraServiceRequest createExtraServiceRequest) {
        ExtraServices extraServices=this.modelMapperService.forRequest().map(createExtraServiceRequest,ExtraServices.class);
        extraServices.setCreatedDate(LocalDateTime.now());

        ExtraServices createdExtraServices=this.extraServicesRepository.save(extraServices);

        CreateExtraServicesResponse createdExtraServicesResponse=this.modelMapperService.forResponse().map(createdExtraServices,CreateExtraServicesResponse.class);
        return createdExtraServicesResponse;
    }

    @Override
    public List<GetAllExtraServicesResponse> getAll() {
        List<GetAllExtraServicesResponse> getAllExtraServicesResponseList=new ArrayList<>();
        List<ExtraServices> extraServicesList = this.extraServicesRepository.findAll();
        for (ExtraServices extraServices:extraServicesList){
            GetAllExtraServicesResponse getAllExtraServicesResponse=this.modelMapperService.forResponse().map(extraServices,GetAllExtraServicesResponse.class);
            getAllExtraServicesResponseList.add(getAllExtraServicesResponse);
        }
        return getAllExtraServicesResponseList;
    }

    @Override
    public GetByIdExtraServicesResponse getById(int id) {
        this.extraServicesBusinessRules.idIsNotExists(id);
        return  this.modelMapperService.forResponse().map(this.extraServicesRepository.findById(id),GetByIdExtraServicesResponse.class);
    }

    @Override
    public UpdateExtraServicesResponse update(int id,UpdateExtraServicesRequest updateExtraServicesRequest) {
        this.extraServicesBusinessRules.idIsNotExists(id);
        ExtraServices existsExtraService=this.extraServicesRepository.findById(id).get();
        //existsExtraService=this.modelMapperService.forRequest().map(updateExtraServicesRequest,ExtraServices.class);
        //existsExtraService.setId(id);
        existsExtraService.setName(updateExtraServicesRequest.getName());
        existsExtraService.setPrice(updateExtraServicesRequest.getPrice());
        existsExtraService.setUpdatedDate(LocalDateTime.now());

        ExtraServices updatedExtraServices=this.extraServicesRepository.save(existsExtraService);

        UpdateExtraServicesResponse updateExtraServicesResponse=this.modelMapperService.forResponse().map(updatedExtraServices,UpdateExtraServicesResponse.class);
        return updateExtraServicesResponse;
    }

    @Override
    public void delete(int id) {
        this.extraServicesBusinessRules.idIsNotExists(id);
        this.extraServicesRepository.deleteById(id);
    }

    @Override
    public ExtraServices getByIdForPayment(int id) {
        this.extraServicesBusinessRules.idIsNotExists(id);
        ExtraServices extraServices=this.extraServicesRepository.findById(id).get();
        return extraServices;
    }
}
