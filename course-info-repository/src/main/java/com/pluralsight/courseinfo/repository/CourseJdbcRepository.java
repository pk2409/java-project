package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;
//import org.h2.jdbc.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CourseJdbcRepository implements CourseRepository{

    private static final String H2_DATABASE_URL=
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";


    private static final String INSERT_COURSE= """
            MERGE INTO Courses(id,name,length,url)
                        VALUES(?,?,?,?)
                        """;

    private static final String ADD_NOTES= """
            UPDATE Courses SET notes=?
            WHERE ID=?
            """;

    //question marks acts as placeholders in a PreparedStatement
    //use preparedstatement and the API to pass SQL statements
    private final DataSource dataSource;

    public CourseJdbcRepository(String databaseFile){
        JdbcDataSource jdbcDataSource= new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
        this.dataSource=jdbcDataSource;
    }
    @Override
    public void saveCourse(Course course) throws RepositoryException {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1, course.id());
            statement.setString(2, course.name());
//            statement.setString(3, course.length());
            statement.setString(4, course.url());
            statement.execute();
        }catch(SQLException e){
            throw new RepositoryException("Failed to save"+course,e);
        }



    }

    @Override
    public List<Course> getAllCourses() throws RepositoryException {
        try (Connection connection = dataSource.getConnection()){
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM COURSES");

            List<Course> courses= new ArrayList<>();

            while(resultSet.next()){
                Course course = new Course(resultSet.getString(1),
                                  resultSet.getString(2),
                                  resultSet.getLong(3),
                resultSet.getString(4),
                        Optional.ofNullable(resultSet.getString(5))
                );

                courses.add(course);
            }
            return Collections.unmodifiableList(courses);
        }catch(SQLException e){
            throw new RepositoryException("Failed to retrieve courses",e);


        }
    }

    @Override
    public void addNotes(String id, String notes) throws RepositoryException {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(ADD_NOTES);
            statement.setString(1,notes);
            statement.setString(2,id);
            statement.execute();
        }catch(SQLException e){
            throw new RepositoryException("Failed to add notes to"+id,e);
        }

    }
}

//need to work on the jdbc datasource imports
//import org.h2.jdbc.JdbcDataSource; is the given statement which is showing an error
//need to figure out how to work with the database
//due to this the coursestorageservicetest.java is not working either
//courserepository.java upon running also shows an error due to the same reason.


//JAX-RS is an API specification
//using eclipse jersey which is JAX-RS  implementation


//A Data Source object enables JDBC applications to obtain a DBMS connection from a connection pool
//A DataSource object holds the credentials needed to get a connection to the database.
//JDBC is java database connectivity
//It is used to write programs required to access databases.
//JDBC is an API used to access databases
//RepositoryException: This exception indicates that an error occurred while performing a Repository task
//This exception indicates that a severe error occured while performing a Repository task

//have sorted the jdbc dependency issue
//had to clear out the version of teh jdbc datasource
//using advanced java programming concepts book
//pluralsight course by sander mak
//on building applications with java
//we have course-info-cli
//course-info-repository and course-info-server
//also have pom.xml files of all of these
//pom.xml files have to have the recent version of each of the dependencies
//otherwise, the maven dependencies fail to reload
//this has to be noted from the maven central repository
//sometimes the dependencies take time to reload
//it might show some error initially
//but soon the files will be available in the external files library