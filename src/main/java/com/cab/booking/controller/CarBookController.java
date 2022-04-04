package com.cab.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.booking.Exception.CbException;
import com.cab.booking.entity.BookingEntity;
import com.cab.booking.service.CarBookService;

@RestController
public class CarBookController {

	@Autowired
	CarBookService carBookService;
	
	@PostMapping(value= "/book")
	public BookingEntity bookingCabByEmpIdAndCarId(@RequestParam("empId") int empId, @RequestParam("carId") int carId) throws CbException {
		return carBookService.bookingCabByEmpIdAndCarId(empId, carId);	
	}
}
