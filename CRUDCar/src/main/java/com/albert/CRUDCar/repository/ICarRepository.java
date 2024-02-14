package com.albert.CRUDCar.repository;

import com.albert.CRUDCar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<Car, Integer> {
}
