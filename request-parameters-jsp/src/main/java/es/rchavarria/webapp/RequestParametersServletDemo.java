package es.rchavarria.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

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
		List<Parameter> params = buildParamList(request);
		request.setAttribute("params", params);
		
		// forward to the JSP page
		request.getRequestDispatcher("/params.jsp").forward(request, response);
    }

	private List<Parameter> buildParamList(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
		if(!names.hasMoreElements()) return Collections.emptyList();

		List<Parameter> result = new LinkedList<Parameter>();
        while(names.hasMoreElements()) {
        	String name = names.nextElement();
        	String value = request.getParameter(name);
        	
        	Parameter p = new Parameter();
        	p.setKey(name);
        	p.setValue(value);
        	result.add(p);
        }
		
		return result;
	}

}
