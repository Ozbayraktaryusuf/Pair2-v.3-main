package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.creates.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.payment.CreatedPaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    private PaymentService paymentService;
    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    CreatedPaymentResponse add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest){
        return this.paymentService.add(createPaymentRequest);
    }
}
