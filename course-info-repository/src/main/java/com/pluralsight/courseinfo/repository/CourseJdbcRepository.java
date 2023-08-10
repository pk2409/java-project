package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;
//import org.h2.jdbc.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 class CourseJdbcRepository implements CourseRepository{

    private static final String H2_DATABASE_URL=
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";


    private static final String INSERT_COURSE= """
            MERGE INTO Courses(id,name,length,url)
                        VALUES(?,?,?,?)
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
    public void saveCourse(Course course){
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setString(3, course.length());
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
                resultSet.getString(4));

                courses.add(course);
            }
            return Collections.unmodifiableList(courses);
        }catch(SQLException e){
            throw new RepositoryException("Failed to retrieve courses",e);


        }
    }
}

//need to work on the jdbc datasource imports
//import org.h2.jdbc.JdbcDataSource; is the given statemnet which is showing an error
//need to figure out how to work with the database
//due to this teh coursestroageservicetest.java is not working either
//courserepository.java upon running also shows an error due to the same reason.


//JAX-RS is an API specification
//using eclipse jersey which is JAX-RS  implementation
