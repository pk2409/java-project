package com.pluralsight.javaproject.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            01:08:54.6789, 68
            00:05:37, 5
            00:00:00.0, 0
            """)
    //it will convert 68,5 and0 to long type for the expected value that we pass
    //into our method

    //CsvSource is an ArgumentsSource which reads comma-separated values (CSV) from one or more CSV records
        // supplied via the value() attribute or textBlock() attribute. The supplied values will be
        // provided as arguments to the annotated @ParameterizedTest method.


    void durationInMinutes(String input,long expected) {
        PluralsightCourse course =
                new PluralsightCourse("id","Test Course",input,"url",false);
        assertEquals(expected,course.durationInMinutes());
    }

//    @Test
//    void durationInMinutesOverHour() {
//        PluralsightCourse course =
//                new PluralsightCourse("id","Test Course","01:05:37","url",false);
//        assertEquals(65,course.durationInMinutes());
//    }
//
//    @Test
//    void durationInMinutesZero() {
//        PluralsightCourse course =
//                new PluralsightCourse("id","Test Course","00:00:00","url",false);
//        assertEquals(0,course.durationInMinutes());
//    }
}