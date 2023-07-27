package com.pluralsight.javaproject.cli.service;

//creating this record to ensure that we can select from the given JSON file
//which properties are actually of relevance and we want to include

public record PluralsightCourse(String id, String title,String duration,String contentUrl,boolean isRetired) {


}
