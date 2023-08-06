package com.pluralsight.javaproject.cli.service;

import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.repository.CourseRepository;

import java.util.List;

public class CourseStorageService {
    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository courseRepository){
        this.courseRepository=courseRepository;

    }

    public void storePluralsightCourses(List<PluralsightCourse> psCourses){
        for(PluralsightCourse psCourse){
            Course course = new Course(psCourse.id(),
                    psCourse.title(), psCourses.durationInMinutes(),
            PS_BASE_URL + psCourses.contentUrl());
            courseRepository.saveCourse(course);
        }
    }
}
