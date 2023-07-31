package com.pluralsight.javaproject.cli.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @Test
    void durationInMinutes() {
        PluralsightCourse course =
                new PluralsightCourse("id","Test Course","00:05:37","url",false);
        assertEquals(5,course.durationInMinutes());
    }

    @Test
    void durationInMinutesOverHour() {
        PluralsightCourse course =
                new PluralsightCourse("id","Test Course","01:05:37","url",false);
        assertEquals(65,course.durationInMinutes());
    }

    @Test
    void durationInMinutesZero() {
        PluralsightCourse course =
                new PluralsightCourse("id","Test Course","00:00:00","url",false);
        assertEquals(0,course.durationInMinutes());
    }
}