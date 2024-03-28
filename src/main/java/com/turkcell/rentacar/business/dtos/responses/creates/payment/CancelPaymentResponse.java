package com.turkcell.rentacar.business.dtos.responses.creates.payment;

import com.turkcell.rentacar.entities.concretes.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CancelPaymentResponse {
    private String paymentState;
}
