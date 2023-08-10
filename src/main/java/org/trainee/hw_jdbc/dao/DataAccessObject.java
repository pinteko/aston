package org.trainee.hw_jdbc.dao;

import org.trainee.hw_jdbc.entities.Group;
import org.trainee.hw_jdbc.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObject {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "berbedos31";

    public void addStudent(String name, int age, int groupId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO students (name, age, group_id) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGroup(String title) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO groups (title) VALUES (?)")) {
            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
             while (rs.next()) {
                 Student student = new Student();
                 student.setId(rs.getInt("id"));
                 student.setName(rs.getString("name"));
                 student.setAge(rs.getInt("age"));

                 int studentId = student.getId();
                 List<Group> groups = getGroupsByStudentId(connection, studentId);
                 student.setGroups(groups);

                 students.add(student);

             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private List<Group> getGroupsByStudentId(Connection connection, int studentId) throws SQLException {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT groups.* FROM groups INNER JOIN students_groups ON groups.id = students_groups.group_id WHERE students_groups.student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Group group = new Group();
                    group.setId(resultSet.getInt("id"));
                    group.setTitle(resultSet.getString("title"));

                    groups.add(group);
                }
            }
        }
        return groups;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM groups";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setTitle(rs.getString("title"));

                int groupId = group.getId();
                List<Student> students = getStudentsByGroupId(connection, groupId);
                group.setStudents(students);

                groups.add(group);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    private List<Student> getStudentsByGroupId(Connection connection, int groupId) throws SQLException{
        List<Student> students = new ArrayList<>();
        String sql = "SELECT students.* FROM students INNER JOIN students_groups ON students.id = students_groups.student_id WHERE students_groups.group_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, groupId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setAge(resultSet.getInt("age"));

                    students.add(student);
                }
            }
        }
        return students;
    }
}
