package com.application;

import com.config.JavaConfig;
import com.domain.Student;
import com.domain.StudentRepository;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sasha on 10/3/15.
 */
public class ApplicationServiceIntegrationTest {
    private static Flyway flyway;

    @BeforeClass
    public static void configInMemoryBd(){
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setTargetAsString("1");
        flyway.migrate();
        System.out.println("configInMemoryBd");
    }
    @Test
    public void testConcatStudentName3() throws Exception {
        flyway.setTargetAsString("2");
        flyway.migrate();
        java.sql.Date date = java.sql.Date.valueOf("1970-01-01");
        List<Student> list = new ArrayList<>();
        list.add(new Student(3,"Eduard_3", "Andreev",date ,"AI111"));
        list.add(new Student(4,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(6,"Eduard_3", "Andreev",date ,"AI111"));
        list.add(new Student(7,"Sasha", "Andreev",date ,"AI111"));
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = (ApplicationService)context.getBean("applicationService");
        StudentRepository repository = (StudentRepository) context.getBean("repository");
        service.concatStudentName3();
        assertEquals(list, repository.getAllStudents());
    }
    @Test
    public void testConcatStudentName3NoMathes(){
        flyway.setTargetAsString("21");
        flyway.migrate();
        java.sql.Date date = java.sql.Date.valueOf("1970-01-01");
        List<Student> list = new ArrayList<>();
        list.add(new Student(3,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(4,"Sasha", "Andreev",date ,"AI111"));
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = (ApplicationService)context.getBean("applicationService");
        StudentRepository repository = (StudentRepository) context.getBean("repository");
        service.concatStudentName3();
        assertEquals(list, repository.getAllStudents());
    }
    @Test
    public void testConcatUserName3EmptyData() throws Exception {
        flyway.setTargetAsString("11");
        flyway.migrate();
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = (ApplicationService)context.getBean("applicationService");
        StudentRepository repository = (StudentRepository) context.getBean("repository");
        service.concatStudentName3();
        assertEquals(new ArrayList<Student>(), repository.getAllStudents());
    }

    @Test
    public void testGetAllStudentsWithRepeatedNames() throws Exception {
        flyway.setTargetAsString("3");
        flyway.migrate();
        java.sql.Date date = java.sql.Date.valueOf("1970-01-01");
        List<Student> list = new ArrayList<>();
        list.add(new Student(1,"Eduard", "Andreev",date ,"AI111"));
        list.add(new Student(3,"Eduard", "Andreev",date ,"AI111"));
        list.add(new Student(2,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(4,"Sasha", "Andreev",date ,"AI111"));
        list.add(new Student(5,"Sasha", "Andreev",date ,"AI111"));
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = (ApplicationService)context.getBean("applicationService");
        assertEquals(list, service.getAllStudentsWithRepeatedNames());
    }

    @Test
    public void testGetAllStudentsWithRepeatedNamesNoRepeat() throws Exception {
        flyway.setTargetAsString("31");
        flyway.migrate();
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = (ApplicationService)context.getBean("applicationService");
        assertEquals(new ArrayList<Student>(), service.getAllStudentsWithRepeatedNames());
    }

    @Test
    public void testGetAllStudentsWithRepeatedNamesEmptyList() throws Exception {
        flyway.setTargetAsString("11");
        flyway.migrate();
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = (ApplicationService)context.getBean("applicationService");
        assertEquals(new ArrayList<Student>(), service.getAllStudentsWithRepeatedNames());
    }
    @After
    public void releswDB(){
        flyway.clean();
    }
}