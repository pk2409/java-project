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

//the repository pattern allows us to hide the implementation details
//so that consumers of the repo API can write clean code
//the API of the repository can be expressed completely in terms of the domain objects
//can also change the implementation without affecting consumers of repo API
//intro of API makes the system more testable
//JDBC: for relational database connectivity API
//the H2 database provides a JDBC driver as do almost all databases
//JDBC is used for interaction with databases
//hibernate library layers on top of the JDBC
//it has features like object relational mapping out of teh box
//working on the JdbcDataSource importation
//updating the PluralsightCourse.java file
//------->MODULE 4<----------
//will check for errors caused by this