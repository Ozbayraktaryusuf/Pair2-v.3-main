package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.creates.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.payment.*;

public interface PaymentService {
    CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest);
    DoPaymentResponse doPayement();
    CancelPaymentResponse cancelPayment();
    GetAllPaymentResponse getall();
    GetByIdPaymentResponse getById(int id);
    void delete(int id);

}
