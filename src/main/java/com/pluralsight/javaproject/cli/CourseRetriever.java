package com.pluralsight.javaproject.cli;

import com.pluralsight.javaproject.cli.service.CourseRetrievalService;
import com.pluralsight.javaproject.cli.service.PluralsightCourse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

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
           // calling our pluralsightcourse record over here instead
//           PluralsightCourse course= new PluralsightCourse("id","title","00:54:57","https://url",false);
//           //once record is established it is immutable and values cannot be changed
//           LOG.info("course :{} ",course);
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
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId);
        LOG.info("retrieved the following {} courses {}",coursesToStore.size(),coursesToStore);
    }


    //after introducing spf4j-api dependancy
    //setting up a logger

    //working with the http webclient API
    //creating class courseretrieval service



}
