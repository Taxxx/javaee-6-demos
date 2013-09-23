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

3. create a junit test
4. invoke the web service

# Run

This demo run as a JUnit test, so one can see the results by running the maven
command `mvn test`.

# Further reading

- [Building web services with JAX-WS](http://docs.oracle.com/javaee/6/tutorial/doc/bnayl.html)
- [Building RESTful Web Services with JAX-RS](http://docs.oracle.com/javaee/6/tutorial/doc/giepu.html)
