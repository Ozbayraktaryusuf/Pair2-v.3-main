package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.FuelMessages;
import com.turkcell.rentacar.business.messages.PaymentMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import com.turkcell.rentacar.entities.concretes.Payment;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentBusinessRules {
    private PaymentRepository paymentRepository;
    public void paymentIsAlreadyDone(int id){
        Optional<Payment> payment=this.paymentRepository.findById(id);
        if (payment.get().getPaymentState().equals("PaymentIsSuccessful")){
            throw new BusinessException(PaymentMessages.paymentIsAlreadyDone);
        }
    }
    public void paymentIsAlreadyCancelled(int id){
        Optional<Payment> payment=this.paymentRepository.findById(id);
        if (payment.get().getPaymentState().equals("PaymentIsCancelled")){
            throw new BusinessException(PaymentMessages.paymentIsAlreadyCancelled);
        }
    }
    public void idIsNotExists(int id){
        Optional<Payment> payment = this.paymentRepository.findById(id);
        if (payment.isEmpty()){
            throw new BusinessException(PaymentMessages.idIsNotExists);
        }
    }
}
