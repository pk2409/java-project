package com.pluralsight.javaproject.cli.service;

//creating this record to ensure that we can select from the given JSON file
//which properties are actually of relevance and we want to include

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Duration;
import java.time.LocalTime;

//which properties to be ignored by json
@JsonIgnoreProperties(ignoreUnknown = true)

public record PluralsightCourse(String id, String title,String duration,String contentUrl,boolean isRetired) {

    //to write the duration as a number rather than a string

    public long durationInMinutes(){

         return Duration.between(
            LocalTime.MIN,
        LocalTime.parse(duration())).toMinutes();
        }

//        LocalTime.parse(duration());

}
