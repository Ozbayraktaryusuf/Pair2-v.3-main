package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.creates.CreateCarRequest;
import com.turkcell.rentacar.business.dtos.requests.updates.UpdateCarRequest;
import com.turkcell.rentacar.business.dtos.responses.creates.CreatedCarResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.updates.UpdatedCarResponse;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Model;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;

    private ModelService modelService;


    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        this.carBusinessRules.modelNameIsExists(createCarRequest.getModelName());
        Car car = this.modelMapperService.forRequest().map(createCarRequest,Car.class);
        Model model = this.modelService.getByName(createCarRequest.getModelName());
        car.setCreatedDate(LocalDateTime.now());
        car.setModel(model);
        Car existsCar = this.carRepository.save(car);
        CreatedCarResponse createdCarResponse = this.modelMapperService.forResponse().map(existsCar,CreatedCarResponse.class);

        return createdCarResponse;
    }

    @Override
    public List<UpdatedCarResponse> getAll() {
        List<UpdatedCarResponse> updatedCarResponseList = new ArrayList<>();
        List<Car> cars = this.carRepository.findAll();
        for (Car car:cars){
            UpdatedCarResponse updatedCarResponse = this.modelMapperService.forResponse().map(car,UpdatedCarResponse.class);
            updatedCarResponseList.add(updatedCarResponse);
        }
        return updatedCarResponseList;
    }

    @Override
    public UpdatedCarResponse getById(int id) {
            this.carBusinessRules.idIsNotExists(id);
            return this.modelMapperService.forResponse().map(this.carRepository.findById(id),UpdatedCarResponse.class);

    }

    @Override
    @Transactional
    public UpdatedCarResponse update(int id, UpdateCarRequest updatedCarRequest) {
        this.carBusinessRules.idIsNotExists(id);
        this.carBusinessRules.modelNameIsExists(updatedCarRequest.getModelName());

        Model model=this.modelService.getByName(updatedCarRequest.getModelName());

        Car existsCar= this.carRepository.findById(id).get();
        existsCar.setModelYear(updatedCarRequest.getModelYear());
        existsCar.setDailyPrice(updatedCarRequest.getDailyPrice());
        existsCar.setMinimumFindexScore(updatedCarRequest.getMinimumFindexScore());
        existsCar.setModel(model);
        existsCar.setUpdatedDate(LocalDateTime.now());
        Car updatedCar=this.carRepository.save(existsCar);
        UpdatedCarResponse updatedCarResponse=this.modelMapperService.forResponse().map(updatedCar,UpdatedCarResponse.class);
        return updatedCarResponse;

    }

    @Override
    @Transactional
    public void delete(int id) {
        this.carBusinessRules.idIsNotExists(id);
        Car car=this.carRepository.findById(id).get();
        this.carRepository.delete(car);

    }

    @Override
    public List<UpdatedCarResponse> getByModelName(String name) {
        List<UpdatedCarResponse> updatedCarResponseList=new ArrayList<>();
        Model model= this.modelService.getByName(name);
        List<Car> carList=this.carRepository.findByModelId(model.getId()).get();
        for (Car car:carList){
                updatedCarResponseList.add(this.modelMapperService.forResponse().map(car,UpdatedCarResponse.class));
            }
        return updatedCarResponseList;
    }
    public Car getByIdForMaintenance(int id){
        this.carBusinessRules.idIsNotExists(id);
        Car car= this.carRepository.findById(id).get();
        return car;
    }

	@Override
	public Car getByIdForRental(int id) {
		this.carBusinessRules.idIsNotExists(id);
		Car car=this.carRepository.findById(id).get();
		return car;
	}

    @Override
    public Car getByIdForPayment(int id) {
        this.carBusinessRules.idIsNotExists(id);
        Car car=this.carRepository.findById(id).get();
        return car;
    }
}
