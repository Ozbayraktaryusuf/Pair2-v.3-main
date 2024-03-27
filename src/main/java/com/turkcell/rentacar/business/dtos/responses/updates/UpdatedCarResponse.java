package com.turkcell.rentacar.business.dtos.responses.updates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedCarResponse {
    private int id;
    private int modelYear;
    private int minimumFindexScore;
    private double dailyPrice;
    private String modelName;
    private String state;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
