package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.PersonCustomerService;
import com.turkcell.rentacar.business.dtos.requests.creates.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.payment.*;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.concretes.PersonalCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private PaymentRepository paymentRepository;
    private ModelMapperService modelMapperService;
    //private PaymentBusinessRules paymentBusinessRules;

    private CarService carService;
    private PersonCustomerService personCustomerService;
    private CorporateCustomerService corporateCustomerService;

    @Override
    public CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        Payment payment=this.modelMapperService.forRequest().map(createPaymentRequest,Payment.class);
        Car car=this.carService.getByIdForPayment(createPaymentRequest.getCarId());
        if (createPaymentRequest.getNationalNumber().isEmpty()){
            CorporateCustomer corporateCustomer=this.corporateCustomerService.getByTaxIdNumberForPayment(createPaymentRequest.getTaxIdNumber()).get();
            payment.setCorporateCustomer(corporateCustomer);

        }
        else {
            PersonalCustomer personalCustomer=this.personCustomerService.getByNationalNumberForPayment(createPaymentRequest.getNationalNumber()).get();
            payment.setPersonalCustomer(personalCustomer);
        }
        payment.setCreatedDate(LocalDateTime.now());
        payment.setCar(car);
        payment.setAmount(payment.getCar().getDailyPrice()*createPaymentRequest.getDayOfRent());
        payment.setPaymentState("PaymentIsWaiting");
        Payment createdPayment =this.paymentRepository.save(payment);
        CreatedPaymentResponse createdPaymentResponse=this.modelMapperService.forResponse().map(createdPayment,CreatedPaymentResponse.class);
        return createdPaymentResponse;
    }

    @Override
    public DoPaymentResponse doPayement() {
        return null;
    }

    @Override
    public CancelPaymentResponse cancelPayment() {
        return null;
    }

    @Override
    public GetAllPaymentResponse getall() {
        return null;
    }

    @Override
    public GetByIdPaymentResponse getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}