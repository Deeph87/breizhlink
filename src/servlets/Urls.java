package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.SimpleUrls;
import DAO.UrlsPasswords;
import beans.URLSPasswords;
import org.apache.commons.validator.routines.UrlValidator;
import services.URLShortener;

import java.security.MessageDigest;

public class Urls extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originUrl = request.getParameterMap().containsKey("originUrl") ? request.getParameter("originUrl") : "";
        String hasPasswordSecure = request.getParameterMap().containsKey("hasPasswordSecure") ? request.getParameter("hasPasswordSecure") : "";
        String password = request.getParameterMap().containsKey("password") ? request.getParameter("password") : "";

        UrlValidator urlValidator = new UrlValidator();

        String error;

        if (originUrl.isEmpty()) {
            error = "Champs requis.";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }

        if (!urlValidator.isValid(originUrl)) {
            error = "L'url entrée n'est pas valide.";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }

        if (!hasPasswordSecure.isEmpty() && password.isEmpty()) {
            error = "Le mot de passe ne peut pas être vide";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }

        URLShortener urlShortener = new URLShortener(5, "http://localhost:8282/s/");
        String generatedUrl = urlShortener.shortenURL(originUrl);

        beans.SimpleUrls simpleUrls = new beans.SimpleUrls();
        simpleUrls.setDestinationUrl(originUrl);
        simpleUrls.setGeneratedUrl(generatedUrl);

        SimpleUrls DAOSimpleUrls = new SimpleUrls(simpleUrls);
        DAOSimpleUrls.save();

        if (!password.isEmpty()) {
            String securePassword = "";
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                messageDigest.update(password.getBytes());
                securePassword = new String(messageDigest.digest());
            } catch (Exception exception) {
                error = "Encryption failed !";
                request.setAttribute("error", error);
                getServletContext().getRequestDispatcher("/home").forward(request, response);
            }

            int generatedKey = DAOSimpleUrls.getGeneratedKey();

            URLSPasswords urlsPasswords = new URLSPasswords();
            urlsPasswords.setUrlId(generatedKey);
            urlsPasswords.setPassword(securePassword);

            UrlsPasswords DAOURLSPasswords = new UrlsPasswords(urlsPasswords);
            DAOURLSPasswords.save();
        }

        request.setAttribute("shortenUrl", generatedUrl);

        getServletContext().getRequestDispatcher("/views/pages/shorten.jsp").forward(request, response);
    }
}
