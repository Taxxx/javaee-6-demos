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

# Set up a servlet that manages http request

Next step is to configure a servlet to manage HTTP requests. Apache CXF
provides this servlet, so, we have to configure our server to start it.
The servlet will manage all requests, because we set the URL pattern to
be like `/*`. 

It is essential to note that CXF servlet URL pattern must be able to handle
the URL to access the web service. That is, if the URL pattern is 
`/services/*` and web service URL is `/web-services/Users`, the servlet won't
be able to manage that HTTP requests.

Our servlet configuration is set in the web application descriptor file,
`web.xml`:

    <servlet>
        <servlet-name>the-cxf-servlet</servlet-name>
        <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>the-cxf-servlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

## Configure the cxf servlet 

CXF servlet will be started, but it doesn't now wich web services has to invoke.
We need to configure the servlet through an XML file, using CXF namespace
provided to configure them.

We can use a default file name, and place the configuration in `WEB-INF/cxf-servlet`,
or we can configure manually our servlet to tell him where is stored its configuration
file.

We modify our existing `web.xml` file:

    <servlet>
        <servlet-name>the-cxf-servlet</servlet-name>
        <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>

		<init-param>
			<param-name>config-location</param-name>
			<param-value>/WEB-INF/services.xml</param-value>   
		</init-param>        

        <load-on-startup>1</load-on-startup>
    </servlet>

And we add the CXF servlet configuration file `WEB-INF/services.xml`: 

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	      xmlns:jaxws="http://cxf.apache.org/jaxws"
	      xmlns:soap="http://cxf.apache.org/bindings/soap"
	      xsi:schemaLocation="
	http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	http://cxf.apache.org/bindings/soap http://cxf.apache.org/schemas/configuration/soap.xsd
	http://cxf.apache.org/jaxws
	http://cxf.apache.org/schemas/jaxws.xsd">

	  <jaxws:server id="aServer" serviceClass="es.rchavarria.ws.UsersManagement" address="/Users">
	  	<jaxws:serviceBean>
	  		<bean class="es.rchavarria.ws.UsersManagementImpl" />
	  	</jaxws:serviceBean>
	  </jaxws:server>
	</beans>

Note that this is a Spring configuration file. A new namespace is added, `jaxws`, which provide 
XML tags to configure the servlet.

## Ready

This demo is ready to run. Run it and visit 
[http://localhost:8080/Users?wsdl](http://localhost:8080/Users?wsdl) to 
see the WSDL file defining the web service.

In the next demo, we will create a client for our web service and we will check 
how it works.

# Run

To run this example, just type `mvn jetty:run`, or even simpler, `mvn` (the default
maven goal is configured as `jetty:run` for your convenience).

# Further reading

- [Building web services with JAX-WS](http://docs.oracle.com/javaee/6/tutorial/doc/bnayl.html)
- [Building RESTful Web Services with JAX-RS](http://docs.oracle.com/javaee/6/tutorial/doc/giepu.html)
- [Apache CXF user's guide](http://cxf.apache.org/docs/index.html)
