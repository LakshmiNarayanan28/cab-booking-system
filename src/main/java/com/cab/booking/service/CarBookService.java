package com.cab.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.booking.Dto.EmployeeProjection;
import com.cab.booking.Exception.CbException;
import com.cab.booking.Repository.CarBookRepository;
import com.cab.booking.entity.BookingEntity;
import com.cab.booking.util.CarBookingConstant;

@Service
public class CarBookService {

	@Autowired
	CarBookRepository carBookRepository;
	
	public BookingEntity bookingCabByEmpIdAndCarId(int empId,int carId) throws CbException {
		BookingEntity bookingEntity = new BookingEntity();
		int activeId = 1;
		String frontCons = CarBookingConstant.FRONTEND;
		String backCons = CarBookingConstant.BACKEND;
		bookingEntity.setEmpId(empId);
		bookingEntity.setCarId(carId);
		Integer AlreadyAllocEmpOrNot = carBookRepository.getAlreadyAllocatedOrNot(empId,activeId);
		if(AlreadyAllocEmpOrNot != null && AlreadyAllocEmpOrNot == empId) {
			throw new CbException("Associates are already booked the car. So booking not possible");
		}
		Integer checkAvailability = carBookRepository.getCheckAvailabiltyOfCab(carId);
		if (checkAvailability > 0 && checkAvailability <= 4) {

			EmployeeProjection getEmployeeDetails = carBookRepository.getEmployeeDetails(empId);
			System.out.println(getEmployeeDetails.getEmployee_Name() + "" + getEmployeeDetails.getEmployee_Role());
			if (getEmployeeDetails.getEmployee_Role().equalsIgnoreCase(CarBookingConstant.FRONTEND)) {
				Integer getFCount = carBookRepository.getAllocatedCountofFront(activeId, frontCons,carId);
				Integer getBbCounts = carBookRepository.getAllocatedCountofBack(activeId, backCons,carId);
				if(getFCount == 0 && getBbCounts == 3) {
					throw new CbException("No Seats for Front Associates");
				}
				if (getFCount == 0) {
					bookingEntity.setEmpLabel(getEmployeeDetails.getEmployee_Role());
					bookingEntity.setStatus(1);
					bookingEntity.setAvailableCapacity(checkAvailability);
				} else {
					Integer getFfCount = carBookRepository.getAllocatedCountofFront(activeId, frontCons,carId);
					Integer getBbCount = carBookRepository.getAllocatedCountofBack(activeId, backCons,carId);
					if (getFfCount == 2 && getBbCount == 1) {
						throw new CbException("No Seats for Frontend Associates");
					} else if (getFfCount == 1 || getFfCount >= 2) {
						bookingEntity.setEmpLabel(getEmployeeDetails.getEmployee_Role());
						bookingEntity.setStatus(1);
						bookingEntity.setAvailableCapacity(checkAvailability);
					}
				}
			} else {
				Integer getBCount = carBookRepository.getAllocatedCountofBack(activeId, backCons,carId);
				Integer getFfCounts = carBookRepository.getAllocatedCountofFront(activeId, frontCons,carId);
				if(getFfCounts == 3 && getBCount == 0) {
					throw new CbException("No Seats for Back Associates");
				}
				if (getBCount == 0) {
					bookingEntity.setEmpLabel(getEmployeeDetails.getEmployee_Role());
					bookingEntity.setStatus(1);
					bookingEntity.setAvailableCapacity(checkAvailability);
				} else {
					Integer getBbCount = carBookRepository.getAllocatedCountofBack(activeId, backCons,carId);
					Integer getFfCount = carBookRepository.getAllocatedCountofFront(activeId, frontCons,carId);
					if (getFfCount == 1 && getBbCount == 2) {
						throw new CbException("No Seats for Backend Associates");
					} else if (getBbCount == 1 || getBbCount >= 2) {
						bookingEntity.setEmpLabel(getEmployeeDetails.getEmployee_Role());
						bookingEntity.setStatus(1);
						bookingEntity.setAvailableCapacity(checkAvailability);
					}
				}
			}
			carBookRepository.save(bookingEntity);
			carBookRepository.updateAvailabliltyOfCab(carId);
		} else {
			System.out.println("Seats not Available");
		}
		return bookingEntity;
	}
}
