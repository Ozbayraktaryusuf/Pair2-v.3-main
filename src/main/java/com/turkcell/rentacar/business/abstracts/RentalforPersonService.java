package com.turkcell.rentacar.business.abstracts;
import java.util.List;

import com.turkcell.rentacar.business.dtos.requests.creates.CreateRentalforPersonRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforPersonResponse;
public interface RentalforPersonService {
		CreateRentalforPersonResponse startRentalforPersonCustomer(CreateRentalforPersonRequest createRentalforPersonRequest);
		List<CreateRentalforPersonResponse> getAllForPersonCustomer();
		CreateRentalforPersonResponse finishRentalForPersonCustomer(int id);
}
