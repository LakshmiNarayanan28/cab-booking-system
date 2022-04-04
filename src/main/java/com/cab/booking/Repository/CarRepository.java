package com.cab.booking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.booking.entity.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer>{
 
}
