package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Statistics extends HttpServlet {
    public static final String VUE = "/views/pages/statistics.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");
        String idUrl = pathParts[1]; // {value}

        HttpSession session = request.getSession(false);

        if(session.getAttribute("userEmail") == null) {
            String error = "Accés interdit";
            request.setAttribute("error", error);
            this.getServletContext().getRequestDispatcher("/home").forward(request, response);
            return;
        }

        DAO.Statistics satisticsDAO = new DAO.Statistics();
        HashMap<String, ArrayList> mapStats = satisticsDAO.getStatsByUrlId(Integer.parseInt(idUrl));

        ArrayList days = mapStats.get("days");
        ArrayList visites = mapStats.get("visites");

        request.setAttribute("days", this.arrayListToString(days));
        request.setAttribute("visites", this.arrayListToString(visites));

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    private String arrayListToString(ArrayList arrayList) {
        StringBuilder str = new StringBuilder();
        str.append("[");

        for(int i = 0; i < arrayList.size(); i++) {
            String item = (String) arrayList.get(i);
            str.append("'");
            str.append(item);
            str.append("'");
            if(i < arrayList.size() - 1){
                str.append(",");
            }
        }

        str.append("]");
        return str.toString();
    }
}
