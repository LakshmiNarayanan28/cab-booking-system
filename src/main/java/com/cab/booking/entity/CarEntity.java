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
@Table(name = "cardb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String carName;
	private String carDriverName;
	private int carCapacity;
	
	@Override
	public String toString() {
		return "CarEntity [id=" + id + ", carName=" + carName + ", carDriverName=" + carDriverName + ", carCapacity="
				+ carCapacity + "]";
	}
	
}
