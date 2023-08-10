package org.trainee.hw_hibernate.services;

import org.hibernate.SessionFactory;
import org.trainee.hw_hibernate.entities.Teacher;
import org.trainee.hw_hibernate.repositories.TeacherRepository;
import org.trainee.hw_hibernate.repositories.TeacherRepositoryImpl;

import java.util.List;

public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(SessionFactory sessionFactory) {
        teacherRepository = new TeacherRepositoryImpl(sessionFactory);
    }

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

    public void deleteTeacher(Long id) {
        teacherRepository.deleteTeacher(id);
    }
}