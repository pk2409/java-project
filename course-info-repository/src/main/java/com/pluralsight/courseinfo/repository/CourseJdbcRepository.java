package com.pluralsight.courseinfo.repository;

import com.pluralsight.courseinfo.domain.Course;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CourseJdbcRepository implements CourseRepository{

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
        Connection connection= dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);


    }

    @Override
    public List<Course> getAllCourses(){
        return null;
    }
}
