package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourse(Course course) throws RepositoryException;

    List<Course> getAllCourses() throws RepositoryException;

    void addNotes(String id,String notes) throws RepositoryException;

    static CourseRepository openCourseRepository(String databaseFile){

        return new CourseJdbcRepository(databaseFile);
    }
}

//the interface is completely expressed in terms of our course domain project
