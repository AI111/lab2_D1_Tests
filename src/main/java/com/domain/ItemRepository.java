package com.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */

public interface ItemRepository {
    void createItem(Item student);
    Item getItem(int id);
    void removeItem(Item student);
    void editItem(Item student);
    List<Item> getAllItems();

}
