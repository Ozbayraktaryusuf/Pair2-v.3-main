package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.TransmissionMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransmissionBusinessRules {
    private TransmissionRepository transmissionRepository;
    public void transmissionNameCanNotBeDuplicated(String transmissionName){
        Optional<Transmission> transmission = this.transmissionRepository.findByNameIgnoreCase(transmissionName);
        if (transmission.isPresent()){
            throw new BusinessException(TransmissionMessages.transmissionNameAlreadyExists);
        }
    }

    public void idIsNotExists(int id){
        Optional<Transmission> transmission = this.transmissionRepository.findById(id);
        if (transmission.isEmpty()){
            throw new BusinessException(TransmissionMessages.idIsNotExists);
        }
    }

    public void transmissionNameIsNotExists(String transmissionName){
        Optional<Transmission> transmission = this.transmissionRepository.findByNameIgnoreCase(transmissionName);
        if (transmission.isEmpty()){
            throw new BusinessException(TransmissionMessages.transmissionNameIsNotExists);
        }
    }
}
