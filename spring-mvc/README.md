# Spring MVC

In this demo, we will create an application to show the use 
of the Spring MVC framework for creating RESTful services.

It will be a simple web application, without persistence, but a
complete Spring MVC application where, instead of render responses
with a JSP page, the response will be in JSON format, to be
consumed as a Web Service.

# Instructions

The application will be a typical CRUD one, and it will manage a 
list of houses, flats, cottages, ... These kind of properties 
managed by a real state agency. So, our entity, our resource will 
be a `Property`.

## Define URI's to access properties

We will have only one resource, a property, so we will only have
one URI to access it:

http://<server name>/springmvc/properties

We will use different HTTP methods to manipulate properties:

- GET /properties: will return a list of properties
- GET /properties/{id}: will return details of the property identified by
{id}
- POST /properties: will create a new property
- DELETE /properties/{id]: will delete property identified by {id}
- PUT /properties/{id]: will update a property

# Create an empty MVC controller to reply to queries (list and get)

The controller that will reply to queries will be named:

	es.rchavarria.springmvc.rest.PropertiesQueriesController

It will reply to HTTP requests querying for data such a list of
properties or details of a property.

Annotate the controller so that Spring knows that the class is a 
controller and what URI's and HTTP methods it will handle. The result
will be something like this:

	@Controller
	@RequestMapping("/properties")
	public class PropertiesQueriesController {
	}

## Create a method to reply a list of properties

Our first method will be one that returns a list of available properties. The
list will be returned by a service. First, this service will manage properties
without persistence, but it will be replaced by a more sophisticated one.

The method will map the HTTP method GET and its returned value will be part
of the response body:

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<String> getAllProperties() {
		return Arrays.asList("one", "two", "three");
	}

## Create tests to exercise the query controller

The test will be an integration test. It will set up a web server, and
it will start our controller and it will perform real HTTP requests
against it.

We will use Mockito to mock external dependencies and a component provided
by Spring MVC, MockMVC. It will be the server and it will manage requests
and analyze responses.

The following code shows how to set up a stand alone server that configures
our controller

	// ...

    private MockMvc mockMvc;

    @InjectMocks
    PropertiesQueriesController controller;

    @Mock
    PropertyService propertyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = standaloneSetup(controller)
        		.setMessageConverters(new MappingJackson2HttpMessageConverter())
        		.build();
    }

	// ...

And the next code snippet shows how to test a simple HTTP GET request:

    @Test
    public void testRequestAllPropertiesUsesHttpOK() throws Exception {
        when(propertyService.requestAllProperties()).thenReturn(allProperties());

        mockMvc.perform(get("/properties")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

We will create another test, just to perform more expectations on the result:

	@Test
    public void testRequestAllPropertiesRendersOkAsJSON() throws Exception {
		when(propertyService.requestAllProperties()).thenReturn(allProperties());

        mockMvc.perform(get("/properties")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(jsonPath("$[0].city").value("first city"))
	        .andExpect(jsonPath("$[1].address").value("second address"))
	        .andExpect(jsonPath("$[2].price").value(300));
    }

## Add a method in the controller passing a parameter in the URI

We will use the URI http://<server>/springmvc/properties/<id> where <id> represents
a property id, and it will return the details of the specified property.

In the controller, we will annotate a method with @RequestMapping and two parameters:
method, it will be HTTP GET; and value, to give a name to the parameter. The annotation
@ResponseStatus tells the framework which HTTP status code should return and 
@ResponseBody indicates that the method's return value should be bound to the 
web response body.

This method will have a parameter, annotated with @PathVariable. This annotation
maps methods parameters to parameters in the URI.

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Property getProperty(@PathVariable String id) {
		return propertyService.findById(id);
	} 

We will create a test similar to the previous integration test, but I will omit it
in this post. Take a look at the code in
[github project](https://github.com/rchavarria/javaee-6-demos/tree/master/spring-mvc) 
if you are interested.

## Configure MVC controllers

We will use annotations to configure our MVC Controllers. Spring MVC provide a useful
annotation, @EnableWebMVC, and it does almost all work for us to configure a MVC
application.

Our configuration class will look like this:

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = { "es.rchavarria.springmvc.rest.controllers" })
	public class MVCConfig {}

We will create an integration test to test whether our configuration works or not.
Please, see integration test: `MVCConfigIntegrationTest`.

## Initialize web application

Again, we won't use any XML file to configure our application, we will configure
it programaticaly.

We will extend a class provided by Spring, `AbstractAnnotationConfigDispatcherServletInitializer`,
and we have to override some methods:
- `getRootConfigClasses`: it should return an array of classes that configures the root context.
So far, we don't have root context, so, it will return an empty array or null.
- `getServletConfigClasses`: it should return return an array of classes that configure the 
servlet context.
- `getServletMappings`: it should return the mappings for the servlet.

Now, we are ready to run our demo application in a servlet container. In this case, we 
will run the demo in a Tomcat 7 server. We will add the tomcat7 maven plugin and run it
with a command:

	<plugin>
	    <groupId>org.apache.tomcat.maven</groupId>
	    <artifactId>tomcat7-maven-plugin</artifactId>
	    <version>2.2</version>
	</plugin>

Run with the command: `mvn tomcat7:run`

## Next steps

This post is getting too longer to be published, and we have a lot of work to do, so I will
implement it in code and I will encourage you to visit the
[github project](https://github.com/rchavarria/javaee-6-demos/tree/master/spring-mvc) 
and read the 
code with the following tasks:

- Create a new controller, a command controller. That controller will allow the user to 
create, update and delete a property.
- Create three methods in the controller, one for each action (create, update, delete).
- Remember, each method should be tested with a unit or integration test.

# Resources

- [Build RESTful services with Spring](http://spring.io/guides/tutorials/rest)
- [Source code of this demo](https://github.com/rchavarria/javaee-6-demos/tree/master/spring-mvc)

