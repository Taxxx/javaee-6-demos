# JAX-WS web service

In this demo, we will learn how to create a web service based in the 
JAX-WS standard, included in the Java EE 6 specification. This web service will
be a SOAP service, that means the client and server exchange data in XML format.
The transport protocol will be HTTP, because it is the most common, but other
protocols are also valid for web services.

# Instructions

0. chose a JAX-WS implementation framework: apache cxf (uses spring)
1. setting up pom.xml dependencies
2. write service interface @WebService
3. write service implementation
4. set up a servlet that manages http request (cxf servlet) -> web.xml
5. configure the cxf servlet -> services.xml (default cxf-servlet.xml)
6. run jetty, a web container, that runs the servlet. Visit 
(http://localhost:8080/HelloWorld?wsdl) to see the WSDL file defining the web service

# Run

To run this example, just type `mvn jetty:run`, or even simpler, `mvn` (the default
maven goal is configured as `jetty:run` for your convenience).

# Further reading

- [Building web services with JAX-WS](http://docs.oracle.com/javaee/6/tutorial/doc/bnayl.html)
- [Building RESTful Web Services with JAX-RS](http://docs.oracle.com/javaee/6/tutorial/doc/giepu.html)
- [Apache CXF user's guide](http://cxf.apache.org/docs/index.html)
