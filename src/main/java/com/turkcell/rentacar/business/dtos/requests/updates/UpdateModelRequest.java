package com.turkcell.rentacar.business.dtos.requests.updates;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateModelRequest {
    @NotNull
    @Size(min=2,max=30)
    private String name;

    @NotNull
    private String brandName;
}
