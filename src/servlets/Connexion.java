package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.PreparedStatement;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String VUE = "/connexion.jsp";
    public static final String CHAMP_PSEUDO = "pseudo";
    public static final String CHAMP_PASSWORD = "password";


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

        System.out.println(request.getParameter("pseudo"));
        System.out.println(request.getParameter("password"));

        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://localhost:3307/breizhlink";
            String user = "jhaudry";
            String passwd = "jhaudry";

            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");

            String pseudo = request.getParameter("pseudo");
            String password = request.getParameter("password");


            String query = "SELECT * FROM user WHERE user = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, pseudo);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            out.println(pseudo);
            out.println(rs);


            if(rs.next()) {

                /* Création ou récupération de la session */
                HttpSession session = request.getSession();

                /* Mise en session d'une chaîne de caractères */
                session.setAttribute( "pseudo", pseudo );

                /* Récupération de l'objet depuis la session */
                String chaine = (String) session.getAttribute( "pseudo" );

                //   response.sendRedirect(VUE);
                this.getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);

            }
            else {
                System.out.println("else");
            }
            conn.close();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();System.out.println("ou est le driver ?");
        }catch (SQLException e1) {
            e1.printStackTrace();System.out.println("pb de connexion ou de requête");
        }

       // response.getWriter().append("tada");
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String date = request.getParameter(CHAMP_PSEUDO);
        String nom = request.getParameter(CHAMP_PASSWORD);

        doGet(request, response);
    }

}
