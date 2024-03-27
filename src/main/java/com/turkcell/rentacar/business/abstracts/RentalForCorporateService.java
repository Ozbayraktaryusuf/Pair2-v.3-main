package com.turkcell.rentacar.business.abstracts;


import com.turkcell.rentacar.business.dtos.requests.creates.CreateRentalforCorporateRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforCorporateResponse;


import java.util.List;

public interface RentalForCorporateService {
    CreateRentalforCorporateResponse startRentalforCorporateCustomer(CreateRentalforCorporateRequest createRentalforCorporateRequest);
    List<CreateRentalforCorporateResponse> getAllForCorporateCustomer();
    CreateRentalforCorporateResponse finishRentalForCorporateCustomer(int id);
}
