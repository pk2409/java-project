package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourse(Course course);

    List<Course> getAllCourses() throws RepositoryException;
}

//the interface is completely expressed in terms of our course domain project