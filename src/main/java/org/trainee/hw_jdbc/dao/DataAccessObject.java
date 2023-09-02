package org.trainee.hw_jdbc.dao;

import org.trainee.hw_jdbc.entities.Group;
import org.trainee.hw_jdbc.entities.Student;

import java.sql.Connection;
import java.util.List;

public interface DataAccessObject {

    void addStudent(String name, int age, int groupId);

    void addGroup(String title);

    List<Student> getAllStudents();

    List<Group> getAllGroups();
}
