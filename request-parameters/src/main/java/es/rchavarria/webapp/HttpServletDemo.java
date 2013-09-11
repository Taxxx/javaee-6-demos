package es.rchavarria.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = {"/SimpleHttpServlet"}) 
public class HttpServletDemo extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        try{
            out.println("<h2>");
            out.println("This servlet has been configured simply by: ");
            out.println("@WebServlet(urlPatterns = {\"/SimpleHttpServlet\"})");
            out.println("</h2>");
        } finally {
            out.close();
        }
    }
}
