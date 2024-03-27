package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.BrandMessages;
import com.turkcell.rentacar.business.messages.MaintenanceMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.dataAccess.abstracts.MaintenanceRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Maintenance;
import com.turkcell.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ManitenanceBusinessRules {
    private MaintenanceRepository maintenanceRepository;
    private CarRepository carRepository;
    public void idIsNotExists (int id){
        Optional<Maintenance> maintenance=this.maintenanceRepository.findById(id);
        if(maintenance.isEmpty()){
            throw new BusinessException(MaintenanceMessages.idIsNotExists);
        }
    }
    public void currentlyRented (int id){
        Optional<Car> car=this.carRepository.findById(id);
        if (car.get().getState().equals(State.Rented)){
            throw new BusinessException(MaintenanceMessages.carIsCurrentlyRented);
        }
    }
    public void currentlyUnderMaintenance (int id){
        Optional<Car> car=this.carRepository.findById(id);
        if (car.get().getState().equals(State.UnderMaintenance)){
            throw new BusinessException(MaintenanceMessages.carIsCurrentlyUnderMaintance);
        }
    }

}
