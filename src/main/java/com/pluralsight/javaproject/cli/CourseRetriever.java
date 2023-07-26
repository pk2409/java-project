package com.pluralsight.javaproject.cli;

import com.pluralsight.javaproject.cli.service.CourseRetrievalService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//import java.util.logging.Logger;

public class CourseRetriever {
    private static final Logger LOG=  LoggerFactory.getLogger(CourseRetriever.class);

   public static void main( String args[]){
       System.out.println("providing the course info over here");
       LOG.info("CourseRetriever started using teh logging interface");
       if (args.length==0){
           System.out.println("Please provide an author name as first argument");
           LOG.warn("need to provide an author name using the logging interface here");
           return;
       }
       try{
           retrieveCourses(args[0]);
       } catch(Exception e){
//           System.out.println("an unexpected error occurs over here");
           LOG.error("unexcpected error occurs",e);
//           e.printStackTrace();
       }
   }

    private static void retrieveCourses(String authorId) {
       System.out.println("Retrieving courses for author "+ authorId);
       LOG.info("retrieving courses using the logging interface for the author "+ authorId);
       CourseRetrievalService courseRetrievalService =  new CourseRetrievalService();

       //calling the method
        String coursesToStore = courseRetrievalService.getCoursesFor(authorId);
        LOG.info("retrieved the required courses {}",coursesToStore);
    }


    //after introducing spf4j-api dependancy
    //setting up a logger

    //working with the http webclient API
    //creating class courseretrieval service



}
