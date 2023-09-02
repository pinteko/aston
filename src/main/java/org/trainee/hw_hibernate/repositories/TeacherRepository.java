package org.trainee.hw_hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trainee.hw_hibernate.entities.Teacher;

import java.util.List;
@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Long>  {

    Teacher getTeacherById(Long id);

    Teacher getTeacherByName(String name);

    List<Teacher> getAllTeachers();

    void saveTeacher(Teacher teacher);

    void updateTeacher(Long id, Teacher teacher);

    void deleteTeacher(Long id);
}
