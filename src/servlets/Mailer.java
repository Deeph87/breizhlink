package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer extends HttpServlet {
    public void init() {}

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.from", "adresse@expediteur.com");
        Session sess = Session.getInstance(props, null);
        try
        {
            MimeMessage msg = new MimeMessage(sess);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO,
                    "adresse@destinataire.net");
            msg.setSubject("merci de votre inscription");
            msg.setSentDate(new Date());
            msg.setText("Corps du message!\n");
            Transport.send(msg);
        }
        catch (MessagingException mex)
        {
            System.out.println("send failed, exception: " + mex);
        }
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("Message envoyé");
        out.close();
    }
}
