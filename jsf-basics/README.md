# JavaServer Faces, the basics

In this demo, we will write a very simple application to learn how
JavaServer Faces technology works.

# Instructions

1. start with a maven webapp archetype
2. add dependencies: junit, slf4j, myfaces(api, impl)
3. configure FacesServlet in web.xml file, adding a listener to start MyFaces initialization
4. create a managed bean @ManagedBean(name = "welcome", eager = true)
5. create a view template login.xhtml
	it has a login form and transfer control to success.xhtml
6. create a new managed bean to read parameters from request
7. create a view template success.xhtml
	it shows the params returned by the seconde managed bean
6. run and visit /login.jsf
7. what else?

# Run


# Further reading

- [Mojarra project](https://javaserverfaces.java.net/): 
the reference implementation of the JSF specification.
- [JSF on Wikipedia](https://en.wikipedia.org/wiki/JavaServer_Faces):
Wikipedia entry for JavaServer Faces.
- [Facelets](https://en.wikipedia.org/wiki/Facelets):
Facelets is the default templating system for JSF, instead of JSP's pages
- [MyFaces](https://myfaces.apache.org/):
an implementation developed by Apache.
- []():

