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
