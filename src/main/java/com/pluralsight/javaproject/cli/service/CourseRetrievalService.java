package com.pluralsight.javaproject.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


// about the http web client API
//Http Client[started on day3] :
// An HttpClient can be used to send requests and retrieve their responses.
// An HttpClient is created through a builder.
// The newBuilder method returns a builder that creates instances of the default HttpClient implementation.
// A BodyHandler must be supplied for each HttpRequest sent.
// The BodyHandler determines how to handle the response provided.
// -------->send(HttpRequest, BodyHandler) blocks until the request has been sent and the response has been received.
// -------->sendAsync(HttpRequest, BodyHandler) sends the request and receives the response asynchronously
//it requires dependencies slf4j-api and slf4j-simple
//need to ensure dependencies fasterxml.jackson are not showing an error
//need to update the ReadME also after this
//this is the course retrieval service
//we also use the logging feature in this
//PS_URI signifies the url for the pluralsight courses API
//this way we can access the courses directly from there


//converting JSON code to list form
//for easier readibility
//resolved jackson dependencies issue

public class CourseRetrievalService {
   //for the http web client API
   //pass in authorid to get courses by that author
    //return type: JSON as string

    //defining string constants carrying location of the API that we have to call
    private static final String PS_URI="https://app.pluralsight.com/profile/data/author/%s/all-content";

    //http client instance
    private static final HttpClient CLIENT=HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();
//            .newHttpClient();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public List<PluralsightCourse> getCoursesFor(String authorId){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();
       try{
           HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
           return switch(response.statusCode()) {
               case 200 -> toPluralsightCourses(response);
               case 404 -> List.of();
               default -> throw new RuntimeException("pluralsight API call failed with status code " + response.statusCode());
           };
       }catch(IOException | InterruptedException e){
           throw new RuntimeException("could not call the pluralsight API",e);

        }
    }

    private static List<PluralsightCourse> toPluralsightCourses(HttpResponse<String> response) throws JsonProcessingException {
        JavaType returnType =OBJECT_MAPPER.getTypeFactory()
                        .constructCollectionType(List.class, PluralsightCourse.class);
        return OBJECT_MAPPER.readValue(response.body(), returnType);
    }
    //due to arrow syntax we dont have to add return syntax for the switch case
}
