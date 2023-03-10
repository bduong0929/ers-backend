package com.jean.ers.daos;

import com.jean.ers.models.User;
import com.jean.ers.utils.HibernateFactory;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserDAO implements CrudDAO<User> {
    @Override
    public void save(User obj) {
        try (SessionFactory sf = HibernateFactory.getInstance().getSessionFactory()) {
            Session s = sf.openSession();
            s.beginTransaction();
            s.persist(obj);
            s.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findAllById(String id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (SessionFactory sf = HibernateFactory.getInstance().getSessionFactory()) {
            Session s = sf.openSession();
            s.beginTransaction();
            users = s.createQuery("SELECT u from User u", User.class).stream().collect(Collectors.toList());
            s.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return users;
    }
}
