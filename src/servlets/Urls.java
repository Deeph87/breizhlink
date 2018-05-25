package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import beans.DAOSimpleUrls;
import beans.SimpleUrls;
import beans.URLSPasswords;
import org.apache.commons.validator.routines.UrlValidator;
import services.URLShortener;

public class Urls extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originUrl = request.getParameterMap().containsKey("originUrl") ? request.getParameter("originUrl") : "";
        String hasPasswordSecure = request.getParameterMap().containsKey("hasPasswordSecure") ? request.getParameter("hasPasswordSecure") : "";
        String password = request.getParameterMap().containsKey("password") ? request.getParameter("password") : "";

        UrlValidator urlValidator = new UrlValidator();

        String error;

        if(originUrl.isEmpty()){
            error = "Champs requis.";
            request.setAttribute("error",error);
            getServletContext().getRequestDispatcher("/home").forward(request,response);
        }

        if(!urlValidator.isValid(originUrl)){
            error = "L'url entrée n'est pas valide.";
            request.setAttribute("error",error);
            getServletContext().getRequestDispatcher("/home").forward(request,response);
        }

        if(!hasPasswordSecure.isEmpty() && password.isEmpty()){
            error = "Le mot de passe ne peut pas être vide";
            request.setAttribute("error",error);
            getServletContext().getRequestDispatcher("/home").forward(request,response);
        }

        URLShortener urlShortener = new URLShortener(5, "http://localhost:8282/s/");
        String generatedUrl = urlShortener.shortenURL(originUrl);

        SimpleUrls simpleUrls = new SimpleUrls();
        simpleUrls.setDestinationUrl(originUrl);
        simpleUrls.setGeneratedUrl(generatedUrl);

        if(!password.isEmpty()){
//            String securePassword =  get_SHA_

            URLSPasswords urlsPasswords = new URLSPasswords();
//            urlsPasswords.setPassword();
//            simpleUrls.setGeneratedUrl(generatedUrl);
        }

        DAOSimpleUrls DAOSimpleUrls = new DAOSimpleUrls(simpleUrls);

        DAOSimpleUrls.save();

        request.setAttribute("shortenUrl", generatedUrl);

        getServletContext().getRequestDispatcher("/views/pages/shorten.jsp").forward(request,response);
    }
}
