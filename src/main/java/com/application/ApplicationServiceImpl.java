package com.application;

import com.domain.Student;
import com.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */
public class ApplicationServiceImpl implements ApplicationService {


    StudentRepository repository;

    @Autowired
    public ApplicationServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public int concatStudentName3() {
        int count=0;
        List<Student> list= repository.getAllStudents();
        for(Student student:list){
            if(student.getName().startsWith("E")){
                student.setName(student.getName()+"_3");
                repository.editStudent(student);
                count++;
            }
        }
        return count;
    }
    @Transactional
    public List<Student> getAllStudentsWithRepeatedNames() {
        List<Student> answer = new ArrayList<Student>();
        List<Student> list = repository.getAllStudents();

        boolean[] aded = new boolean[list.size()];
        boolean repeat=false;
        Arrays.fill(aded, false);
        for (int i = 0; i < list.size() ; i++) {
            for (int j = 0; j < list.size(); j++) {
                if(i!=j&&!aded[i]&&list.get(i).getName().equals(list.get(j).getName())){
                    if(!repeat)answer.add(list.get(i));
                    repeat=true;
                    answer.add(list.get(j));
                    aded[j]=true;

                }
            }
            repeat =false;
        }
        return answer;
    }
}
