package org.trainee.hw_hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trainee.hw_hibernate.entities.Student;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {

    Student getStudentById(Long id);

    Student getStudentByName(String name);

    List<Student> getAllStudents();

    void saveStudent(Student student);

    void deleteStudent(Long id);

    List<Student> getAllAfter();
}
