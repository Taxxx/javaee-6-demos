# JAX-WS web service

In this demo, we will learn how to create a web service based in the 
JAX-WS standard, included in the Java EE 6 specification. This web service will
be a SOAP service, that means the client and server exchange data in XML format.
The transport protocol will be HTTP, because it is the most common, but other
protocols are also valid for web services.

# Instructions

## Chose a JAX-WS implementation framework

Creating a web service could seem pretty straight forward, according to 
[Oracle documentation](http://docs.oracle.com/javaee/6/tutorial/doc/bnayl.html)
about the subject. But, there is a pitfall. *Implementation*.
Java EE 6 specification _specifies_, but it doesn't provide an implementation.

We will chose [Apache CXF](http://cxf.apache.org) as our implementation, but
there are others, like [Spring WS](http://projects.spring.io/spring-ws/)
(we may try that one some day).

## Setting up project dependencies

The demo will depend on:

- `javax.servlet-api`: the Java EE API for servlets, because it will be a servlet
who will manage HTTP requests. The web service will rely on this servlet.
- `cxf-rt-frontend-jaxws`: the implementation of JAX-WS of CXF.
- `cxf-rt-transports-http`: the protocol used for communications.
- `spring-web`: CXF uses Spring internally.

## Write the service interface

Our web service will be used to store the names of our users, and retrieve that
name based on an `id` return by the web service itselt.

We will need two methods: 

- `addUser`: to add a user to our system.
- `getUser`: to retrieve a user name.

Our interface will look something like this:

	import javax.jws.WebService;
	import javax.jws.WebParam;

	@WebService
	public interface UsersManagement {
		public String getUser(@WebParam(name="userId") int userId);
		public int addUser(@WebParam(name="name") String name);
	}

## Write service implementation

Now it's the time to implement our service. The implementation needs to be
annotated with `@WebService` as well, and we should provide some parameters
to that annotation. The most important one is `endpointInterface` that must
point to our service interface.

The web service implementation should look like this:

	import javax.jws.WebService;

	@WebService(endpointInterface = "es.rchavarria.ws.UsersManagement",
	            serviceName = "Users")
	public class UsersManagementImpl implements UsersManagement {
		 // ... methods implementation
	}

Look into the code to see how methods are implemented.

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
