package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.ExtraServicesMessages;
import com.turkcell.rentacar.business.messages.FuelMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.ExtraServicesRepository;
import com.turkcell.rentacar.entities.concretes.ExtraServices;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ExtraServicesBusinessRules {
    private ExtraServicesRepository extraServicesRepository;
    public void idIsNotExists(int id){
        Optional<ExtraServices> extraServices = this.extraServicesRepository.findById(id);
        if (extraServices.isEmpty()){
            throw new BusinessException(ExtraServicesMessages.idIsNotExists);
        }
    }
}
