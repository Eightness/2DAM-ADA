package com.albert.CRUDCar.application.implementation;

import com.albert.CRUDCar.application.service.GenericService;
import com.albert.CRUDCar.domain.Car;
import com.albert.CRUDCar.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class CarService implements GenericService<Car> {
    @Autowired
    private ICarRepository carRepository;

    @Override
    public Car getEntityById(int id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.orElse(null);
    }

    @Override
    public List<Car> getAllEntities(int pageNumber, int pageSize) {
        return carRepository.findAll();
    }

    @Override
    public Car addEntity(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateEntityById(int id, Car car) {
        if (carRepository.existsById(id)) {
            car.setCarId(id);
            return carRepository.save(car);
        }
        return null;
    }

    @Override
    public Car modifyEntityById(int id, Car car) {
        return updateEntityById(id, car);
    }

    @Override
    public void deleteEntityById(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public void deleteAllEntities() {
        carRepository.deleteAll();
    }
}
