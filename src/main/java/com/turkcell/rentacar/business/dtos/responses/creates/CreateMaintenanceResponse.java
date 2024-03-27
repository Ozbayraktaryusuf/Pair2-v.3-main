package com.turkcell.rentacar.business.dtos.responses.creates;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMaintenanceResponse {
    private int id;
    private int carID;
    private LocalDateTime dateSent;
    private LocalDateTime dateReturned;
}
