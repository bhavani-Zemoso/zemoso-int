package com.springboot.application.OnlineBookStore.dao;

import com.springboot.application.OnlineBookStore.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findByUserName(String userName) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);

        theQuery.setParameter("uName", userName);
        User theUser = null;

        try {
            theUser = theQuery.getSingleResult();
        }
        catch (Exception e)
        {
            theUser = null;
        }

        return theUser;
    }

    @Override
    public void save(User user) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(user);
    }

    @Override
    public User findRecentUser() {

        Session currentSession = entityManager.unwrap(Session.class);

        User theUser = null;

        Query<User> theQuery = currentSession.createQuery("from User order by id DESC", User.class);
        theQuery.setMaxResults(1);

        try{
            theUser = theQuery.getSingleResult();
        }

        catch(Exception exception){
            System.out.println("Cannot find user");
        }

        return theUser;
    }
}
