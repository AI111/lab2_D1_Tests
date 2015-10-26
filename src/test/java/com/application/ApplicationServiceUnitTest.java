package com.application;

import com.domain.Student;
import com.domain.StudentRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by sasha on 10/2/15.
 */
public class ApplicationServiceUnitTest {

    @Test
    public void testConcatUserName3() throws Exception {
        Date date = Date.valueOf("1970-01-01");
        List<Student> list = new ArrayList<>();
        list.add(new Student("NAME", "LAST NAME",date ,"AI11"));
        list.add(new Student("ENAME", "LAST NAME", date, "AI111"));
        list.add(new Student("enE", "LAST NAME", date, "AI111"));
        list.add(new Student("ENAME2_3", "LAST NAME", date, "AI111"));
        list.add(new Student("E", "LAST NAME", date, "AI111"));
        list.add(new Student("NAME", "LAST NAME", date, "AI111"));
        StudentRepository mockRepository = mock(StudentRepository.class);
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        when(mockRepository.getAllStudents()).thenReturn(list);
        applicationService.concatStudentName3();
        verify(mockRepository, times(1)).getAllStudents();
        verify(mockRepository).editStudent(new Student("ENAME_3", "LAST NAME", date, "AI111"));
        verify(mockRepository).editStudent(new Student("ENAME2_3_3", "LAST NAME", date, "AI111"));
        verify(mockRepository).editStudent(new Student("E_3", "LAST NAME", date, "AI111"));

    }

    @Test
    public void testConcatUserName3EmptyData() throws Exception {
        List<Student> list = new ArrayList<>();

        StudentRepository mockRepository = mock(StudentRepository.class);
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;

        when(mockRepository.getAllStudents()).thenReturn(list);

        int i=applicationService.concatStudentName3();
        verify(mockRepository, times(1)).getAllStudents();
        verify(mockRepository,never()).editStudent(any(Student.class));
        assertEquals(i, 0);

    }

    @Test
    public void testConcatUserName3NoMatches() throws Exception {
        Date date = new Date(0);
        List<Student> list = new ArrayList<>();
        list.add(new Student("NAME", "LAST NAME",date, "AI111" ));
        list.add(new Student("enE", "LAST NAME", date, "AI111"));
        list.add(new Student("1ENAME2_3", "LAST NAME", date, "AI111"));
        list.add(new Student("e", "LAST NAME", date, "AI111"));

        StudentRepository mockRepository = mock(StudentRepository.class);
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        when(mockRepository.getAllStudents()).thenReturn(list);

        int i=applicationService.concatStudentName3();
        verify(mockRepository, times(1)).getAllStudents();
        verify(mockRepository,never()).editStudent(any(Student.class));
        assertEquals(i, 0);
    }
    @Test
    public void testGetAllStudentsWithRepeatedNames() throws Exception {
        Date date = new Date(0);
        List<Student> list = new ArrayList<>();
        list.add(new Student("NAME", "LAST NAME",date, "AI111" ));
        list.add(new Student("ENAME", "LAST NAME", date, "AI111"));
        list.add(new Student("ENAME", "LAST NAME", date, "AI111"));
        list.add(new Student("ENAME2", "LAST NAME", date, "AI111"));
        list.add(new Student("ENAME", "LAST NAME", date, "AI111"));
        list.add(new Student("NAME", "LAST NAME", date, "AI111"));

        List<Student> mustReturn = new ArrayList<>();
        mustReturn.add(new Student("NAME", "LAST NAME", date, "AI111"));
        mustReturn.add(new Student("NAME", "LAST NAME", date, "AI111"));
        mustReturn.add(new Student("ENAME", "LAST NAME", date, "AI111"));
        mustReturn.add(new Student("ENAME", "LAST NAME", date, "AI111"));
        mustReturn.add(new Student("ENAME", "LAST NAME", date, "AI111"));

        StudentRepository mockRepository = mock(StudentRepository.class);
        when(mockRepository.getAllStudents()).thenReturn(list);

        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        List ans = applicationService.getAllStudentsWithRepeatedNames();
        assertEquals(ans, mustReturn);
        verify(mockRepository,times(1)).getAllStudents();

    }

    @Test
    public void testGetAllStudentsWithRepeatedNamesEmptyList() throws Exception {
        StudentRepository mockRepository = mock(StudentRepository.class);
        when(mockRepository.getAllStudents()).thenReturn(new ArrayList<Student>());
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        List ans = applicationService.getAllStudentsWithRepeatedNames();
        assertEquals(ans,new ArrayList<Student>() );
        verify(mockRepository,times(1)).getAllStudents();
    }

    @Test
    public void testGetAllStudentsWithRepeatedNamesNoRepeat() throws Exception {
        Date date = new Date(0);
        List<Student> list = new ArrayList<>();
        list.add(new Student("NAME", "LAST NAME",date, "AI111" ));
        list.add(new Student("ENAME-", "LAST NAME", date, "AI111"));
        list.add(new Student("ENAME1", "LAST NAME", date, "AI111"));
        list.add(new Student("ENAME2", "LAST NAME", date, "AI111"));
        list.add(new Student("ENAME3", "LAST NAME", date, "AI111"));
        list.add(new Student("NAME7", "LAST NAME", date, "AI111"));

        StudentRepository mockRepository = mock(StudentRepository.class);
        when(mockRepository.getAllStudents()).thenReturn(list);

        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        List ans = applicationService.getAllStudentsWithRepeatedNames();
        assertEquals(ans, new ArrayList<Student>());
        verify(mockRepository,times(1)).getAllStudents();

    }
}