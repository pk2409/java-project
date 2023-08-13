package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class CourseServer {


private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class)
    public static void main(String... args ){
    LOG.info("starting teh HTTP server");
        CourseRepository courseRepository= CourseRepository.openCourseRepository("./courses.db");
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));
    }
}
