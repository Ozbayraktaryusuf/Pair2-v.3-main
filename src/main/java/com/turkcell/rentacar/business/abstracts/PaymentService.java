package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.creates.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getAlls.GetAllPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.payment.*;
import com.turkcell.rentacar.business.dtos.responses.getById.GetByIdPaymentResponse;
import com.turkcell.rentacar.entities.concretes.Payment;

import java.util.List;

public interface PaymentService {
    CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest);
    DoPaymentResponse doPayment(int id);
    CancelPaymentResponse cancelPayment(int id);
    List<GetAllPaymentResponse> getall();
    GetByIdPaymentResponse getById(int id);
    void delete(int id);
    Payment getByIdForRental(int id);

}
