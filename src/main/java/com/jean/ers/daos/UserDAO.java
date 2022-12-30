package com.jean.ers.daos;

import com.jean.ers.models.User;
import com.jean.ers.utils.HibernateFactory;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;

import java.util.List;

@ApplicationScoped
public class UserDAO implements CrudDAO<User> {
    @Override
    public void save(User obj) {
        try (SessionFactory sf = HibernateFactory.getInstance().getSessionFactory()) {
            Session s = sf.openSession();
            s.getTransaction().begin();
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
        return null;
    }
}
