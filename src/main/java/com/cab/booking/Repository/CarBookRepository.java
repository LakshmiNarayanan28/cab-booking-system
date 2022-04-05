package com.cab.booking.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cab.booking.Dto.EmployeeProjection;
import com.cab.booking.entity.BookingEntity;

@Repository
public interface CarBookRepository extends JpaRepository<BookingEntity,Integer> {

	
	@Query(value="SELECT car_capacity FROM `cab-book-db`.`cardb` WHERE id =:carId", nativeQuery=true)
	Integer getCheckAvailabiltyOfCab(@Param("carId") int carId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE cardb SET car_capacity = car_capacity-1 WHERE id =:carId", nativeQuery=true)
	void updateAvailabliltyOfCab(@Param("carId") int carId);
	
	@Query(value="SELECT * FROM `cab-book-db`.`employee` WHERE eid =:empId", nativeQuery=true)
	EmployeeProjection getEmployeeDetails(@Param("empId") int empId);
	
	@Query(value = "SELECT COUNT(*) FROM `booking_car` WHERE STATUS =:activeId AND emp_label=:frontCons AND car_id=:carId", nativeQuery= true)
	Integer getAllocatedCountofFront(@Param("activeId") int activeId, @Param("frontCons") String frontCons,@Param("carId") int carId);
	
	
	@Query(value = "SELECT COUNT(*) FROM `booking_car` WHERE STATUS =:activeId AND emp_label=:backCons AND car_id=:carId", nativeQuery= true)
	Integer getAllocatedCountofBack(@Param("activeId") int activeId, @Param("backCons") String backCons,@Param("carId") int carId);
	
	
	@Query(value="SELECT bc.emp_id FROM `cab-book-db`.`booking_car` bc INNER JOIN `cab-book-db`.`employee` em ON bc.`emp_id` = em.`eid` WHERE emp_id=:empId AND bc.status=:activeId", nativeQuery= true)
	Integer getAlreadyAllocatedOrNot(@Param("empId") int empId,@Param("activeId") int activeId);
}
