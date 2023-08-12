package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG= LoggerFactory.getLogger(CourseResource.class);

   private final CourseRepository courseRepository;

   public CourseResource(CourseRepository courseRepository){
       this.courseRepository=courseRepository;
   }




   @GET
   public String getCourses(){
       return courseRepository
               .getAllCourses()
               .stream()
               .map(Course::toString)
               .collect(Collectors.joining(", "));
   }


}
