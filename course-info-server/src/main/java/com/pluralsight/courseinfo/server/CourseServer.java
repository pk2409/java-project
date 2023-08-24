package com.pluralsight.courseinfo.server;

import com.pluralsight.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
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

        //in a real application we switch the simple logging backend with
        //full fledged implementation like Logback
        //in that case the slf4j setup will still work
        //all log statements will end up in the new Logback implementation

        //external configuration for a java application
        //externalising means to move configuration data out of code
        //we can use configuration files that can use configuration properties for the application
        //we can try to externalise the name of the database required


    }


private static final Logger LOG = (Logger) LoggerFactory.getLogger(CourseServer.class);
    private static String BASE_URI="http://localhost:8080/";

    public static void main(String... args ){
        String databaseFilename=loadDatabaseFilename();
        LOG.info("starting the HTTP server {}");
        CourseRepository courseRepository= CourseRepository.openCourseRepository(databaseFilename);
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),config);
    }

    private static String loadDatabaseFilename() {
        //by putting something in the resource folder, it is available in the JAR file
        //this means that it is available in the class path for us to load
        try(InputStream propertiesStream=
                CourseServer.class.getResourceAsStream("/server.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            return properties.getProperty("course-info.database");
        }catch (IOException e){
            throw new IllegalStateException("could not load database filename");
        }
    }
}
