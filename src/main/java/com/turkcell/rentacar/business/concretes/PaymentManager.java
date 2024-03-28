package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.*;
import com.turkcell.rentacar.business.dtos.requests.creates.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getAlls.GetAllPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.payment.*;
import com.turkcell.rentacar.business.dtos.responses.getById.GetByIdPaymentResponse;
import com.turkcell.rentacar.business.rules.PaymentBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.entities.concretes.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private PaymentRepository paymentRepository;
    private ModelMapperService modelMapperService;
    private PaymentBusinessRules paymentBusinessRules;

    private CarService carService;
    private PersonCustomerService personCustomerService;
    private CorporateCustomerService corporateCustomerService;
    private ExtraServicesService extraServicesService;

    @Override
    public CreatedPaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        Payment payment=this.modelMapperService.forRequest().map(createPaymentRequest,Payment.class);
        Car car=this.carService.getByIdForPayment(createPaymentRequest.getCarId());
        ExtraServices extraServices=this.extraServicesService.getByIdForPayment(createPaymentRequest.getExtraServiceId());
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
        payment.setExtraServices(extraServices);
        payment.setTotalAmount((payment.getCar().getDailyPrice()*createPaymentRequest.getDayOfRent())+payment.getExtraServices().getPrice());
        payment.setPaymentState("PaymentIsWaiting");
        Payment createdPayment =this.paymentRepository.save(payment);
        CreatedPaymentResponse createdPaymentResponse=this.modelMapperService.forResponse().map(createdPayment,CreatedPaymentResponse.class);
        return createdPaymentResponse;
    }

    @Override
    public DoPaymentResponse doPayment(int id) {
        this.paymentBusinessRules.idIsNotExists(id);
        this.paymentBusinessRules.paymentIsAlreadyCancelled(id);
        Payment doingPayement=this.paymentRepository.findById(id).get();
        doingPayement.setPaymentState("PaymentIsSuccessful");
        this.paymentRepository.save(doingPayement);
        DoPaymentResponse doPaymentResponse=this.modelMapperService.forResponse().map(doingPayement,DoPaymentResponse.class);
        return doPaymentResponse;
    }

    @Override
    public CancelPaymentResponse cancelPayment(int id) {
        this.paymentBusinessRules.paymentIsAlreadyCancelled(id);
        this.paymentBusinessRules.paymentIsAlreadyDone(id);
        Payment doingPayement=this.paymentRepository.findById(id).get();
        doingPayement.setPaymentState("PaymentIsCancelled");
        this.paymentRepository.save(doingPayement);
        CancelPaymentResponse cancelPaymentResponse=this.modelMapperService.forResponse().map(doingPayement,CancelPaymentResponse.class);
        return cancelPaymentResponse;
    }

    @Override
    public List<GetAllPaymentResponse> getall() {
        List<GetAllPaymentResponse> getAllPaymentResponseList =new ArrayList<>();
        List<Payment> paymentList=this.paymentRepository.findAll();
        for (Payment payment:paymentList){
            GetAllPaymentResponse getAllPaymentResponse=this.modelMapperService.forResponse().map(payment,GetAllPaymentResponse.class);
            getAllPaymentResponseList.add(getAllPaymentResponse);
        }
        return getAllPaymentResponseList;
    }

    @Override
    public GetByIdPaymentResponse getById(int id) {
        this.paymentBusinessRules.idIsNotExists(id);
        //Payment payment=this.paymentRepository.findById(id).get();
        return this.modelMapperService.forResponse().map(this.paymentRepository.findById(id).get(),GetByIdPaymentResponse.class);
    }

    @Override
    public void delete(int id) {
        this.paymentBusinessRules.idIsNotExists(id);
        this.paymentRepository.deleteById(id);

    }

    @Override
    public Payment getByIdForRental(int id) {
        this.paymentBusinessRules.idIsNotExists(id);
        Payment payment=this.paymentRepository.findById(id).get();
        return payment;
    }

}