package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.model.entity.Role;
import ru.epam.university_portal.model.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Владос on 11.05.2016.
 */
public class AdminDAO extends BaseDAO {
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    Role role;
    @Autowired
    User user;
    public void create (String login, String password) throws Exception {
        role = roleDAO.get("Admin");
        if (role == null) {
            throw new Exception("Exception - the role 'Admin' was not found");
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l = session.createQuery("from User u where u.login = :login and u.password = :password")
                .setParameter("login", login).setParameter("password", password).list();
        if (l.size() > 0) {
            throw new Exception("Exception - this user is already exists");
        }
        user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setIdRole(role.getId());
        session.save(user);
        t.commit();
        session.close();
    }
    public void createOrUpdate(String login, String password, String name, String lastName, Date date)
            throws Exception {
        role = roleDAO.get("Admin");
        if (role == null) {
            throw new Exception("Exception - the role 'Admin' was not found");
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l = session.createQuery("from User u where u.login = :login and u.password = :password")
                .setParameter("login", login).setParameter("password", password).list();
        if (l.size() > 0) {
            l.get(0).setName(name);
            l.get(0).setLastName(lastName);
            l.get(0).setAge(date);
            session.saveOrUpdate(l.get(0));
        } else {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setIdRole(role.getId());
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(date);
            session.save(user);
        }
        t.commit();
        session.close();
    }
    public User get(String name, String lastName) {
        Session session = factory.openSession();
        List<User> l1 = session.createQuery("from User u where u.name = :p1 and u.lastName = :p2")
                .setParameter("p1", name).setParameter("p2", lastName).list();
        session.close();
        if (l1.size() == 0) {
            return null;
        }
        else {
            return l1.get(0);
        }
    }
    public User getByLoginAndPassword(String login, String password) {
        Session session = factory.openSession();
        List<User> l1 = session.createQuery("from User u where u.login = :p1 and u.password = :p2")
                .setParameter("p1", login).setParameter("p2", password).list();
        session.close();
        if (l1.size() == 0) {
            return null;
        }
        else {
            return l1.get(0);
        }
    }
    public List<User> getAll() throws Exception {
        Session session = factory.openSession();
        List<Role> l1 = session.createQuery("from Role r where r.name = :name")
                .setParameter("name", "Admin").list();
        session.close();
        if (l1.size() == 0) {
            throw new Exception("Exception - role Admin has not found");
        }
        List<User> l2 = session.createQuery("from User u where u.idRole = :role")
                .setParameter("role", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            return new ArrayList<User>();
        }
        else {
            return l2;
        }
    }
    public List<String> getAllNames() throws Exception {
        List<User> userList = getAll();
            List<String> result = new ArrayList<String>();
            for(User u : userList) {
                result.add(u.getName() + " " + u.getLastName());
            }
            return result;
    }
    public void remove(String login, String password) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.login = :login and u.password = :password")
                .setParameter("login", login).setParameter("password", password).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + login + "' '" + password + "' was not found");
        }
        session.delete(l1.get(0));
        t.commit();
        session.close();
    }
}
