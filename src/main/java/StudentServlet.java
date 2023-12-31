import org.trainee.hw_jdbc.dao.DataAccessObjectImpl;
import org.trainee.hw_jdbc.entities.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

//@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet  extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private DataAccessObjectImpl dao;

    @Override
    public void init() throws ServletException {
        dao = new DataAccessObjectImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int groupId = Integer.parseInt(request.getParameter("group"));

        dao.addStudent(name, age, groupId);

        response.sendRedirect(request.getContextPath() + "/students");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = dao.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }

    public DataAccessObjectImpl getDao() {
        return this.dao;
    }

    public void setDao(DataAccessObjectImpl dao) {
        this.dao = dao;
    }
}
