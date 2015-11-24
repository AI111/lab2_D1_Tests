package com.application;

import com.domain.Item;
import com.domain.ItemRepository;
import com.domain.Student;
import com.domain.StudentRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Arrays;
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

        List<Item> list = new ArrayList<>(Arrays.asList(new Item[]{
                new Item("NAME", "description",0.99),
                new Item("ENAME", "description", 0.99),
                new Item("enE", "description", 0.99),
                new Item("ENAME2_3", "description",0.99),
                new Item("E", "description",  0.99),
                new Item("NAME", "description", 0.99)
        }));

        ItemRepository mockRepository = mock(ItemRepository.class);
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        when(mockRepository.getAllItems()).thenReturn(list);
        applicationService.concatItemName3();
        verify(mockRepository, times(1)).getAllItems();
        verify(mockRepository).editItem(new Item("ENAME_3", "description", 0.99));
        verify(mockRepository).editItem(new Item("ENAME2_3_3", "description", 0.99));
        verify(mockRepository).editItem(new Item("E_3", "description", 0.99));

    }

    @Test
    public void testConcatUserName3EmptyData() throws Exception {
        List<Item> list = new ArrayList<>();
        ItemRepository mockRepository = mock(ItemRepository.class);
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;

        when(mockRepository.getAllItems()).thenReturn(list);

        int i=applicationService.concatItemName3();
        verify(mockRepository, times(1)).getAllItems();
        verify(mockRepository,never()).editItem(any(Item.class));
        assertEquals(i, 0);

    }

    @Test
    public void testConcatUserName3NoMatches() throws Exception {
        Date date = new Date(0);
        List<Item> list = new ArrayList<>(Arrays.asList(new Item[]{
                new Item("NAME", "description",0.99),
                new Item("qNAME", "description", 0.99),
                new Item("fnE", "description", 0.99),
                new Item("ANAME2_3", "description",0.99)
        }));

        ItemRepository mockRepository = mock(ItemRepository.class);
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        when(mockRepository.getAllItems()).thenReturn(list);

        int i=applicationService.concatItemName3();
        verify(mockRepository, times(1)).getAllItems();
        verify(mockRepository,never()).editItem(any(Item.class));
        assertEquals(i, 0);
    }
    @Test
    public void testGetAllItemsWithRepeatedNames() throws Exception {
        List<Item> list = new ArrayList<>();
        list.add(new Item("NAME", "LAST NAME",0.99));
        list.add(new Item("ENAME", "LAST NAME", 0.99));
        list.add(new Item("ENAME", "LAST NAME", 0.99));
        list.add(new Item("ENAME2", "LAST NAME", 0.99));
        list.add(new Item("ENAME", "LAST NAME", 0.99));
        list.add(new Item("NAME", "LAST NAME", 0.99));

        List<Item> mustReturn = new ArrayList<>();
        mustReturn.add(new Item("NAME", "LAST NAME", 0.99));
        mustReturn.add(new Item("NAME", "LAST NAME", 0.99));
        mustReturn.add(new Item("ENAME", "LAST NAME", 0.99));
        mustReturn.add(new Item("ENAME", "LAST NAME", 0.99));
        mustReturn.add(new Item("ENAME", "LAST NAME", 0.99));

        ItemRepository mockRepository = mock(ItemRepository.class);
        when(mockRepository.getAllItems()).thenReturn(list);

        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        List ans = applicationService.getAllItemsWithRepeatedNames();
        assertEquals(ans, mustReturn);
        verify(mockRepository,times(1)).getAllItems();

    }

    @Test
    public void testGetAllItemsWithRepeatedNamesEmptyList() throws Exception {
        ItemRepository mockRepository = mock(ItemRepository.class);
        when(mockRepository.getAllItems()).thenReturn(new ArrayList<Item>());
        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        List ans = applicationService.getAllItemsWithRepeatedNames();
        assertEquals(ans,new ArrayList<Item>() );
        verify(mockRepository,times(1)).getAllItems();
    }

    @Test
    public void testGetAllItemsWithRepeatedNamesNoRepeat() throws Exception {
        Date date = new Date(0);
        List<Item> list = new ArrayList<>();
        list.add(new Item("NAME", "LAST NAME",0.99));
        list.add(new Item("ENAME-", "LAST NAME", 0.99));
        list.add(new Item("ENAME1", "LAST NAME", 0.99));
        list.add(new Item("ENAME2", "LAST NAME", 0.99));
        list.add(new Item("ENAME3", "LAST NAME", 0.99));
        list.add(new Item("NAME7", "LAST NAME", 0.99));

        ItemRepository mockRepository = mock(ItemRepository.class);
        when(mockRepository.getAllItems()).thenReturn(list);

        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.repository = mockRepository;
        List ans = applicationService.getAllItemsWithRepeatedNames();
        assertEquals(ans, new ArrayList<Item>());
        verify(mockRepository,times(1)).getAllItems();

    }
}