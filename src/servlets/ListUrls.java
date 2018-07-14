package servlets;

import DAO.SimpleUrls;
import DAO.UrlsPasswords;
import beans.URLSPasswords;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ListUrls extends HttpServlet {
    public static final String VUE = "/views/pages/list-urls.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        beans.SimpleUrls urlBean = new beans.SimpleUrls();
        SimpleUrls urlDAO = new SimpleUrls(urlBean);

        HttpSession session = request.getSession(false);

        if(session.getAttribute("userEmail") == null) {
            String error = "Accés interdit";
            request.setAttribute("error", error);
            this.getServletContext().getRequestDispatcher("/home").forward(request, response);
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        ArrayList<beans.SimpleUrls> listUrls = urlDAO.getUrlsByUserId(userId);

        request.setAttribute("listUrls", listUrls);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
