package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CourseServer {
    //static initializer block will be executed once when the CourseServer class
    //is loaded by the JVM
    //this is what is required so that it can be executed early on
    //in the lifecycle of our application
    static{
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
        //it comes from the jul-to-slf4j  maven dependency. this bridge handler takes care of installing some hooks
        //into the JDK Logging API that were redirected to SLF4J
        //this code is executed very early before any of teh other code is executed
        //all subsequent calls to the JDK API will be intercepted by the bridge handler
        //and redirected to slf4j, so that we have one unified logging approach in our application



    }


private static final Logger LOG = (Logger) LoggerFactory.getLogger(CourseServer.class);
    private static String BASE_URI="http://localhost:8080/";

    public static void main(String... args ){
    LOG.info("starting the HTTP server");
        CourseRepository courseRepository= CourseRepository.openCourseRepository("./courses.db");
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),config);
    }
}
