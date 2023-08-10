package org.trainee.hw_hibernate.repositories;

import org.trainee.hw_hibernate.entities.Student;

import java.util.List;

public interface StudentRepository {

    Student getStudentById(Long id);

    Student getStudentByName(String name);

    List<Student> getAllStudents();

    void saveStudent(Student student);

    void deleteStudent(Long id);

    List<Student> getAllAfter();
}
