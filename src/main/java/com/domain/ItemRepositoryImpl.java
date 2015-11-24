package com.domain;

import org.eclipse.persistence.sessions.factories.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sasha on 11/22/15.
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {
//    @Autowired
//    SessionFactory sessionFactory;
//    @Override
//    public void createItem(Item student) {
//
//    }
//
//    @Override
//    public Item getItem(int id) {
//        return null;
//    }
//
//    @Override
//    public void removeItem(Item student) {
//
//    }
//
//    @Override
//    public void editItem(Item student) {
//
//    }
//
//    @Override
//    public List<Item> getAllItems() {
//        return null;
//    }

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void createItem(Item item) {
        entityManager.persist(item);
    }

    @Override
    public Item getItem(int id) {
        return entityManager.find(Item.class,id);
    }

    @Override
    public void removeItem(Item item) {
        entityManager.remove(item);
    }

    @Override
    public void editItem(Item item) {
        entityManager.merge(item);
    }

    @Override
    public List<Item> getAllItems() {
        return entityManager.createQuery("from"+Item.class.getName()).getResultList();
    }
}
