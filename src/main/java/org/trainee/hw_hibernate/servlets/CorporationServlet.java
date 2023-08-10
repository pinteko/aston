package org.trainee.hw_hibernate.servlets;

import org.trainee.hw_hibernate.configs.HibernateUtil;
import org.trainee.hw_hibernate.entities.*;
import org.trainee.hw_hibernate.services.CorporationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/corporations")
public class CorporationServlet extends HttpServlet {

    private static final CorporationService corporationService = new CorporationService(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "getById" -> getCorporationById(request, response);
            case "getByType" -> getCorporationByType(request, response);
            default -> getAllCorporations(request, response);
            }
    }

    private void getCorporationById(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long corporationId = Long.parseLong(idParam);
            AbstractCorporation corporation = corporationService.getCorporationById(corporationId);
            response.getWriter().println(corporation);
        }
        else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getCorporationByType(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("name");
        if (title != null) {
            AbstractCorporation corporation = corporationService.getCorporationByName(title);
            response.getWriter().println(corporation);
        }
        else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getAllCorporations(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<AbstractCorporation> corporations = corporationService.getAllCorporations();
        response.getWriter().println(corporations);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String product = request.getParameter("product");
        AbstractCorporation corporation = null;
        if (type != null) {
            switch (type) {
                case "Microsoft" -> {   corporation = new MicrosoftCorporation();
                                        ((MicrosoftCorporation) corporation).setMicrosoftProduct(product);}
                case "Tesla" -> {       corporation = new TeslaCorporation();
                                        ((TeslaCorporation) corporation).setTeslaProduct(product);}
                case "Apple" -> {       corporation = new AppleCorporation();
                                        ((AppleCorporation) corporation).setAppleProduct(product);}
                default ->              response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        assert corporation != null;
        corporation.setName(name);

        corporationService.saveCorporation(corporation);
        getAllCorporations(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long corporationId = Long.parseLong(idParam);
            corporationService.deleteCorporation(corporationId);
        }
        getAllCorporations(request, response);
    }
}
