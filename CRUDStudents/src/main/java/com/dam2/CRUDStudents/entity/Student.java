package com.dam2.CRUDStudents.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int studentId;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
}
