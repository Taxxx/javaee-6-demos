# Request Parameters Servlet (JSP)

In this demo, we will extend our previous demo
[request parameters](../request-parameters). In this demo, instead of generate
plain text to display data stored in request parameters, we will use a JSP page,
that will render that data in an HTML and pretty format.

# Instructions

# **TO BE COMPLETED**

The servlet will read request parameters and will build a list of objects with
the data that just has been read. Then, data will be stored in a request
attribute and the request will be forwared to a JSP page.

The JSP page will read the attribute, and will format the data in an HTML and
pretty format.

## Dependencies

In this case, we have the same dependencies as in our previous demo: Java EE and
Jetty maven plugin.

## Writing the servlet

# **TO BE COMPLETED**

The servlet will build a list of objects. Each object will store information about
the name and value of a request parameter. The code will look similar to this, 
please, look into the source code to see the final version that really works:

    private List<Parameter> buildParamList(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        List<Parameter> result = new LinkedList<Parameter>();
        while(names.hasMoreElements()) {
            result.add(new Parameter(key, value));
        }
        
        return result;
    }

Then, the servlet just forward the request to a JSP page, to render the data in
an HTML page.

    // [...]
    List<Parameter> params = buildParamList(request);
    request.setAttribute("params", params);
    request.setAttribute("check", "true");
    
    // forward to the JSP page
    request.getRequestDispatcher("/params.jsp").forward(request, response);
    // [...]

# Run

To run this example, just type `mvn jetty:run`, or even simpler, `mvn` (the default
maven goal is configured as `jetty:run` for your convenience).

