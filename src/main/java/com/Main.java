package com;

import com.application.ApplicationService;
import com.config.JavaConfig;
import com.domain.Item;
import com.domain.ItemRepository;
import com.domain.Student;
import com.domain.StudentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        ApplicationService service = context.getBean(ApplicationService.class);
        ItemRepository repository = context.getBean(ItemRepository.class);

        List<Item> list = new ArrayList<>(Arrays.asList(new Item[]{ new Item("name","description",199.99)}));
        list.stream().forEach(item -> repository.createItem(item));
//        System.out.println("concat _3");
//        service.concatStudentName3();
//        System.out.println(repository.getAllStudents());
//        System.out.println("repeated names");
//        System.out.println(service.getAllStudentsWithRepeatedNames());
    }
}
