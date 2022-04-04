package com.cab.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cab.booking.Repository.CarRepository;
import com.cab.booking.entity.CarEntity;

@Service
public class CarService {

	@Autowired
	CarRepository carRepository;
	
	public List<CarEntity> getAllCars() {
		return carRepository.findAll();
	}
	
	public Optional<CarEntity> getCarDetailsById(int id) {
		return carRepository.findById(id);
	}
	
	public CarEntity addCarDetails(CarEntity carEntity) {
		return carRepository.save(carEntity);	
	}
	
	public CarEntity updateCarDetails(int id, CarEntity carEntity) {
		Optional<CarEntity> getDetails = carRepository.findById(id);
		if(getDetails.isPresent()) {
			CarEntity getCarDetail = getDetails.get();
			getCarDetail.setCarName(carEntity.getCarName());
			getCarDetail.setCarDriverName(carEntity.getCarDriverName());
			getCarDetail.setCarCapacity(carEntity.getCarCapacity());
			carRepository.save(getCarDetail);
			return getCarDetail;
		}
		return null;
	}
	
	public Integer deleteCarDetails(int id) {
		carRepository.deleteById(id);
		if(carRepository.findById(id) == null) {
			return null;
		}
		return id;
	}
}

