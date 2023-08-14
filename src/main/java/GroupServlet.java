import org.trainee.hw_jdbc.dao.DataAccessObject;
import org.trainee.hw_jdbc.entities.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

//@WebServlet(name = "GroupServlet", urlPatterns = "/groups")
public class GroupServlet  extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private DataAccessObject dao;

    @Override
    public void init() throws ServletException {
        dao = new DataAccessObject();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String title = request.getParameter("title");

        dao.addGroup(title);

        response.sendRedirect(request.getContextPath() + "/groups");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = dao.getAllGroups();
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("/groups.jsp").forward(request, response);
    }

    public DataAccessObject getDao() {
        return this.dao;
    }

    public void setDao(DataAccessObject dao) {
        this.dao = dao;
    }
}
