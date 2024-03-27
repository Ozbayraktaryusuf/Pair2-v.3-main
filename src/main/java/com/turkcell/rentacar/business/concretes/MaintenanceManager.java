package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.MaintenanceService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateMaintenanceResponse;
import com.turkcell.rentacar.business.rules.ManitenanceBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.MaintenanceRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Maintenance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class MaintenanceManager implements MaintenanceService {
    private MaintenanceRepository maintenanceRepository;
    private ModelMapperService modelMapperService;
    private ManitenanceBusinessRules maintenanceBusinessRules;

    private CarService carService;

    @Override
    public CreateMaintenanceResponse startMaintence(CreateMaintenanceRequest createMaintenanceRequest) {
        this.maintenanceBusinessRules.currentlyRented(createMaintenanceRequest.getCarId());

        Maintenance maintenance=this.modelMapperService.forRequest().map(createMaintenanceRequest,Maintenance.class);
        Car car=this.carService.getByIdForMaintenance(createMaintenanceRequest.getCarId());
        maintenance.setDateSent(LocalDateTime.now());
        maintenance.setCar(car);
        car.setState("UnderMaintenance");
        Maintenance existsMaintenance=this.maintenanceRepository.save(maintenance);

        CreateMaintenanceResponse createMaintenanceResponse=this.modelMapperService.forResponse().map(existsMaintenance,CreateMaintenanceResponse.class);
        return createMaintenanceResponse;
    }

    @Override
    public List<CreateMaintenanceResponse> getAll() {
        List<CreateMaintenanceResponse> createMaintenanceResponseList=new ArrayList<>();
        List<Maintenance> maintenanceList=this.maintenanceRepository.findAll();

        for(Maintenance maintenance:maintenanceList){
            CreateMaintenanceResponse createMaintenanceResponse=this.modelMapperService.forResponse().map(maintenance,CreateMaintenanceResponse.class);
            createMaintenanceResponseList.add(createMaintenanceResponse);
        }
        return createMaintenanceResponseList;
    }

    @Override
    public CreateMaintenanceResponse finishMaintence(int id) {
        this.maintenanceBusinessRules.idIsNotExists(id);
        Maintenance existsMaintenance =this.maintenanceRepository.findById(id).get();
        existsMaintenance.setDateReturned(LocalDateTime.now());
        Car car=existsMaintenance.getCar();
        car.setState("Available");
        Maintenance updatedMaintenance=this.maintenanceRepository.save(existsMaintenance);
        CreateMaintenanceResponse createMaintenanceResponse=this.modelMapperService.forResponse().map(updatedMaintenance,CreateMaintenanceResponse.class);
        return createMaintenanceResponse;

    }
}
