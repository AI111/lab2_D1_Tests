package com.application;

import com.domain.Item;
import com.domain.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * Created by sasha on 02.10.15.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ItemRepository repository;

    @Transactional
    public int concatItemName3() {
        int count=0;
        List<Item> list= repository.getAllItems();
        for(Item student:list){
            if(student.getName().startsWith("E")){
                student.setName(student.getName()+"_3");
                repository.editItem(student);
                count++;
            }
        }
        return count;
    }
    @Transactional
    public List<Item> getAllItemsWithRepeatedNames() {
        List<Item> answer = new LinkedList<>();
        List<Item> list = repository.getAllItems();
        Map<String,LinkedList<Item>> studentMap = new LinkedHashMap<>();
        for (Item student: list){
            if(studentMap.containsKey(student.getName())){
                studentMap.get(student.getName()).add(student);
            }else{
                studentMap.put(student.getName(),new LinkedList<>(Arrays.asList(new Item[]{student})));
            }
        }
        studentMap.forEach(new BiConsumer<String, LinkedList<Item>>() {
            @Override
            public void accept(String s, LinkedList<Item> students) {
                if(students.size()>1)answer.addAll(students);
            }
        });
        return answer;
    }
}
