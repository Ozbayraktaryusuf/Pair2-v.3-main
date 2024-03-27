package com.turkcell.rentacar.core.adapter.concretes;

import com.turkcell.rentacar.core.adapter.abstracts.CustomerFindexService;
import com.turkcell.rentacar.core.adapter.abstracts.FindexService;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindexManager implements FindexService {
    private final CustomerFindexService customerFindexService;

    @Override
    public int calculateFindexScore(String identifier) {
        return customerFindexService.calculateFindexScore(identifier);
    }
}
