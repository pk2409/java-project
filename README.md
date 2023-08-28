# java-project
initial commit

in this project i plan to use and learn the following
->building a maven based project
->calling external web API
->storing data in a database
->creating a REST API

NOTES OF WHAT I LEARNT ABOUT
->Http Client[started on day3] :
An HttpClient can be used to send requests and retrieve their responses. An HttpClient is created through a builder. The newBuilder method returns a 
builder that creates instances of the default HttpClient implementation.A BodyHandler must be supplied for each HttpRequest sent. The BodyHandler determines how to 
handle the response provided.
-------->send(HttpRequest, BodyHandler) blocks until the request has been sent and the response has been received.
-------->sendAsync(HttpRequest, BodyHandler) sends the request and receives the response asynchronously


//it comes from the jul-to-slf4j  maven dependency. this bridge handler takes care of installing some hooks
        //into the JDK Logging API that were redirected to SLF4J
        //this code is executed very early before any of teh other code is executed
        //all subsequent calls to the JDK API will be intercepted by the bridge handler
        //and redirected to slf4j, so that we have one unified logging approach in our application

        //in a real application we switch the simple logging backend with
        //full fledged implementation like Logback
        //in that case the slf4j setup will still work
        //all log statements will end up in the new Logback implementation

        //external configuration for a java application
        //externalising means to move configuration data out of code
        //we can use configuration files that can use configuration properties for the application
        //we can try to externalise the name of the database required


//ship the course-info server as a single self-contained JAR file
    //UNIFIED LOGGING
    //currently, course-info server has two different logging formats
    //because we are using SLF4J as a single frontend to our logging
    //all of the statements that we log , will eb logged by the simple logging backend that we use
    //also using external libraries such as jersey for our rest API
    //jersey uses JDK logging for its internal logging
    //SLF4J logging is formatted in a single line
    //whereas the JDK logging is spread over two lines
    //it is a different logging API that uses different formatting
    //many of the tools won't do partial log lines
    //it is required to have a single format
    //we can introduce a bridge library which will redirect the JDK logging calls
    //to the SLF4J library, anything used for JDK logging will be used for
    //the slf4j logging backends that are provided
    //JDK logging will still be there, but will not be used anymore in the code
    //need to introduce jul which is java.util.logging to slf4j library
