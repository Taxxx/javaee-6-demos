# Simple HTTP Servlet

In this example, we will create a simple HTTP servlet, without a web descriptor, just to
show how Java EE 6 annotations can save developers some boiler plate XML code.

# Instructions

The servlet will reply as simple as possible to client requests. It will just write some
plain text in the response, that's all. You can change the text to check how this demo
works.

## Dependencies

First of all, we will add the dependencies to be able to develop a Java EE Servlet into
the `pom.xml` file:

    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

We will also use a maven plugin to run the demo. Instead of installing an application server,
the Jetty maven plugin will serve us well. So, we will add that plugin to our `pom.xml` 
file:

	<plugin>
	    <groupId>org.mortbay.jetty</groupId>
	    <artifactId>jetty-maven-plugin</artifactId>
	    <version>${jetty.version}</version>
	    <configuration>
	        <webApp>
	            <contextPath>/</contextPath>
	        </webApp>
	    </configuration>
	</plugin>

## Writing the servlet

Our servlet will be a HTTP servlet, so, we have to extend `javax.servlet.http.HttpServlet`:

	public class HttpServletDemo extends HttpServlet {
		//...
	}

To keep the servlet as simple as possible, we will reply with a simple response, just a few
words in plain text.

## Configuring the servlet

Java EE 6 allows us to configure the servlet without using a web descriptor, just by using
annotations. The `@WebServlet` annotation do all the work for us. As easy as a pie:

	@WebServlet(urlPatterns = {"/SimpleHttpServlet"})

The `urlPatterns` annotation attribute allows us to configure the URL where the 
servlet will reply to client requests.

# Run

To run this example, just type `mvn jetty:run`, or even simpler, `mvn` (the default
maven goal is configured as `jetty:run` for your convenience).

