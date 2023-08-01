import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.trainee.hw_jdbc.dao.DataAccessObject;
import org.trainee.hw_jdbc.entities.Group;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GroupServletTest {
    private static GroupServlet groupServlet;
    private static List<Group> groups;
    private static DataAccessObject dataAccessObject;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dataAccessObject = mock(DataAccessObject.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/groups.jsp")).thenReturn(dispatcher);

        if (groupServlet == null) {
            groupServlet = new GroupServlet();
            groupServlet.setDao(dataAccessObject);
        }

        groups = new ArrayList<>();
    }

    @Test
    void testGetGroupsList() throws IOException, ServletException {

        groupServlet.doGet(request, response);

        verify(request).setAttribute("groups", groups);
        verify(request.getRequestDispatcher("/groups.jsp")).forward(request, response);
    }

    @Test
    void testAddGroup() throws IOException, ServletException {
        when(request.getParameter("title")).thenReturn("blackberry");

        groupServlet.doPost(request, response);

        verify(dataAccessObject).addGroup(any());
    }
}
