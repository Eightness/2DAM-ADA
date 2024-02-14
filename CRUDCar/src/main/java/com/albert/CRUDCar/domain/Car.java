package com.albert.CRUDCar.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car {
    public enum Type {
        ELECTRIC,
        OIL,
        DIESEL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "carId")
    private int carId;

    @NotBlank(message = "Brand cannot be empty")
    @Column(name = "brand")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    @Column(name = "model")
    private String model;

    @Positive(message = "Price must be positive")
    @Column(name = "price")
    private double price;

    @NotNull(message = "Type cannot be null")
    @Column(name = "type")
    private Type type;
}
