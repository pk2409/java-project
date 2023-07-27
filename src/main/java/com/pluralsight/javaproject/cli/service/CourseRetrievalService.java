package com.pluralsight.javaproject.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

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

    public List<PluralsightCourse> getCoursesFor(String authorId){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();
       try{
           HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
           return switch(response.statusCode()) {
               case 200 -> null;
               case 404 -> List.of();
               default -> throw new RuntimeException("pluralsight API call failed with status code " + response.statusCode());
           };
       }catch(IOException | InterruptedException e){
           throw new RuntimeException("could not call the pluralsight API",e);

        }
    }
        //due to arrow syntax we dont have to add return syntax for the switch case
}
