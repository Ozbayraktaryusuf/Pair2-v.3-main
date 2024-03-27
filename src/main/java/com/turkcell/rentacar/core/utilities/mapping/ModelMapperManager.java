package com.turkcell.rentacar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService{
    private ModelMapper modelMapper;

    public ModelMapperManager(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }
    @Override
    public ModelMapper forResponse() { //responseları map ederken
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
    @Override
    public ModelMapper forRequest() { //requestleri map ederken
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper;
    }
}
