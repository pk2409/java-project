package com.pluralsight.courseinfo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void rejectNullComponents(){
        assertThrowsExactly(IllegalArgumentException.class,() ->
                new Course(null,null,1,null));

    }

    @Test
    void rejectBlankNotes(){
        assertThrows(IllegalException.class,()->
                new Course("1","",1,"url"));
    }

}