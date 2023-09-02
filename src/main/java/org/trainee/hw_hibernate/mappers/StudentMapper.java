package org.trainee.hw_hibernate.mappers;

import org.springframework.stereotype.Component;
import org.trainee.hw_hibernate.dtos.StudentDto;
import org.trainee.hw_hibernate.entities.Student;

@Component
public class StudentMapper {

    public Student dtoToEntity (StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setGroups(studentDto.getGroups());
        return student;
    }

    public StudentDto entityToDto (Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setGroups(student.getGroups());
        return studentDto;
    }
}
