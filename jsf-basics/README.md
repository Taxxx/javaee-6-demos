# JavaServer Faces, the basics

In this demo, we will write a very simple application to learn how
JavaServer Faces technology works.

It will be similar to other demos. We will have a wellcome page, with a form
where the user can enter an email and a password. When the submit button is
pressed, we will navigate to a new page showing the request params and its
values.

# Instructions

1. Start with a maven webapp archetype

The fastest way to start is using a maven archetype. So, why not start using
the maven webapp archetype by default? Type the following command:

	mvn archetype:generate -DgroupId=<group-id> -DartifactId=<app-id> -DarchetypeArtifactId=maven-archetype-webapp

Where `group-id` is your group name, for example, your company name followed by 
your project name, such as `es.rchavarria.jsf`. The `app-id` is the name of
your new application. We will use `jsf-basics`.

This command will create the skeleton of a Java web application, including an
index `.jsp` page and a `web.xml` file, describing the web application. We will
modify these files and others to complete our demo.

2. Add dependencies

Now it's the time to add our needed dependencies, let's start:

- `myfaces-api`: The public API classes of the Apache MyFaces CORE project.
- `myfaces-impl`: The private implementation classes of the Apache MyFaces Core 
Implementation
- `tomahawk20`: JSF components and utilities to use in a JSF 2.x implementation.
- `javax-servlet`: We will access to http request objects through the servlet
implementation.
- `jetty`: Include jetty as our server for development.

3. Configure FacesServlet in webapp descriptor file.

We need to configure a servlet, the FacesServlet, to handle all JSF invocations.
To do so, we need to modify the `web.xml` file:

    <servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.jsf</url-pattern>
    </servlet-mapping>

Basically, it means: our servlet `Faces Servlet` will handle all requests 
ending in `.jsf`.

Most tutorials don't go further, but this configuration doesn't work. We need to
setup a servlet listener, to start MyFaces initialization. So, be sure to
include the following config:

	<listener>
		<listener-class>org.apache.myfaces.webapp.StartupServletContextListener</listener-class>
	</listener>

This is a demo, and we are under a development stage, so I would recomend to use
this context param for our servlet (see a list of lots of context params documented
in the [Apache MyFaces project](https://myfaces.apache.org/core21/myfaces-impl/webconfig.html)). 

    <context-param>
        <param-name>javax.faces.PROJECT_STAGE</param-name>
        <param-value>Development</param-value>
    </context-param>

4. Create a managed bean called `login`

We will create a simple managed bean, and it will allow us to access it in our
JSF view, our first page that we will see later.

It is fairly simple, just use the annotation `@ManagedBean` and you'll get it.
We can use it, for example, to return a title for our future login form. Let's
take a look to the code:

	import javax.faces.bean.ManagedBean;

	@ManagedBean(name = "login", eager = true)
	public class GreetingBean {
		public String getMessage() {
			return "Login user";
		}
	}

We will learn how to acces this managed bean in a `.jsf` page in the next step.

5. create a view template login.xhtml
	it has a login form and transfer control to success.xhtml
6. create a new managed bean to read parameters from request
7. create a view template success.xhtml
	it shows the params returned by the seconde managed bean
6. run and visit /login.jsf
7. what else?

# Run

Run the maven command `mvn jetty:run` or just `mvn` to start the server. Then,
visit [http://localhost:8080/login.jsf] and play!.

# Further reading

- [Mojarra project](https://javaserverfaces.java.net/): 
the reference implementation of the JSF specification.
- [JSF on Wikipedia](https://en.wikipedia.org/wiki/JavaServer_Faces):
Wikipedia entry for JavaServer Faces.
- [Facelets](https://en.wikipedia.org/wiki/Facelets):
Facelets is the default templating system for JSF, instead of JSP's pages
- [MyFaces](https://myfaces.apache.org/):
an implementation developed by Apache.
- [Kinds of managed beans](http://java.dzone.com/articles/making-distinctions-between):
a comparison table of several kinds of managed beans.
- []():

