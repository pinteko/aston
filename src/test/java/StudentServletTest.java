import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.trainee.hw_jdbc.dao.DataAccessObject;
import org.trainee.hw_jdbc.entities.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StudentServletTest {

    private static StudentServlet studentServlet;
    private static List<Student> students;
    private static DataAccessObject dataAccessObject;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() throws ServletException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dataAccessObject = mock(DataAccessObject.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/students.jsp")).thenReturn(dispatcher);

        if (studentServlet == null) {
            studentServlet = new StudentServlet();
            studentServlet.setDao(dataAccessObject);
        }

        students = new ArrayList<>();
    }

    @Test
    void testGetStudentsList() throws IOException, ServletException {

        studentServlet.doGet(request, response);

        verify(request).setAttribute("students", students);
        verify(request.getRequestDispatcher("/students.jsp")).forward(request, response);
    }

    @Test
    void testAddStudent() throws IOException, ServletException {
        when(request.getParameter("name")).thenReturn("Alice");
        when(request.getParameter("age")).thenReturn("32");
        when(request.getParameter("group")).thenReturn("1");

        studentServlet.doPost(request, response);

        verify(dataAccessObject).addStudent(anyString(), anyInt(), anyInt());
    }
}
