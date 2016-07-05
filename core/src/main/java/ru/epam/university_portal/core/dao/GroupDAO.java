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
import org.springframework.core.env.SystemEnvironmentPropertySource;
import ru.epam.university_portal.model.entity.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends BaseDAO {
    @Autowired
    Group group;
    public void create(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        group.setName(name);
        session.save(group);
        t.commit();
        session.close();
    }
    public void remove(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l = session
                .createQuery("FROM Group g where g.name = :parameter")
                .setParameter("parameter", name).list();
        if (l.size() > 0) {
            for (Group g : l) {
                session.delete(g);
            }
            t.commit();
        }
        session.close();
    }
    public void rename(String oldName, String newName) {
        group = get(oldName);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        group.setName(newName);
        session.saveOrUpdate(group);
        transaction.commit();
        session.close();
    }
    public Group get(String name) {
        Session session = factory.openSession();
        List<Group> l = session
                .createQuery("FROM Group g where g.name = :parameter")
                        .setParameter("parameter", name).list();
        session.close();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }
    public Group get(int id) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l = session
                .createQuery("FROM Group g where g.id = :parameter")
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
        group = get(id);
        return group.getName();
    }
    public List<Group> getAll(){
        Session session = factory.openSession();
        List<Group> l = session.createQuery("FROM Group").list();
        session.close();
        return l;
    }
    public List<String> getAllNames() {
        Session session = factory.openSession();
        List<Group> l = session.createQuery("FROM Group").list();
        session.close();
        List<String> result = new ArrayList<String>();
        for (Group g : l) {
            result.add(g.getName());
        }
        return result;
    }
}
