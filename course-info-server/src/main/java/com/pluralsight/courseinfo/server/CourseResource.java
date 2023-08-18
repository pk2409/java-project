package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;
import com.pluralsight.courseinfo.repository.RepositoryException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG= LoggerFactory.getLogger(CourseResource.class);

   private final CourseRepository courseRepository;

   public CourseResource(CourseRepository courseRepository){
       this.courseRepository=courseRepository;
   }




   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Course> getCourses(){
       try {
           return courseRepository
                   .getAllCourses()
                   .stream()
                   .sorted(Comparator.comparing(Course::id))
                   .toList();
       }catch(RepositoryException e){
           LOG.error("could not retrieve courses from the database",e);
           throw new NotFoundException();
       }
   }

   //beacuse we want user to use Http POST to reach our function
    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
   public void addNotes(@PathParam("id") String id,String notes){
       courseRepository.addNotes(id,notes);


   }
   //this endpoint will act the opposite to the endpoint we already had
    //so it does not return any information, it will have a void return type
    //the notes parameter is filled with the body of the Http POST request that we receive here
    //these annotations are all required to steer the jersey implementation into the right direction
    //cannot use the browser like we used for the GET request
    //we use a command line tool called curl
    //which can be used to craft Http requests from the command line



//have to sort the error
    //repository exception is providing error and not letting
    //the application run
    //it has not been caught or declared
    //error in the CourseResource.java

    //adding notes into the course
    //we are going to expose an endpoint in the course
    //and endpoint into a particular course/{id}/notes
    //where the body of teh POST request contains the notes
    //through such a request, the notes will be added to the course with teh particular id
    //we need to extend our course-info-repository implementation
    //with a method that can add notes to a particular course
    //through an SQL update statement
    //also need to update our course domain class so that it can contain the notes
    //used to update the repository to store course notes
    //need to solve the error due to which teh  JSON file is not visible


    //The @Produces annotation is used to specify the MIME media types or representations
    // a resource can produce and send back to the client
    //@path is used for An object that may be used to locate a file in a file system.
    // It will typically represent a system dependent file path
    // java.util.Comparator is A comparison function, which imposes a total ordering on some collection of objects.







}
