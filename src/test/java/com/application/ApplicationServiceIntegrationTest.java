package com.application;

import com.config.JavaConfig;
import com.domain.Item;
import com.domain.ItemRepository;
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
import java.util.Arrays;
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
    ItemRepository repository;
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
    public void testConcatItemName3() throws Exception {
        migrateToVersion("2");
        Date date = Date.valueOf("1970-01-01");
        List<Item> list = new ArrayList<>(Arrays.asList(new Item[]{
                new Item(1,"Ename_3", "description",0.99),
                new Item(2,"Ename_3", "description",0.99),
                new Item(3,"Uname", "description",0.99),
                new Item(4,"E_3", "description",0.99)

    }));
        service.concatItemName3();
        assertEquals(list, repository.getAllItems());
    }



    @Test
    public void testGetAllItemsWithRepeatedNames() throws Exception {
        migrateToVersion("3");
        Date date = Date.valueOf("1970-01-01");
        List<Item> list = new ArrayList<>();
        list.add(new Item(1,"Ename", "description", 0.99));
        list.add(new Item(3,"Ename", "description", 0.99));
        list.add(new Item(2,"name", "description", 0.99));
        list.add(new Item(4,"name", "description", 0.99));
        list.add(new Item(5,"name", "description", 0.99));

        assertEquals(list, service.getAllItemsWithRepeatedNames());
    }
//
//    @Test
//    public void testGetAllStudentsWithRepeatedNamesNoRepeat() throws Exception {
//        migrateToVersion("31");
//        assertEquals(new ArrayList<Student>(), service.getAllStudentsWithRepeatedNames());
//    }
//
//    @Test
//    public void testGetAllStudentsWithRepeatedNamesEmptyList() throws Exception {
//        migrateToVersion("11");
//
//        assertEquals(new ArrayList<Student>(), service.getAllStudentsWithRepeatedNames());
//    }
    @After
    public void releswDB(){
        flyway.clean();
    }
    private void migrateToVersion(String vers){
        flyway.setTargetAsString(vers);
        flyway.migrate();
    }
}