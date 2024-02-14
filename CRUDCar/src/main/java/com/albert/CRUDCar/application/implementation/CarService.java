package com.albert.CRUDCar.application.implementation;

import com.albert.CRUDCar.application.service.GenericService;
import com.albert.CRUDCar.domain.Car;
import com.albert.CRUDCar.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarService implements GenericService<Car> {
    @Autowired
    private ICarRepository iCarRepository;

    @Override
    public Car getEntityById(int id) {
        return null;
    }

    @Override
    public List<Car> getEntitiesByIds(List<Integer> ids) {
        return List.of();
    }

    @Override
    public List<Car> getAllEntities(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public Car addEntity(Car car) {
        return null;
    }

    @Override
    public List<Car> addEntities(List<Car> cars) {
        return List.of();
    }

    @Override
    public Car updateEntityById(int id, Car car) {
        return null;
    }

    @Override
    public List<Car> updateEntitiesByIds(List<Integer> ids, List<Car> cars) {
        return List.of();
    }

    @Override
    public Car modifyEntityById(int id, Car car) {
        return null;
    }

    @Override
    public List<Car> modifyEntitiesByIds(List<Integer> ids, List<Car> cars) {
        return List.of();
    }

    @Override
    public void deleteEntityById(int id) {

    }

    @Override
    public void deleteEntitiesByIds(List<Integer> ids) {

    }

    @Override
    public void deleteAllEntities() {

    }
}
