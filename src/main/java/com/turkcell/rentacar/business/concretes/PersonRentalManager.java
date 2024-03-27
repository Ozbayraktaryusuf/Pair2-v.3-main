package com.turkcell.rentacar.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.turkcell.rentacar.business.rules.RentalBusinessRules;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.FuelService;
import com.turkcell.rentacar.business.abstracts.PersonCustomerService;
import com.turkcell.rentacar.business.abstracts.RentalforPersonService;
import com.turkcell.rentacar.business.abstracts.RentalforPersonService;
import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateRentalforPersonRequest;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateRentalforPersonRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforPersonResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedPersonCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.creates.CreateRentalforPersonResponse;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.PersonalCustomer;
import com.turkcell.rentacar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
@Service
public class PersonRentalManager implements RentalforPersonService {
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private PersonCustomerService personCustomerService;
	private RentalBusinessRules rentalBusinessRules;

	@Override
	public CreateRentalforPersonResponse startRentalforPersonCustomer(CreateRentalforPersonRequest createRentalforPersonRequest) {
		rentalBusinessRules.currentlyUnderMaintenance(createRentalforPersonRequest.getCarId());
		rentalBusinessRules.currentlyRented(createRentalforPersonRequest.getCarId());
		
		Rental rental = this.modelMapperService.forRequest().map(createRentalforPersonRequest, Rental.class);
		Car car = this.carService.getByIdForRental(createRentalforPersonRequest.getCarId());
		PersonalCustomer personalCustomer = this.personCustomerService.getByIdForPersonRental(createRentalforPersonRequest.getCustomerId());
		rental.setPersonalCustomer(personalCustomer);
		rental.setDateSent(LocalDateTime.now());
		rental.setCar(car);
		car.setState("Rented");
		Rental existsRental = this.rentalRepository.save(rental);

		CreateRentalforPersonResponse createRentalResponse = this.modelMapperService.forResponse().map(existsRental,
				CreateRentalforPersonResponse.class);
		createRentalResponse.setPersonCustomerId(personalCustomer.getId());
		return createRentalResponse;

	}

	@Override
	public List<CreateRentalforPersonResponse> getAllForPersonCustomer() {
		List<CreateRentalforPersonResponse> createRentalResponseList = new ArrayList<>();
		List<Rental> rentalList = this.rentalRepository.findAll();

		for (Rental rental : rentalList) {
			CreateRentalforPersonResponse createRentalResponse = this.modelMapperService.forResponse().map(rental,
					CreateRentalforPersonResponse.class);
			if (rental.getCorporateCustomer() == null) {
				createRentalResponse.setPersonCustomerId(rental.getPersonalCustomer().getId());
				createRentalResponseList.add(createRentalResponse);

			}
		}
		return createRentalResponseList;
	}

	@Override
	public CreateRentalforPersonResponse finishRentalForPersonCustomer(int id) {
		//this.rentalBusinessRules.idIsNotExists(id);
		Rental existsRental = this.rentalRepository.findById(id).get();
		existsRental.setDateReturned(LocalDateTime.now());
		Car car = existsRental.getCar();
		car.setState("Available");
		Rental updatedRental = this.rentalRepository.save(existsRental);
		CreateRentalforPersonResponse createRentalforPersonResponse = this.modelMapperService.forResponse().map(updatedRental,
				CreateRentalforPersonResponse.class);
		createRentalforPersonResponse.setPersonCustomerId(existsRental.getPersonalCustomer().getId());
		return createRentalforPersonResponse;
	}



}
