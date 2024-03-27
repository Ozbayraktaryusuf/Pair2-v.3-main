package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.CarMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private CarRepository carRepository;
    private ModelRepository modelRepository;

    public void idIsNotExists(int id){
        Optional<Car> car = this.carRepository.findById(id);
        if (car.isEmpty()){
            throw new BusinessException(CarMessages.idIsNotExists);
        }
    }
    public void modelNameIsExists(String modelName) {
        Optional<Model> existingModel = this.modelRepository.findByNameIgnoreCase(modelName);
        if (existingModel.isEmpty()) {
            throw new BusinessException(CarMessages.modelNameIsNotExists);
        }
    }

}
