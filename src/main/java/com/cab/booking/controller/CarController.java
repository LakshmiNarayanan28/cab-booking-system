package com.cab.booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cab.booking.entity.CarEntity;
import com.cab.booking.service.CarService;

@RestController
public class CarController {
	
	@Autowired
	CarService carService;

	@GetMapping(value="/cars")
	public List<CarEntity> getAllCars() {
		return carService.getAllCars();
	}
	
	@GetMapping(value="/cars/{id}")
	public Optional<CarEntity> getCarDetailsById(@PathVariable("id") int id) {
		return carService.getCarDetailsById(id);
	}
	
	@PostMapping(value="/cars")
	public CarEntity addCarDetails(@RequestBody CarEntity carEntity) {
		return carService.addCarDetails(carEntity);
	}
	
	@PutMapping(value="/cars/{id}")
	public ResponseEntity<CarEntity> updateCarDetails(@PathVariable(name = "id") int id, @RequestBody CarEntity carEntity) {
		CarEntity carResponse = carService.updateCarDetails(id, carEntity);
		if(carResponse!=null) {
			return new ResponseEntity<>(carResponse,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(carResponse,HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value="/cars/{id}")
	public ResponseEntity<String> deleteCarDetails(@PathVariable(name="id") int id){
		Integer delResponse = carService.deleteCarDetails(id);
		if(delResponse==null) {
			return new ResponseEntity<String>("Deletion successfully",HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Record Not Present",HttpStatus.NOT_FOUND);
		}
	}
	
}
