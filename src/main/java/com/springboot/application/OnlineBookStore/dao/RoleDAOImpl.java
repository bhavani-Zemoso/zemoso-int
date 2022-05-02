package com.springboot.application.OnlineBookStore.dao;

import com.springboot.application.OnlineBookStore.entity.Role;
import com.springboot.application.OnlineBookStore.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String roleName) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);

        theQuery.setParameter("roleName", roleName);
        Role theRole = null;

        try {
            theRole = theQuery.getSingleResult();
        }
        catch (Exception e)
        {
            theRole = null;
        }

        return theRole;
    }
}
