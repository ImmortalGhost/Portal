package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.Messages1To1;
import ru.epam.university_portal.model.entity.News;
import ru.epam.university_portal.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владос on 09.05.2016.
 */
public class Messages1To1DAO extends BaseDAO {
    @Autowired
    Messages1To1 messages1To1;
    public void create(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception {
            Session session = factory.openSession();
            Transaction t = session.beginTransaction();
            List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                    .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
            if (l1.size() == 0) {
                throw new Exception("Exception - user '" + fromName + "' '" + fromLastName + "' was not found");
            }
            List<User> l2 = session.createQuery("from User u where u.name = :toName and u.lastName = :toLastName")
                    .setParameter("toName", toName).setParameter("toLastName", toLastName).list();
            if (l2.size() == 0) {
                throw new Exception("Exception - user '" + toName + "' '" + toLastName + "' was not found");
            }
            messages1To1.setIdFrom(l1.get(0).getId());
            messages1To1.setIdTo(l2.get(0).getId());
            messages1To1.setMessage(message);
            session.save(messages1To1);
            t.commit();
            session.close();
    }
    public List<Messages1To1> getAll(String fromName, String fromLastName, String toName, String toLastName) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
        if (l1.size() == 0) {
            return new ArrayList<Messages1To1>();
        }
        List<User> l2 = session.createQuery("from User u where u.name = :toName and u.lastName = :toLastName")
                .setParameter("toName", toName).setParameter("toLastName", toLastName).list();
        if (l2.size() == 0) {
            return new ArrayList<Messages1To1>();
        }
        List<Messages1To1> l3 = session.createQuery("from Messages1To1 m where (m.idFrom = :id1 and m.idTo = :id2) or (m.idFrom = :id2 and m.idTo = :id1)")
                .setParameter("id1", l1.get(0).getId()).setParameter("id2", l2.get(0).getId()).list();
        session.close();
        return l3;
    }
    public Messages1To1 get(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception{
        Session session = factory.openSession();
        List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + fromName + "' '" + fromLastName + "' was not found");
        }
        List<User> l2 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", toName).setParameter("lastName", toLastName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + toName + "' '" + toLastName + "' was not found");
        }
        List<Messages1To1> l3 = session.createQuery("from Messages1To1 m where m.idFrom = :from " +
                "and m.idTo = :to and m.message = :message")
                .setParameter("from", l1.get(0).getId()).setParameter("to", l2.get(0).getId()).setParameter("message", message).list();
        session.close();
        return l3.get(l3.size() - 1);
    }
    public void removeAll(String fromName, String fromLastName, String toName, String toLastName) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + fromName + "' '" + fromLastName + "' was not found");
        }
        List<User> l2 = session.createQuery("from User u where u.name = :toName and u.lastName = :toLastName")
                .setParameter("toName", toName).setParameter("toLastName", toLastName).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - user '" + toName + "' '" + toLastName + "' was not found");
        }
        List<Messages1To1> l3 = session.createQuery("from Messages1To1 m where m.idFrom = :id1 and m.idTo = :id2")
                .setParameter("id1", l1.get(0).getId()).setParameter("id2", l2.get(0).getId()).list();
        for (Messages1To1 m : l3) {
            session.delete(m);
        }
        t.commit();
        session.close();
    }
    public void remove(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + fromName + "' '" + fromLastName + "' was not found");
        }
        List<User> l2 = session.createQuery("from User u where u.name = :toName and u.lastName = :toLastName")
                .setParameter("toName", toName).setParameter("toLastName", toLastName).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - user '" + toName + "' '" + toLastName + "' was not found");
        }
        List<Messages1To1> l3 = session.createQuery("from Messages1To1 m where m.idFrom = :id1 and m.idTo = :id2 and m.message = :mes")
                .setParameter("id1", l1.get(0).getId()).setParameter("id2", l2.get(0).getId())
                .setParameter("mes", message).list();
        for (Messages1To1 m : l3) {
            session.delete(m);
        }
        t.commit();
        session.close();
    }
}
