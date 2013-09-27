# JAX-WS web service client

In this demo, we will learn how to create a web service client that 
consumes our web service developed and deployed in a previous demo.

# Instructions


## Start a web service

First, we need to start our web service, in order to be able to read the WSDL
file that describes it. Here, we will start the web service we developed
in our previous demo, [ws-jaxws-cxf](../ws-jaxws-cxf). If you have your 
own web service, start it as you usually do.

Start the web service:

    cd ws-jaxws-cxf
    mvn jetty:run

Now, we have the WSDL file available at `http://localhost:8080/Users?wsdl`

## Generate service and port classes with wsimport

Java provides a tool, `wsimport`, that generates the needed classes to consume
a web service. 

The basic use of the command would be:

    wsimport [options] <WSDL_URI>

In our case:
    
    wsimport http://localhost:8080/Users?wsdl

This will generate classes, compile them and delete the source code, leaving
`.class` files only. We will use the following parameters to get a desired 
result from `wsimport`:

- `-d src/main/java`: to generate files in that directory.
- `-keep`: to keep generated `.java` files.
- `-Xnocompile`: not to compile generated files, so no `.class` files will be 
generated

The complete command is: 

    wsimport -d src/main/java -keep -Xnocompile http://localhost:8080/Users?wsdl

The main generated classes are: 

- `UsersManagementService`: a class representing the web service.
- `UsersManagement`: a class with the same methods of the web service, acting 
like a proxy to our web service. This is the `port` class, and it allow us to
invoke the methods exposed by the web service.

## Create a JUnit test to invoke the web service 

We will use a simple JUnit test to invoke our web service. 

First of all, we need to add the JUnit dependency to our maven project:

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.8.1</version>
        <scope>test</scope>
    </dependency>

We will create the test in the `es.rchavarria.ws.client` package. In the `setUp`
method of our test, we will create an instance of the service, and the we
get the _port_ class. This class will allow us to invoke the web service:

    //...
    public class JaxWsClientTest {

        private UsersManagement port;

        @Before
        public void setUp() {
            UsersManagementService service = new UsersManagementService();
            port = service.getUsersManagementPort();
        }
    
    //...        
    }

## Invoke the web service

Now, we have the _port_ class, so we are able to invoke methods exposed by our
web service: `addUser` and `getUser`. Time to test them!!

    @Test
    public void test() {
        assertEquals(1, port.addUser("The boss"));
        assertEquals(2, port.addUser("The king"));
        assertEquals(3, port.addUser("The queen"));
        
        assertEquals("The boss", port.getUser(1));
        assertEquals("The queen", port.getUser(3));
        assertEquals("The king", port.getUser(2));
    }

# Run

This demo run as a JUnit test, so one can see the results by running the maven
command `mvn test`. 

I added the maven surefire report plugin as well, so, running the command `mvn site`,
maven will generate a report with the results of all executed tests. Run the command
and open the HTML file in `target/site/index.html`.

# Further reading

- [Building web services with JAX-WS](http://docs.oracle.com/javaee/6/tutorial/doc/bnayl.html)
- [Building RESTful Web Services with JAX-RS](http://docs.oracle.com/javaee/6/tutorial/doc/giepu.html)
