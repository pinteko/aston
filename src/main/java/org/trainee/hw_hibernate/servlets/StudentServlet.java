package org.trainee.hw_hibernate.servlets;

import org.trainee.hw_hibernate.configs.HibernateUtil;
import org.trainee.hw_hibernate.entities.Group;
import org.trainee.hw_hibernate.entities.Student;
import org.trainee.hw_hibernate.services.GroupService;
import org.trainee.hw_hibernate.services.StudentService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private static final StudentService studentService = new StudentService(HibernateUtil.getSessionFactory());
    private GroupService groupService;

    public void init() {
        ServletContext servletContext = getServletContext();
        groupService = (GroupService) servletContext.getAttribute("groupService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "getById" -> getStudentById(request, response);
                case "getByName" -> getStudentByName(request, response);
                case "getAll" -> getAllStudents(request, response);
                case "getAllAfter" -> getAllAfter(request, response);
                default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void getStudentById(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Student student = studentService.getStudentById(id);
            response.getWriter().println(student.getId());
            response.getWriter().println(student.getName());
            response.getWriter().println(student.getAge());
            response.getWriter().println(student.getGroups());
//            response.getWriter().println(student.getTeachers());
        } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getStudentByName(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        if (name != null) {
            Student student = studentService.getStudentByName(name);
            response.getWriter().println(student);
        } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getAllAfter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Student> students = studentService.getAllAfter();
        response.getWriter().println(students);
    }

    private void getAllStudents(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Student> students = studentService.getAllStudents();
        response.getWriter().println(students);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String studentId = request.getParameter("id");
        String nameParam = request.getParameter("name");
        String ageParam = request.getParameter("age");
        String groupIdParam = request.getParameter("groupId");
        Student student;
        if (studentId != null)
            student = studentService.getStudentById(Long.parseLong(studentId));
        else
            student = new Student();
        student.setName(nameParam);
        student.setAge(Integer.parseInt(ageParam));
        if (groupIdParam != null) {
            Long groupId = Long.parseLong(groupIdParam);
            Group group = groupService.getGroupById(groupId);
            student.getGroups().add(group);
        }
        studentService.saveStudent(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long studentId = Long.parseLong(idParam);
            studentService.deleteStudent(studentId);
        }
    }
}