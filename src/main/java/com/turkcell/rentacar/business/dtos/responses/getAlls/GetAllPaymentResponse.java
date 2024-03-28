package com.turkcell.rentacar.business.dtos.responses.getAlls;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllPaymentResponse {
    private int id;
    private String paymentState;
    private double amount;
    private String cardHolderName;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private int dayOfRent;
    private int carId;
    private int personalCustomer;
    private int corporateCustomer;
    private int extraServiceId;
    private LocalDateTime createdDate;
}
