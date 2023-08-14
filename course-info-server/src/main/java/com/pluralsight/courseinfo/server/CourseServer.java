package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.logging.Logger;

public class CourseServer {


private static final Logger LOG = (Logger) LoggerFactory.getLogger(CourseServer.class);
    private static String BASE_URI="http://localhost:8080/";

    public static void main(String... args ){
    LOG.info("starting the HTTP server");
        CourseRepository courseRepository= CourseRepository.openCourseRepository("./courses.db");
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),config);
    }
}
