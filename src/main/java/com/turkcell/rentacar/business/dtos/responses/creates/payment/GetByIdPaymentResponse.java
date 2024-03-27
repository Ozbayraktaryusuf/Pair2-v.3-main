package com.turkcell.rentacar.business.dtos.responses.creates.payment;

import java.time.LocalDateTime;

public class GetByIdPaymentResponse {
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
    private LocalDateTime createdDate;
}
