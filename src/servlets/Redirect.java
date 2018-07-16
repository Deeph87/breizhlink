package servlets;

import DAO.SimpleUrls;
import DAO.UrlsPasswords;
import beans.ComplexUrls;
import beans.Statistics;
import beans.URLSPasswords;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String generatedURL = request.getRequestURL().toString();

        String error;

        beans.SimpleUrls simpleUrls = new beans.SimpleUrls();
        simpleUrls.setGeneratedUrl(generatedURL);

        SimpleUrls DAOSimpleUrls = new SimpleUrls(simpleUrls);
        simpleUrls = DAOSimpleUrls.getUrlByGeneratedUrl();

        int urlId = simpleUrls.getId();

        if (DAOSimpleUrls.urlNeedsPassword()) {
            request.setAttribute("urlId", urlId);
            this.getServletContext().getRequestDispatcher("/views/pages/url-password.jsp").forward(request, response);
            return;
        }

        DAO.ComplexUrls DAOcomplexUrls = new DAO.ComplexUrls();

        if (!DAOcomplexUrls.canAccessToUrl(urlId)) {
            error = "Cette url a expiré";
            request.setAttribute("error", error);
            request.setAttribute("urlId", urlId);
            getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }

        Statistics statisticsBean = new Statistics();
        statisticsBean.setUrl_id(simpleUrls.getId());

        DAO.Statistics statisticsDAO = new DAO.Statistics(statisticsBean);
        statisticsDAO.createOrUpdate();

        response.sendRedirect(simpleUrls.getDestinationUrl());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameterMap().containsKey("password") ? request.getParameter("password") : "";
        String urlId = request.getParameterMap().containsKey("urlId") ? request.getParameter("urlId") : "";

        String error;

        if (password.isEmpty()) {
            error = "Champs requis.";
            request.setAttribute("error", error);
            request.setAttribute("urlId", urlId);
            getServletContext().getRequestDispatcher("/views/pages/url-password.jsp").forward(request, response);
        }

        URLSPasswords passwordBean = new URLSPasswords();
        passwordBean.setPassword(password);

        UrlsPasswords DAOPasswords = new UrlsPasswords(passwordBean);

        if (!DAOPasswords.canAccessToUrl(urlId)) {
            error = "Mauvais mot de passe";
            request.setAttribute("error", error);
            request.setAttribute("urlId", urlId);
            getServletContext().getRequestDispatcher("/views/pages/url-password.jsp").forward(request, response);
            return;
        }

        beans.SimpleUrls simpleUrlsBean = new beans.SimpleUrls();
        simpleUrlsBean.setId(Integer.parseInt(urlId));

        SimpleUrls simpleUrlsDAO = new SimpleUrls(simpleUrlsBean);

        simpleUrlsDAO.getDestinationUrlById();

        Statistics statisticsBean = new Statistics();
        statisticsBean.setUrl_id(Integer.parseInt(urlId));

        DAO.Statistics statisticsDAO = new DAO.Statistics(statisticsBean);
        statisticsDAO.createOrUpdate();

        response.sendRedirect(simpleUrlsBean.getDestinationUrl());
    }
}
