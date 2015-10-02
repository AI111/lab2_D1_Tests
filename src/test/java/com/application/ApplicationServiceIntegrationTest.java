package com.application;

import com.config.JavaConfig;
import com.domain.StudentRepository;
import com.domain.StudentRepositoryImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.*;

/**
 * Created by sasha on 10/3/15.
 */
public class ApplicationServiceIntegrationTest {
    @Bean
    public void configInMemoryBd(){

    }
    @Test
    public void testConcatStudentName3() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        StudentRepository repository = (StudentRepository) context.getBean("repository");
        System.out.println(repository.getAllStudents());
    }

    @Test
    public void testGetAllStudentsWithRepeatedNames() throws Exception {

    }
}