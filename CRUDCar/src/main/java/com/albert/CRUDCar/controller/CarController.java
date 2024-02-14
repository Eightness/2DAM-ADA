package com.albert.CRUDCar.controller;

import com.albert.CRUDCar.domain.Car;
import com.albert.CRUDCar.application.implementation.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/car")
@Tag(name = "Coches", description = "Catálogo de coches")
public class CarController {
    @Autowired
    private CarService carService;

    //-----------------------------------------------------------------------------------------

    // Get requests.
    @Operation(summary = "Obtiene un coche determinado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Existe el coche",
                    content = @Content(schema = @Schema(implementation = Car.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "El coche no existe",
                    content = @Content(schema = @Schema(implementation= ResponseEntity.class)))
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getCarById(@PathVariable Integer id) {
        Car car = carService.getEntityById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtiene el listado de coches")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de coches",
                    content = @Content(array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @Schema(implementation = Car.class)))),
    })
    @GetMapping("/get/all")
    public List<Car> getAllCars() {
        return carService.getAllEntities(1, 5);
    }

    //-----------------------------------------------------------------------------------------

    // Post requests.
    @Operation(summary = "Registra un nuevo coche")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Se registra el coche",
                    content = @Content(schema = @Schema(implementation = Car.class)))
    })
    @PostMapping("/add/new")
    public ResponseEntity<Object> addCar(@Valid @RequestBody Car newCar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        Car addedCar = carService.addEntity(newCar);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCar);
    }

    //-----------------------------------------------------------------------------------------

    // Put requests.
    @Operation(summary = "Modifica un coche en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se modifica el coche",
                    content = @Content(schema = @Schema(implementation = Car.class))),
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Integer id, @Valid @RequestBody Car carToUpdate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        Car updatedCar = carService.updateEntityById(id, carToUpdate);
        if (updatedCar != null) {
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //-----------------------------------------------------------------------------------------

    // Delete requests.
    @Operation(summary = "Elimina un coche")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Se elimina el coche",
                    content = @Content(schema = @Schema(implementation=ResponseEntity.class))),
    })
    @DeleteMapping("/car/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) {
        carService.deleteEntityById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Elimina todos los coches")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Se eliminan todos los coches",
                    content = @Content(schema = @Schema(implementation=ResponseEntity.class))),
    })
    @DeleteMapping("/car/delete/all")
    public ResponseEntity<Object> deleteAllCars() {
        carService.deleteAllEntities();
        return ResponseEntity.noContent().build();
    }
}
