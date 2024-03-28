package com.turkcell.rentacar.business.dtos.responses.getAlls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllExtraServicesResponse {
    private int id;
    private String name;
    private double price;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
