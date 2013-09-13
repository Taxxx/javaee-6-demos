# Request Parameters Servlet

In this example, we will create a HTTP servlet able to read request parameters.
The servlet will be based in our previous 
[simple HTTP servlet](../simple-http-servlet).

# Instructions

We aren't using any JSP page to display the result to the user, so, the servlet
will write simple plain text to show that it is working, that's all.

Our servlet will read the parameters in the HTTP request and it will display
their names and their values.

## Dependencies

In this case, we have the same dependencies as in our previous demo: Java EE and
Jetty maven plugin.

## Writing the servlet

The servlet will be based in our previous servlet, so it will be an HTTP servlet,
so it will inherit from `javax.servlet.http.HttpServlet`. We will configure it
using the annotation `@WebServlet` as in our previous demo. 

We will read request parameters using two methods in `HttpServletRequest` interface:
`Enumeration<String> getParameterNames()` and `String getParameter(String name)`. 
The code that reads the request parameters and generates HTML code to show them is:

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

# Run

To run this example, just type `mvn jetty:run`, or even simpler, `mvn` (the default
maven goal is configured as `jetty:run` for your convenience).

