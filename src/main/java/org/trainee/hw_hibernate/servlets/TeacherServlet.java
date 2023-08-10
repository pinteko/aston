package org.trainee.hw_hibernate.servlets;

import org.trainee.hw_hibernate.configs.HibernateUtil;
import org.trainee.hw_hibernate.entities.Teacher;
import org.trainee.hw_hibernate.services.TeacherService;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {
    private static final TeacherService teacherService = new TeacherService(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "getById" -> getTeacherById(request, response);
                case "getByName" -> getTeacherByName(request, response);
                case "getAll" -> getAllTeachers(request, response);
                default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void getTeacherById(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Teacher teacher = teacherService.getTeacherById(id);
            response.getWriter().println(teacher.getId());
            response.getWriter().println(teacher.getName());
            response.getWriter().println(teacher.getStudents());
            response.getWriter().println(teacher.getGroups());
        } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getTeacherByName(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        if (name != null) {
            Teacher teacher = teacherService.getTeacherByName(name);
            response.getWriter().println(teacher);
        } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getAllTeachers(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Teacher> teachers = teacherService.getAllTeachers();
        response.getWriter().println(teachers);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");

        Teacher teacher = new Teacher();
        teacher.setName(name);

        teacherService.saveTeacher(teacher);
        response.sendRedirect(request.getContextPath() + "/teachers");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long teacherId = Long.parseLong(idParam);
            teacherService.deleteTeacher(teacherId);
        }
    }
}