package com.application;

import com.domain.Item;

import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */
public interface ApplicationService {
    int concatItemName3();
    List<Item> getAllItemsWithRepeatedNames();

}
