package com;

import com.application.ApplicationService;
import com.config.JavaConfig;
import com.domain.Student;
import com.domain.StudentRepository;
import javafx.scene.input.DataFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.text.DateFormatter;
import java.sql.Date;

/**
 * Created by sasha on 02.10.15.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = (ApplicationService) context.getBean("applicationService");
        StudentRepository repository = (StudentRepository) context.getBean("repository");
        System.out.println("concat _3");
        service.concatStudentName3();
        System.out.println(repository.getAllStudents());
        System.out.println("repeated names");

        System.out.println(service.getAllStudentsWithRepeatedNames());
//        Student student = new Student();
//        student.setName("Sasha");
//        student.setBirthday(new Date(100000));
//        student.setSurname("Andreev");
//        student.setGroupe("AI111");
//      //  repository.createStudent(student);
//        System.out.println("GET ALL");
//        System.out.println(repository.getAllStudents());
    }
}
