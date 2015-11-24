package com.domain;

import org.eclipse.persistence.sessions.factories.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sasha on 11/22/15.
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void createItem(Item item) {
        entityManager.persist(item);
    }
    @Transactional
    @Override
    public void createItems(List<Item> items) {
        items.stream().forEach(item -> entityManager.persist(item));
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
        return entityManager.createQuery("SELECT a FROM  Item a").getResultList();
    }
}
