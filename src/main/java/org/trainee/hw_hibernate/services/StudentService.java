package org.trainee.hw_hibernate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trainee.hw_hibernate.dtos.StudentDto;
import org.trainee.hw_hibernate.entities.Student;
import org.trainee.hw_hibernate.mappers.StudentMapper;
import org.trainee.hw_hibernate.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentDto getStudentById(Long id) {
        return studentMapper.entityToDto(studentRepository.getStudentById(id));
    }

    public StudentDto getStudentByName(String name) {
        return studentMapper.entityToDto(studentRepository.getStudentByName(name));
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.getAllStudents();
        return students.stream().map(studentMapper::entityToDto).collect(Collectors.toList());
    }

    public void saveStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        studentRepository.saveStudent(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }
}
