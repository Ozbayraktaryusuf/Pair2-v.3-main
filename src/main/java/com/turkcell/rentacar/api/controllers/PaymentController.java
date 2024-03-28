package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.payment.CancelPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.payment.DoPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getAlls.GetAllPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getById.GetByIdPaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    private PaymentService paymentService;
    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPaymentResponse add(@Valid @RequestBody CreatePaymentRequest createPaymentRequest){
        return this.paymentService.add(createPaymentRequest);
    }
    @PostMapping("/doing-payment/{id}")
    public DoPaymentResponse doPayment(@PathVariable int id){
        return this.paymentService.doPayment(id);
    }
    @PostMapping("/canceling-payment/{id}")
    public CancelPaymentResponse cancelPayment(@PathVariable int id){
        return this.paymentService.cancelPayment(id);
    }
    @GetMapping("/getAll")
    public List<GetAllPaymentResponse> getAll(){
        return this.paymentService.getall();
    }
    @GetMapping("/getById/{id}")
    public GetByIdPaymentResponse getById(@PathVariable int id){
        return this.paymentService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        this.paymentService.delete(id);
    }
}
