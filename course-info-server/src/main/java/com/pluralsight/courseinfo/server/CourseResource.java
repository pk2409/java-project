package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;
import com.pluralsight.courseinfo.repository.RepositoryException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
       return courseRepository
               .getAllCourses()
               .stream()
               .sorted(Comparator.comparing(Course::id))
               .toList();
   }
//have to sort the error
    //repository exception is providing error and not letting
    //the application run
    //it has not been caught or declared
    //error in the CourseResource.java

}
