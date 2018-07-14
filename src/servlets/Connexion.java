package servlets;

import DAO.Users;
import beans.User;
import services.Password;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.PreparedStatement;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {

    public static final String VUE = "/views/pages/connexion.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameterMap().containsKey("email") ? request.getParameter("email") : "";
        String password = request.getParameterMap().containsKey("password") ? request.getParameter("password") : "";

        String error;
        String success;

        if (email.isEmpty() || password.isEmpty() ) {
            error = "Champs requis.";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/views/pages/connexion.jsp").forward(request, response);
        }

        User user = new User();

        Password passwordService = new Password(password);
        String encryptedPassword = passwordService.encrypt();

        if(encryptedPassword.isEmpty()){
            error = "Encryption failed !";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }

        user.setEmail(email);
        user.setPassword(encryptedPassword);

        Users usersDAO = new Users(user);

        if(usersDAO.userExists()){
            HttpSession session = request.getSession();

            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userId", user.getId());

            response.sendRedirect("/home");

        } else {
            error = "Mauvais email ou mauvais mot de passe, veuillez réessayer";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/views/pages/connexion.jsp").forward(request, response);
        }
    }

}
