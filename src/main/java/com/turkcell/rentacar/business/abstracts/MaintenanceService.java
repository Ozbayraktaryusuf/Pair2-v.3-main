package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.creates.CreateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    CreateMaintenanceResponse startMaintence(CreateMaintenanceRequest createMaintenanceRequest);

    List<CreateMaintenanceResponse> getAll();
    CreateMaintenanceResponse finishMaintence(int id);

}
