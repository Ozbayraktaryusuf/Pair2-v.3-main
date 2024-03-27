package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.ModelMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    ModelRepository modelRepository;
    public void modelNameCanNotBeDuplicated(String modelName){
        Optional<Model> model = this.modelRepository.findByNameIgnoreCase(modelName);
        if (model.isPresent()){
            throw new BusinessException(ModelMessages.modelNameAlreadyExists);
        }
    }

    public void idIsNotExists(int id){
        Optional<Model> model = this.modelRepository.findById(id);
        if (model.isEmpty()){
            throw new BusinessException(ModelMessages.idIsNotExists);
        }
    }

    public void modelNameCheck(int id, String modelName) {
        Optional<Model> existingModel = this.modelRepository.findById(id);
        if (existingModel.isPresent()) {
            String currentModelName = existingModel.get().getName();
            if (!currentModelName.equals(modelName)) {
                modelNameCanNotBeDuplicated(modelName);
            }
        }
    }
    public void modelNameIsNotExists(String modelName){
        Optional<Model> existsModel = this.modelRepository.findByNameIgnoreCase(modelName);
        if (existsModel.isEmpty()){
            throw new BusinessException(ModelMessages.modelNameIsNotExists);
        }
    }
}
