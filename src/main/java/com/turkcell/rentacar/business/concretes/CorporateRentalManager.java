package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentalForCorporateService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateRentalforCorporateRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforCorporateResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforPersonResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class CorporateRentalManager implements RentalForCorporateService {
    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private CorporateCustomerService corporateCustomerService;
    private PaymentService paymentService;

    //private RentalBusinessRules rentalBusinessRules;
    @Override
    public CreateRentalforCorporateResponse startRentalforCorporateCustomer(CreateRentalforCorporateRequest createRentalforCorporateRequest) {
        Rental rental = this.modelMapperService.forRequest().map(createRentalforCorporateRequest, Rental.class);
//        Car car = this.carService.getByIdForRental(createRentalforCorporateRequest.getCarId());
//        CorporateCustomer corporateCustomer = this.corporateCustomerService.getByIdForCorporateRental(createRentalforCorporateRequest.getCustomerId());
        Payment payment=this.paymentService.getByIdForRental(createRentalforCorporateRequest.getPaymentId());
        rental.setDateSent(LocalDateTime.now());
        rental.setPayment(payment);
        rental.setCorporateCustomer(rental.getPayment().getCorporateCustomer());
        rental.setCar(rental.getPayment().getCar());
        rental.getCar().setState("Rented");
        Rental existsRental = this.rentalRepository.save(rental);

        CreateRentalforCorporateResponse createRentalforCorporateResponse = this.modelMapperService.forResponse().map(existsRental,
                CreateRentalforCorporateResponse.class);
        createRentalforCorporateResponse.setCorporateCustomerId(rental.getCorporateCustomer().getId());
        return createRentalforCorporateResponse;

    }

    @Override
    public List<CreateRentalforCorporateResponse> getAllForCorporateCustomer() {
        List<CreateRentalforCorporateResponse> createRentalforCorporateResponseList = new ArrayList<>();
        List<Rental> rentalList = this.rentalRepository.findAll();

        for (Rental rental : rentalList) {

            CreateRentalforCorporateResponse createRentalforCorporateResponse = this.modelMapperService.forResponse().map(rental, CreateRentalforCorporateResponse.class);
            if (rental.getPersonalCustomer()==null) {
                createRentalforCorporateResponse.setCorporateCustomerId(rental.getCorporateCustomer().getId());
                createRentalforCorporateResponseList.add(createRentalforCorporateResponse);
            }
        }
        return createRentalforCorporateResponseList;
    }

    @Override
    public CreateRentalforCorporateResponse finishRentalForCorporateCustomer(int id) {
        //this.rentalBusinessRules.idIsNotExists(id);
        Rental existsRental = this.rentalRepository.findById(id).get();
        existsRental.setDateReturned(LocalDateTime.now());
        Car car = existsRental.getCar();
        car.setState("Available");
        Rental updatedRental = this.rentalRepository.save(existsRental);
        CreateRentalforCorporateResponse createRentalforCorporateResponse = this.modelMapperService.forResponse().map(updatedRental,
                CreateRentalforCorporateResponse.class);
        createRentalforCorporateResponse.setCorporateCustomerId(existsRental.getCorporateCustomer().getId());
        return createRentalforCorporateResponse;
    }
}
