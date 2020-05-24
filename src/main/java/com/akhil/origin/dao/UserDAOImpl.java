package com.akhil.origin.dao;

import com.akhil.origin.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query = currentSession.createQuery("from User", User.class);

        List<User> users = query.getResultList();

        return users;

    }
}
