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
        	out.println(outputHeading(names.hasMoreElements()));
            out.println(outputParametersList(request));
        } finally {
            out.close();
        }
    }

	private String outputHeading(boolean thereAreParameters) {
    	String yes = thereAreParameters ? "will" : "won't";
    	return  "<h2>" + 
    			"This servlet " + yes + " read your form parameters" +
    			"</h2>";
	}

	private String outputParametersList(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
		if(!names.hasMoreElements()) return "";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<ul>");
        while(names.hasMoreElements()) {
        	String name = names.nextElement();
        	String value = request.getParameter(name);
        	
        	sb.append("<li>");
            sb.append(name + ": " + value);
            sb.append("</li>");
        }
        sb.append("</ul>");
		
		return sb.toString();
	}
	
}
