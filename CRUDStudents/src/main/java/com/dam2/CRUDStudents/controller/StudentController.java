package com.dam2.CRUDStudents.controller;

import com.dam2.CRUDStudents.entity.Student;
import com.dam2.CRUDStudents.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private IStudentRepository iStudentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return iStudentRepository.findAll();
    }

    @PostMapping("/student/new")
    public Student addStudent(@RequestBody Student newStudent) {
        return iStudentRepository.save(newStudent);
    }

    @PatchMapping("/student/edit/{id}")
    public Student editStudent(
            @PathVariable Integer id,
            @RequestBody Student student
    ) {
        Optional<Student> studentOptional = iStudentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student currentStudent = studentOptional.get();
            currentStudent.setName(student.getName());
            currentStudent.setLastName(student.getLastName());
            currentStudent.setEmail(student.getEmail());
            return iStudentRepository.save(currentStudent);
        }

        return null;
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentById(@PathVariable Integer id) {
        return iStudentRepository.findById(id);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        iStudentRepository.deleteById(id);
    }
}
