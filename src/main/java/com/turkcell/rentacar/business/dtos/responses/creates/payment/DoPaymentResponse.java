package com.turkcell.rentacar.business.dtos.responses.creates.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoPaymentResponse {
    private String paymentState;
}
