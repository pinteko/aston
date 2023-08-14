package org.trainee.hw_hibernate.servlets;

import org.trainee.hw_hibernate.configs.HibernateUtil;
import org.trainee.hw_hibernate.entities.Group;
import org.trainee.hw_hibernate.entities.Teacher;
import org.trainee.hw_hibernate.services.GroupService;
import org.trainee.hw_hibernate.services.TeacherService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/groups")
public class GroupServlet extends HttpServlet {
    private static final GroupService groupService = new GroupService(HibernateUtil.getSessionFactory());

    private TeacherService teacherService;

    public void init() {
        ServletContext servletContext = getServletContext();
        teacherService = (TeacherService) servletContext.getAttribute("teacherService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "getById" -> getGroupById(request, response);
                case "getByTitle" -> getGroupByTitle(request, response);
                case "getAll" -> getAllGroup(request, response);
                default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void getGroupById(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long groupId = Long.parseLong(idParam);
            Group group = groupService.getGroupById(groupId);
            response.getWriter().println(group.getId());
            response.getWriter().println(group.getTitle());
            response.getWriter().println(group.getTeacher());
            response.getWriter().println(group.getStudents());
        } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getGroupByTitle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("title");
        if (title != null) {
            Group group = groupService.getGroupByName(title);
            response.getWriter().println(group);
        } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getAllGroup(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Group> groups = groupService.getAllGroups();
        response.getWriter().println(groups);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("title");
        String teacherId = request.getParameter("teacherId");
        Group group = new Group();
        group.setTitle(title);
        if (teacherId != null) {
            Long id = Long.parseLong(teacherId);
            Teacher teacher = teacherService.getTeacherById(id);
            group.setTeacher(teacher);
        }
        groupService.saveGroup(group);
        response.sendRedirect(request.getContextPath() + "/groups");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long groupId = Long.parseLong(idParam);
            groupService.deleteGroup(groupId);
        }
    }
}