package com.abc.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Transactional
    public void save(User user) {
        Session session = getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void delete(User user) {
        getSession().delete(user);
    }

    public List getAll() {
        return getSession().createQuery("from User").list();
    }

    public User getByEmail(String email) {
        return (User) getSession().createQuery(
                "from User where email = :email")
                .setParameter("email", email)
                .list().get(0);
    }

    public User getById(long id) {
        return (User) getSession().load(User.class, id);
    }

    public void update(User user) {
        getSession().update(user);
    }

}
