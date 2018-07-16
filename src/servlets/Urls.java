package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import DAO.SimpleUrls;
import DAO.UrlsPasswords;
import beans.ComplexUrls;
import beans.Statistics;
import beans.URLSPasswords;
import org.apache.commons.validator.routines.UrlValidator;
import services.Password;
import services.URLShortener;

import java.security.MessageDigest;
import java.security.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Urls extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originUrl = request.getParameterMap().containsKey("originUrl") ? request.getParameter("originUrl") : "";
        String hasPasswordSecure = request.getParameterMap().containsKey("hasPasswordSecure") ? request.getParameter("hasPasswordSecure") : "";
        String password = request.getParameterMap().containsKey("password") ? request.getParameter("password") : "";
        String validity = request.getParameterMap().containsKey("validity") ? request.getParameter("validity") : "";

        UrlValidator urlValidator = new UrlValidator();

        String error;

        if (originUrl.isEmpty()) {
            error = "Champs requis.";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
            return;
        }

        if (!urlValidator.isValid(originUrl)) {
            error = "L'url entrée n'est pas valide.";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
            return;
        }

        if (!hasPasswordSecure.isEmpty() && password.isEmpty()) {
            error = "Le mot de passe ne peut pas être vide";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
            return;
        }

        URLShortener urlShortener = new URLShortener(5, "http://localhost:8282/s/");
        String generatedUrl = urlShortener.shortenURL(originUrl);

        beans.SimpleUrls simpleUrls = new beans.SimpleUrls();
        simpleUrls.setDestinationUrl(originUrl);
        simpleUrls.setGeneratedUrl(generatedUrl);

        HttpSession session = request.getSession(false);

        if(session.getAttribute("userId") != null){
            int userId = (Integer) session.getAttribute("userId");
            simpleUrls.setUserId(userId);
        } else {
            simpleUrls.setUserId(-1);
        }

        SimpleUrls DAOSimpleUrls = new SimpleUrls(simpleUrls);
        DAOSimpleUrls.save();

        int generatedKey = DAOSimpleUrls.getGeneratedKey();

        // Password part
        if (!password.isEmpty()) {
            Password passwordService = new Password(password);
            String securePassword = passwordService.encrypt();

            if(securePassword.isEmpty()){
                error = "Encryption failed !";
                request.setAttribute("error", error);
                getServletContext().getRequestDispatcher("/home").forward(request, response);
            }

            URLSPasswords urlsPasswords = new URLSPasswords();
            urlsPasswords.setUrlId(generatedKey);
            urlsPasswords.setPassword(securePassword);

            UrlsPasswords DAOURLSPasswords = new UrlsPasswords(urlsPasswords);
            DAOURLSPasswords.save();
        }

        ComplexUrls complexUrlsBean = new ComplexUrls();

        // Validity Duration
        if(!validity.isEmpty()) {

            long validitySeconds = Integer.parseInt(validity) * 60;

            Instant nowInstant = Instant.now();
            long nowLong = nowInstant.getEpochSecond();

            long maxDateLong = nowLong + validitySeconds;

            complexUrlsBean.setUrlId(generatedKey);
            complexUrlsBean.setMaxDate(maxDateLong);

        }

        DAO.ComplexUrls DAOComplexUrl = new DAO.ComplexUrls(complexUrlsBean);
        DAOComplexUrl.save();

        request.setAttribute("shortenUrl", generatedUrl);

        getServletContext().getRequestDispatcher("/views/pages/shorten.jsp").forward(request, response);
    }
}
