package es.rchavarria.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = {"/RequestParametersServlet"}) 
public class RequestParametersServletDemo extends HttpServlet {
 
	private static final long serialVersionUID = 5407847482550527510L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Enumeration<String> names = request.getParameterNames();

        PrintWriter out = response.getWriter();
        try{
        	String yes = names.hasMoreElements() ? "will" : "won't";
            out.println("<h2>");
            out.println("This servlet " + yes + " read your form parameters");
            out.println("</h2>");
            
            out.println("<ul>");
            while(names.hasMoreElements()) {
            	String name = names.nextElement();
            	String value = request.getParameter(name);
            	
            	out.println("<li>");
                out.println(name + ": " + value);
                out.println("</li>");
            }
            out.println("</ul>");
            
        } finally {
            out.close();
        }
    }
}
