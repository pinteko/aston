package org.trainee.hw_hibernate.repositories;

import org.trainee.hw_hibernate.entities.Teacher;

import java.util.List;

public interface TeacherRepository {

    Teacher getTeacherById(Long id);

    Teacher getTeacherByName(String name);

    List<Teacher> getAllTeachers();

    void saveTeacher(Teacher teacher);

    void deleteTeacher(Long id);
}
