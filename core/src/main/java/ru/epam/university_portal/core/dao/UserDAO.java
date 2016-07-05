package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.Role;
import ru.epam.university_portal.model.entity.Student;
import ru.epam.university_portal.model.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Владос on 07.06.2016.
 */
public class UserDAO extends BaseDAO {
    public UserDAO(){
        super();
    }
    public User getByLoginAndPassword(String login, String password) {
        Session session = factory.openSession();
        List<User> l = session.createQuery("from User u where u.login = :login and u.password = :password").
                setParameter("login", login).setParameter("password", password).list();
        if (l.size() == 0) {
            session.close();
            return null;
        }
        else {
            session.close();
            return l.get(0);
        }
    }
    public User get(String name, String lastName) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> test = session.createQuery("from User u where u.name = :name and u.lastName = :lastName").
                setParameter("name", name).setParameter("lastName", lastName).list();
        User us = test.get(0);
        if (test.size() == 0) {
            session.close();
            return null;
        }
        else {
            session.close();
            return test.get(0);
        }
    }
    public User get(int id) {
        Session session = factory.openSession();
        List<User> l = session.createQuery("from User u where u.id = :id").
                setParameter("id", id).list();
        session.close();
        if (l.size() == 0) {

            return null;
        }
        else {
            return l.get(0);
        }
    }
}
