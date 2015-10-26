package com.application;

import com.config.JavaConfig;
import com.domain.Student;
import com.domain.StudentRepository;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sasha on 10/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class ApplicationServiceIntegrationTest {
    private static Flyway flyway;
    @Autowired
    StudentRepository repository;
    @Autowired
    ApplicationService service;
    @BeforeClass
    public static void configInMemoryBd(){
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        DataSource dataSource = context.getBean(DataSource.class);
        flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setTargetAsString("1");
        flyway.migrate();
    }
    @Test
    public void testConcatStudentName3() throws Exception {
        migrateToVersion("2");
        Date date = Date.valueOf("1970-01-01");
        List<Student> list = new ArrayList<>();
        list.add(new Student(3,"Eduard_3", "Andreev",date ,"AI111"));
        list.add(new Student(4,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(6,"Eduard_3", "Andreev",date ,"AI111"));
        list.add(new Student(7,"Sasha", "Andreev",date ,"AI111"));

        service.concatStudentName3();
        assertEquals(list, repository.getAllStudents());
    }
    @Test
    public void testConcatStudentName3NoMathes(){
        migrateToVersion("21");
        java.sql.Date date = java.sql.Date.valueOf("1970-01-01");
        List<Student> list = new ArrayList<>();
        list.add(new Student(3,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(4,"Sasha", "Andreev",date ,"AI111"));
        service.concatStudentName3();
        assertEquals(list, repository.getAllStudents());
    }
    @Test
    public void testConcatUserName3EmptyData() throws Exception {
        migrateToVersion("11");
        service.concatStudentName3();
        assertEquals(new ArrayList<Student>(), repository.getAllStudents());
    }

    @Test
    public void testGetAllStudentsWithRepeatedNames() throws Exception {
        migrateToVersion("3");
        Date date = Date.valueOf("1970-01-01");
        List<Student> list = new ArrayList<>();
        list.add(new Student(1,"Eduard", "Andreev",date ,"AI111"));
        list.add(new Student(3,"Eduard", "Andreev",date ,"AI111"));
        list.add(new Student(2,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(4,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(5,"Sasha", "Andreev",date ,"AI111"));

        assertEquals(list, service.getAllStudentsWithRepeatedNames());
    }

    @Test
    public void testGetAllStudentsWithRepeatedNamesNoRepeat() throws Exception {
        migrateToVersion("31");
        assertEquals(new ArrayList<Student>(), service.getAllStudentsWithRepeatedNames());
    }

    @Test
    public void testGetAllStudentsWithRepeatedNamesEmptyList() throws Exception {
        migrateToVersion("11");

        assertEquals(new ArrayList<Student>(), service.getAllStudentsWithRepeatedNames());
    }
    @After
    public void releswDB(){
        flyway.clean();
    }
    private void migrateToVersion(String vers){
        flyway.setTargetAsString(vers);
        flyway.migrate();
    }
}