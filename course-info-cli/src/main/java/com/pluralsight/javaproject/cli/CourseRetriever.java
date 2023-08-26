package com.pluralsight.javaproject.cli;

import com.pluralsight.courseinfo.repository.CourseRepository;
import com.pluralsight.courseinfo.repository.RepositoryException;
import com.pluralsight.javaproject.cli.service.CourseRetrievalService;
import com.pluralsight.javaproject.cli.service.CourseStorageService;
import com.pluralsight.javaproject.cli.service.PluralsightCourse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

import static java.util.function.Predicate.not;

//import java.util.logging.Logger;

public class CourseRetriever {
    private static final Logger LOG=  LoggerFactory.getLogger(CourseRetriever.class);
    //to find or create a logger with name defined as the parameter. it returns a suitable logger
    //The LoggerFactory is a utility class producing Loggers for various logging APIs.
    //This method accepts a string value representing a name and returns a Logger object with the specified name

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
       }catch(Exception e){
//           System.out.println("an unexpected error occurs over here");
           LOG.error("unexcpected error occurs",e);
//           e.printStackTrace();
       } catch (RepositoryException e) {
           throw new RuntimeException(e);
       }
   }
    // calling our pluralsightcourse record over here instead
//           PluralsightCourse course= new PluralsightCourse("id","title","00:54:57","https://url",false);
//           //once record is established it is immutable and values cannot be changed
//           LOG.info("course :{} ",course);

    private static void retrieveCourses(String authorId) throws RepositoryException {
       System.out.println("Retrieving courses for author "+ authorId);
       LOG.info("retrieving courses using the logging interface for the author "+ authorId);
       CourseRetrievalService courseRetrievalService =  new CourseRetrievalService();

        CourseRepository courseRepository =CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);


       //The List interface is found in java.util package and inherits the Collection interface

       //filtering the already retired courses
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream()
//                .filter(course -> !course.isRetired())
                .filter( not(PluralsightCourse::isRetired))
                .toList();

       //calling the method
//        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId);
        LOG.info("retrieved the following {} courses {}",coursesToStore.size(),coursesToStore);
        courseStorageService.storePluralsightCourses(coursesToStore);
        LOG.info("Courses are successfully stored");
    }


    //after introducing spf4j-api dependancy
    //setting up a logger

    //working with the http webclient API
    //creating class courseretrieval service

    //H2 is a database that is an embedded SQL databse in java meaning that we dont have to install a seperate database to use that
    //can introduce H2 as a library in our application and it can run alongside the application in the same process
    //H2 writes all data to disk in teh file
    //acts as a normal SQL database server
    //H2 also has a mode where other applications can connect to this embedded databse running in our application
    //called as the auto server mode
    //JDBC is part of standardized library and can connect to any SQL database in a standardized manner as long as teh database has a JDBC driver
    //JDBC= java databse connectivity; a standardized java API to connect to any SQL databases
    //hibernate offers object relational mapping




}
