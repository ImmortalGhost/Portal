package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.epam.university_portal.model.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Владос on 09.05.2016.
 */
public class StudentDAO extends BaseDAO {
    public StudentDAO() {
        super();
    }
    public void create (String login, String password, String nameGroup) throws Exception {
        RoleDAO roleDAO = new RoleDAO();
        Role role = roleDAO.get("Student");
        if (role == null) {
            throw new Exception("Exception - the role 'Student' was not found");
        }
        GroupDAO groupDAO = new GroupDAO();
        Group group = groupDAO.get(nameGroup);
        if (group == null) {
            throw new Exception("Exception - group '" + group.getName() + "' was not found");
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l = session.createQuery("from User u where u.login = :login and u.password = :password")
                .setParameter("login", login).setParameter("password", password).list();
        if (l.size() > 0) {
            throw new Exception("Exception - this user is already exists");
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setIdRole(role.getId());
        session.save(user);
        Student student = new Student();
        student.setIdUser(user.getId());
        student.setIdGroup(group.getId());
        session.save(student);
        t.commit();
        session.close();
    }
    public void createOrUpdate(String login, String password, String nameGroup, String name, String lastName, Date date)
    throws Exception {
        RoleDAO roleDAO = new RoleDAO();
        Role role = roleDAO.get("Student");
        if (role == null) {
            throw new Exception("Exception - the role 'Student' was not found");
        }
        GroupDAO groupDAO = new GroupDAO();
        Group group = groupDAO.get(nameGroup);
        if (group == null) {
            throw new Exception("Exception - group '" + group.getName() + "' was not found");
        }
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l = session.createQuery("from User u where u.login = :login and u.password = :password")
                .setParameter("login", login).setParameter("password", password).list();
        if (l.size() > 0) {
            l.get(0).setName(name);
            l.get(0).setLastName(lastName);
            l.get(0).setAge(date);
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setIdRole(role.getId());
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(date);
            session.save(user);
            Student student = new Student();
            student.setIdUser(user.getId());
            student.setIdGroup(group.getId());
            session.save(student);
        }
        t.commit();
        session.close();
    }
    public Student get(String name, String lastName) {
        session = factory.openSession();
        List<User> l1 = session.createQuery("from User u where u.name = :p1 and u.lastName = :p2")
                .setParameter("p1", name).setParameter("p2", lastName).list();
        if (l1.size() == 0) {
            return null;
        }
        List<Student> l2 = session.createQuery("from Student s where s.idUser = :p1")
                .setParameter("p1", l1.get(0).getId()).list();
        session.close();
        if (l2.size() == 0) {
            return null;
        }
        else {
            return l2.get(0);
        }
    }
    public Student getByLoginAndPassword(String login, String password) {
        session = factory.openSession();
        List<User> l1 = session.createQuery("from User u where u.login = :p1 and u.password = :p2")
                .setParameter("p1", login).setParameter("p2", password).list();
        if (l1.size() == 0) {
            return null;
        }
        List<Student> l2 = session.createQuery("from Student s where s.idUser = :p1")
                .setParameter("p1", l1.get(0).getId()).list();
        session.close();
        if (l2.size() == 0) {
            return null;
        }
        else {
            return l2.get(0);
        }
    }
    public List<Student> getAll() {
        session = factory.openSession();
        List<Student> l = session.createQuery("from Student").list();
        if (l.size() == 0) {
            return new ArrayList<Student>();
        }
        else {
            return l;
        }
    }
    public void addNewMessage(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + fromName + "' '" + fromLastName + "' was not found");
        }
        List<User> l2 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", toName).setParameter("lastName", toLastName).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - user '" + toName + "' '" + toLastName + "' was not found");
        }
        Messages1To1DAO dao = new Messages1To1DAO();
        dao.create(fromName, fromLastName, toName, toLastName, message);
        Messages1To1 messages1To1 = dao.get(fromName, fromLastName, toName, toLastName, message);
        List<Student> l3 = session.createQuery("from Student s where s.idUser = :idUser")
                .setParameter("idUser", l2.get(0).getId()).list();
        if (l3.size() == 0) {
            throw new Exception("Exception - student '" + toName + "' '" + toLastName + "' was not found");
        }
        String idMessages = l3.get(0).getNewMessagesIDs();
        if (idMessages == null) {
            idMessages = "";
        }
        l3.get(0).setNewMessagesIDs(idMessages + "" + messages1To1.getId() + "|");
        t.commit();
        session.close();
    }
    public void clearNewMessages(String name, String lastName) throws Exception{
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", name).setParameter("lastName", lastName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + name + "' '" + lastName + "' was not found");
        }
        List<Student> l2 = session.createQuery("from Student s where s.idUser = :idUser")
                .setParameter("idUser", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - student '" + name + "' '" + lastName + "' was not found");
        }
        l2.get(0).setNewMessagesIDs("");
        t.commit();
        session.close();
    }
    public void clearNewMessagesByLoginAndPassword(String login, String password) throws Exception{
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.login = :login and u.password = :password")
                .setParameter("login", login).setParameter("password", password).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + login + "' '" + password + "' was not found");
        }
        List<Student> l2 = session.createQuery("from Student s where s.idUser = :idUser")
                .setParameter("idUser", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - student '" + login + "' '" + password + "' was not found");
        }
        l2.get(0).setNewMessagesIDs("");
        t.commit();
        session.close();
    }
    public void remove(String login, String password) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User u where u.login = :login and u.password = :password")
                .setParameter("login", login).setParameter("password", password).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + login + "' '" + password + "' was not found");
        }
        List<Student> l2 = session.createQuery("from Student s where s.idUser = :idUser")
                .setParameter("idUser", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - student '" + login + "' '" + password + "' was not found");
        }
        session.delete(l1.get(0));
        session.delete(l2.get(0));
        t.commit();
        session.close();
    }
}
