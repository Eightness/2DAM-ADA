package com.albert.CRUDCar.controller;

import com.albert.CRUDCar.application.implementation.CarService;
import com.albert.CRUDCar.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    @Autowired
    private CarService carService;

    //-----------------------------------------------------------------------------------------

    // Get requests.
    @GetMapping("/get/{id}")
    public Car getCarById(@PathVariable Integer id) {
        return carService.getEntityById(id);
    }

    @GetMapping("/get/various/{ids}")
    public List<Car> getCarsByIds(@PathVariable List<Integer> ids) {
        return carService.getEntitiesByIds(ids);
    }

    @GetMapping("/get/all")
    public List<Car> getAllCars() {
        return carService.getAllEntities(1, 5);
    }

    //-----------------------------------------------------------------------------------------

    // Post requests.
    @PostMapping("/add/new")
    public Car addCar(@RequestBody Car newCar) {
        return carService.addEntity(newCar);
    }

    @PostMapping("/add/various")
    public List<Car> addCars(@RequestBody List<Car> newCars) {
        return carService.addEntities(newCars);
    }

    //-----------------------------------------------------------------------------------------

    // Put requests.
    @PutMapping("/update/{id}")
    public Car updateCar(@PathVariable Integer id, @RequestBody Car carToUpdate) {
        return carService.updateEntityById(id, carToUpdate);
    }

    @PutMapping("/update/various/{ids}")
    public List<Car> updateCars(@PathVariable List<Integer> ids, @RequestBody List<Car> carsToUpdate) {
        return carService.updateEntitiesByIds(ids, carsToUpdate);
    }

    //-----------------------------------------------------------------------------------------

    // Patch requests.
    @PatchMapping("/edit/{id}")
    public Car editCar(@PathVariable Integer id, @RequestBody Car carToEdit) {
        return carService.modifyEntityById(id, carToEdit);
    }

    @PatchMapping("/edit/various/{ids}")
    public List<Car> editCars(@PathVariable List<Integer> ids, @RequestBody List<Car> carsToEdit) {
        return carService.modifyEntitiesByIds(ids, carsToEdit);
    }

    //-----------------------------------------------------------------------------------------

    // Delete requests.
    @DeleteMapping("/car/delete/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        carService.deleteEntityById(id);
    }

    @DeleteMapping("/car/delete/{ids}")
    public void deleteCarsById(@RequestParam List<Integer> ids) {
        carService.deleteEntitiesByIds(ids);
    }

    @DeleteMapping("/car/delete/all")
    public void deleteAllCars() {
        carService.deleteAllEntities();
    }
}
