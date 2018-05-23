package servlets;

import beans.DAOSimpleUrls;
import beans.SimpleUrls;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String generatedURL = request.getRequestURL().toString();

        SimpleUrls simpleUrls = new SimpleUrls();
        simpleUrls.setGeneratedUrl(generatedURL);

        DAOSimpleUrls DAOSimpleUrls = new DAOSimpleUrls(simpleUrls);

        String destinationUrl = DAOSimpleUrls.getDestinationUrlByGeneratedUrl();
        response.sendRedirect(destinationUrl);
    }
}
