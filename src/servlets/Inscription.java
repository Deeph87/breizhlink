package servlets;

import DAO.UserTypes;
import DAO.Users;
import beans.User;
import beans.UserType;
import services.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/inscription")
public class Inscription extends HttpServlet{
    public static final String VUE = "/views/pages/inscription.jsp";

    private UserTypes DAOUserTypes;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        this.DAOUserTypes = new UserTypes();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        ArrayList<UserType> typesList = this.DAOUserTypes.getUserTypes();

        request.setAttribute("typesList", typesList);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameterMap().containsKey("email") ? request.getParameter("email") : "";
        String password = request.getParameterMap().containsKey("password") ? request.getParameter("password") : "";
        String typeId = request.getParameterMap().containsKey("type") ? request.getParameter("type") : "-1";

        String error;
        String success;

        if (email.isEmpty() || password.isEmpty() || typeId.isEmpty() ) {
            error = "Champs requis.";
            request.setAttribute("error", error);
            getServletContext().getRequestDispatcher("/register").forward(request, response);
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
        user.setTypeId(typeId);
        user.setEnabled(true);

        Users users = new Users(user);

        users.save();

        success = "Votre compte a été créé avec succés !";
        request.setAttribute("success", success);

        this.getServletContext().getRequestDispatcher("/views/pages/home.jsp").forward(request, response);
    }

}
