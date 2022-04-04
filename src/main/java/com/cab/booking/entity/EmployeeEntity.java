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
@Table(name="Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int eid;
	private String employeeName;
	private String employeeRole;
	
	@Override
	public String toString() {
		return "EmployeeEntity [eid=" + eid + ", employeeName=" + employeeName + ", employeeRole=" + employeeRole + "]";
	}
	
}
