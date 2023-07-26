package com.pluralsight.javaproject.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrievalService {
   //for the http web client API
   //pass in authorid to get courses by that author
    //return type: JSON as string

    //defining string constants carrying location of the API that we have to call
    private static final String PS_URI="https://app.pluralsight.com/profile/data/author/%s/all-content";

    //http client instance
    private static final HttpClient CLIENT=HttpClient.newHttpClient();

    public String getCoursesFor(String authorId){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();
       try{
           HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
           return response.body();
       }catch(IOException | InterruptedException e){
           throw new RuntimeException("could not call the pluralsight API",e);

        }
    }
}
