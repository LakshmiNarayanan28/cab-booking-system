package com.cab.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BookingCar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bid;
	private int empId;
	private int carId;
	private int availableCapacity;
	private String empLabel;
	private int status;
	
	@Override
	public String toString() {
		return "BookingEntity [bid=" + bid + ", empId=" + empId + ", carId=" + carId + ", availableCapacity="
				+ availableCapacity + ", empLabel=" + empLabel + ", status=" + status + "]";
	}
	
	
}
