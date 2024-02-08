package com.dam2.CRUDStudents.repository;

import com.dam2.CRUDStudents.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
}
