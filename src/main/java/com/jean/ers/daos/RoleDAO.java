package com.jean.ers.daos;

import com.jean.ers.models.Role;
import com.jean.ers.utils.HibernateFactory;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoleDAO implements CrudDAO<Role> {
    @Override
    public void save(Role obj) {

    }

    @Override
    public void update(String id) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Role findById(String id) {
        return null;
    }

    @Override
    public List<Role> findAllById(String id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();

        try (SessionFactory sf = HibernateFactory.getInstance().getSessionFactory()) {
            Session s = sf.openSession();
            s.beginTransaction();
            roles = s.createQuery("SELECT r FROM Role r", Role.class).stream().toList();
            s.getTransaction().commit();
        } catch (SessionException e) {
            e.printStackTrace();
        }

        return roles;
    }
}
