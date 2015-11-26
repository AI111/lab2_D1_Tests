package com.domain;

import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */

public interface ItemRepository {
    void createItem(Item item);
    void createItems(List<Item> item);
    Item getItem(int id);
    void removeItem(Item item);
    void editItem(Item item);
    List<Item> getAllItems();

}
