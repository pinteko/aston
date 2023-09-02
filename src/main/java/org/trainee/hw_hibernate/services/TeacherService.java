package org.trainee.hw_hibernate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trainee.hw_hibernate.entities.Teacher;
import org.trainee.hw_hibernate.repositories.TeacherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Teacher getTeacherById(Long id) {
        return teacherRepository.getTeacherById(id);
    }

    public Teacher getTeacherByName(String name) {
        return teacherRepository.getTeacherByName(name);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAllTeachers();
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepository.saveTeacher(teacher);
    }

    public void updateTeacher(Long id, Teacher teacher) {
        teacherRepository.updateTeacher(id, teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteTeacher(id);
    }
}