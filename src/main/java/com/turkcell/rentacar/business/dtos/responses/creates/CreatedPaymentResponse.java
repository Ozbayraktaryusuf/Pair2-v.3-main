package com.turkcell.rentacar.business.dtos.responses.creates;

import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;
import com.turkcell.rentacar.entities.concretes.PersonalCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreatedPaymentResponse {
    private int id;
    private String paymentState;
    private double amount;
    private int carId;
    private int personalCustomerId;
    private int corporateCustomerId;
    private int extraServiceId;
    private LocalDateTime createdDate;
}
