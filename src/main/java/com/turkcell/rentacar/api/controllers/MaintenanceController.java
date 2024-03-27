package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.MaintenanceService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateMaintenanceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/maintenances")
public class MaintenanceController {
    private MaintenanceService maintenanceService;
    @PostMapping("/startMaintenance")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse startMaintence(@Valid @RequestBody CreateMaintenanceRequest createMaintenanceRequest){
        return this.maintenanceService.startMaintence( createMaintenanceRequest);
    }
    @GetMapping("/getall")
    public List<CreateMaintenanceResponse> getAll(){
        return this.maintenanceService.getAll();
    }
    @PostMapping("/finishMaintenance/{id}")
    public CreateMaintenanceResponse finishMaintence(@PathVariable int id) {
        return this.maintenanceService.finishMaintence( id);
    }
}
