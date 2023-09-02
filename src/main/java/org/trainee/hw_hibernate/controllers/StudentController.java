package org.trainee.hw_hibernate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.trainee.hw_hibernate.dtos.StudentDto;
import org.trainee.hw_hibernate.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController{
    private final StudentService studentService;

    @GetMapping()
    public List<StudentDto> getAllStudents() {
       return studentService.getAllStudents();
   }

    @GetMapping("{/id}")
    public StudentDto getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping()
    public StudentDto getStudentByName(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }

    @PostMapping()
    public void createStudent(@RequestBody StudentDto studentDto) {
       studentService.saveStudent(studentDto);
    }

    @DeleteMapping("{/id}")
    public void deleteStudent(@PathVariable("id") Long id) {
       studentService.deleteStudent(id);
    }
}