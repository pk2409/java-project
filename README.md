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
