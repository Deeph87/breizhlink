package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import beans.DAOSimpleUrls;
import beans.SimpleUrls;
import org.apache.commons.validator.routines.UrlValidator;
import services.URLShortener;

public class Urls extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originUrl = request.getParameter("originUrl");

        UrlValidator urlValidator = new UrlValidator();
        URLShortener urlShortener = new URLShortener(5, "http://localhost:8282/s/");
        String generatedUrl = urlShortener.shortenURL(originUrl);

        String error;

        if(originUrl.isEmpty()){
            error = "Champs requis.";
            request.setAttribute("error",error);
            getServletContext().getRequestDispatcher("/home").forward(request,response);
        }

        if(!urlValidator.isValid(originUrl)){
            error = "L'url n'est pas valide.";
            request.setAttribute("error",error);
            getServletContext().getRequestDispatcher("/home").forward(request,response);
        }

        SimpleUrls simpleUrls = new SimpleUrls();
        simpleUrls.setDestinationUrl(originUrl);
        simpleUrls.setGeneratedUrl(generatedUrl);

        DAOSimpleUrls DAOSimpleUrls = new DAOSimpleUrls(simpleUrls);

        DAOSimpleUrls.save();

        request.setAttribute("shortenUrl", generatedUrl);

        getServletContext().getRequestDispatcher("/views/pages/shorten.jsp").forward(request,response);
    }
}
