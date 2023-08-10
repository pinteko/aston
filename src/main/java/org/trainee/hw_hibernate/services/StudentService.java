package org.trainee.hw_hibernate.services;

import org.hibernate.SessionFactory;
import org.trainee.hw_hibernate.entities.Student;
import org.trainee.hw_hibernate.repositories.StudentRepository;
import org.trainee.hw_hibernate.repositories.StudentRepositoryImpl;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(SessionFactory sessionFactory) {
        studentRepository = new StudentRepositoryImpl(sessionFactory);
    }

    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    public Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void saveStudent(Student student) {
        studentRepository.saveStudent(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }

    public List<Student> getAllAfter() {
        return studentRepository.getAllAfter();
    }
}
