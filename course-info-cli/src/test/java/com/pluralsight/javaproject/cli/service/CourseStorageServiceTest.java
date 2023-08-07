package com.pluralsight.javaproject.cli.service;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void storePluralsightCourses() {
        CourseRepository repository= new InMemoryCourseRepository();

        courseStorageService courseStorageService= new CourseStorageService(repository);

        PluralsightCourse ps1= new PluralsightCourse("1","Title 1","01:40:00.123","/url-1",false);

    }

   //creating a fake implementation of the CourseRepository

   static class InMemoryCourseRepository implements CourseRepository{

        private final List<Course> courses = new ArrayList<>();

        @Override
       public void saveCourse(Course course){
            courses.add(course);
        }

        @Override
       public List<Course> getAllCourses(){
            return courses;
        }


   }
}