package ru.epam.university_portal.core.dao;

/**
 * Created by Владос on 08.05.2016.
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends BaseDAO {
    @Autowired
    Role role;
    public void create(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        role.setName(name);
        session.save(role);
        t.commit();
        session.close();
    }
    public void remove(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Role> l = session
                .createQuery("FROM Role r where r.name = :parameter")
                .setParameter("parameter", name).list();
        if (l.size() > 0) {
            for (Role r : l) {
                session.delete(r);
            }
            t.commit();
        }
        session.close();
    }
    public Role get(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Role> l = session
                .createQuery("FROM Role r where r.name = :parameter")
                .setParameter("parameter", name).list();
        session.close();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }
    public Role get(int id) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Role> l = session
                .createQuery("FROM Role r where r.id = :parameter")
                .setParameter("parameter", id).list();
        session.close();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }
    public String getName(int id) {
        Role role = get(id);
        return role.getName();
    }
    public List<Role> getAll(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Role> l = session.createQuery("FROM Role").list();
        session.close();
        return l;
    }
    public List<String> getAllNames() {
        List<Role> roleList = getAll();
        List<String> result = new ArrayList<String>();
        for (Role r : roleList) {
            result.add(r.getName());
        }
        return result;
    }
}
