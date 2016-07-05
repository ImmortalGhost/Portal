package ru.epam.university_portal.core.dao;

        import org.hibernate.Session;
        import org.hibernate.Transaction;
        import org.springframework.beans.factory.annotation.Autowired;
        import ru.epam.university_portal.model.entity.*;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Date;
        import java.util.List;

/**
 * Created by Владос on 09.05.2016.
 */
public class StudentDAO extends BaseDAO {
    @Autowired
    Student student;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    Role role;
    @Autowired
    GroupDAO groupDAO;
    @Autowired
    Group group;
    @Autowired
    User user;
    @Autowired
    Messages1To1DAO messages1To1DAO;
    @Autowired
    UserDAO userDAO;
    public void addInterlocutor(String name, String lastName, String nameSubject, String lastNameSubject) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l2 = session.createQuery("from User u where u.name = :nameSubject and u.lastName = :lastNameSubject").
                setParameter("nameSubject", nameSubject).setParameter("lastNameSubject", lastNameSubject).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - user - " + nameSubject + " " + lastNameSubject + " was not found");
        }
        student = get(name, lastName);
        user = l2.get(0);
        String idInterlocutors = student.getInterlocutorsIDs();
        if (idInterlocutors == null) {
            idInterlocutors = "";
        }
        student.setInterlocutorsIDs(idInterlocutors + "" + user.getId() + "|");
        session.saveOrUpdate(student);
        t.commit();
        session.close();
        session = factory.openSession();
        session.beginTransaction();
        getInterlocutors(name, lastName);
        session.close();
    }
    public List<User> getInterlocutors (String name, String lastName) throws Exception {
        student = get(name, lastName);
        Session session = factory.openSession();
        String locutorsString = student.getInterlocutorsIDs();
        if (locutorsString == null) {
            return new ArrayList<User>();
        }
        String[] locutors = locutorsString.split("\\|");
        List<String> l = Arrays.asList(locutors);
        List<User> result = new ArrayList<User>();
        for (String s : l) {
            int id = -1;
            try {
                id = Integer.parseInt(s);
            }
            catch(Exception e) {
                return new ArrayList<User>();
            }
            List<User> l1 = session.createQuery("from User u where u.id = :id").
                    setParameter("id", id).list();
            if (l1.size() == 0) {
                throw new Exception("Exception - user " + name + " " + lastName + " was not found");
            }
            result.add(l1.get(0));
        }
        session.close();
        return result;
    }
    public boolean InterlocutorIsExists (String name, String lastName, String nameSubject, String lastNameSubject) throws Exception {
        List<User> l = getInterlocutors(name, lastName);
        for (User u : l) {
            if (u.getName() == nameSubject && u.getLastName() == lastNameSubject) {
                return true;
            }
        }
        return false;
    }
    public void create (String login, String password, String nameGroup) throws Exception {
        role = roleDAO.get("Student");
        if (role == null) {
            throw new Exception("Exception - the role 'Student' was not found");
        }
        group = groupDAO.get(nameGroup);
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
        user.setLogin(login);
        user.setPassword(password);
        user.setIdRole(role.getId());
        session.save(user);
        student.setIdUser(user.getId());
        student.setIdGroup(group.getId());
        session.save(student);
        t.commit();
        session.close();
    }
    public void createOrUpdate(String login, String password, String nameGroup, String name, String lastName, Date date)
            throws Exception {
        role = roleDAO.get("Student");
        if (role == null) {
            throw new Exception("Exception - the role 'Student' was not found");
        }
        group = groupDAO.get(nameGroup);
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
            session.saveOrUpdate(l.get((0)));
            t.commit();
            t = session.beginTransaction();

            List<User> l1 = session.createQuery("from User u where u.login = :p1 and u.password = :p2")
                    .setParameter("p1", login).setParameter("p2", password).list();
            List<Student> l2 = session.createQuery("from Student s where s.idUser = :p1")
                    .setParameter("p1", l1.get(0).getId()).list();
            student = l2.get(0);

            student.setIdGroup(group.getId());
            session.saveOrUpdate(student);
            t.commit();
            session.close();
            session = factory.openSession();
            t = session.beginTransaction();
            List<User> l3 = session.createQuery("from User u where u.login = :login and u.password = :password").
                    setParameter("login", login).setParameter("password", password).list();
            user = l3.get(0);
            user.setLogin(login);
            user.setPassword(password);
            user.setIdRole(role.getId());
            session.saveOrUpdate(user);
        }
        else {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setIdRole(role.getId());
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(date);
            session.save(user);
            t.commit();
            t = session.beginTransaction();
            student = new Student();
            student.setIdUser(user.getId());
            student.setIdGroup(group.getId());
            session.save(student);
        }
        t.commit();
        session.close();
        User usr = userDAO.get(name, lastName);
    }
    public Student get(String name, String lastName) {
        Session session = factory.openSession();
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
        Session session = factory.openSession();
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
        Session session = factory.openSession();
        List<Student> l = session.createQuery("from Student").list();
        session.close();
        if (l.size() == 0) {
            return new ArrayList<Student>();
        }
        else {
            return l;
        }
    }
    public List<String> getAllNames() {
        List<Student> studentList = getAll();
        List<String> result = new ArrayList<String>();
        for (Student s : studentList) {
            result.add(getName(s) + " " + getLastName(s));
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
    public String getName (Student student) {
        Session session = factory.openSession();
        List<User> l = session.createQuery("from User u where u.id = :id").setParameter("id", student.getIdUser()).list();
        user = l.get(0);
        session.close();
        return user.getName();
    }
    public String getLastName (Student student) {
        Session session = factory.openSession();
        List<User> l = session.createQuery("from User u where u.id = :id").setParameter("id", student.getIdUser()).list();
        user = l.get(0);
        session.close();
        return user.getLastName();
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
        messages1To1DAO.create(fromName, fromLastName, toName, toLastName, message);
        Messages1To1 messages1To1 = messages1To1DAO.get(fromName, fromLastName, toName, toLastName, message);
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
    public Date getDate (Student student) {
        Session session = factory.openSession();
        List<User> l = session.createQuery("from User u where u.id = :id").setParameter("id", student.getIdUser()).list();
        user = l.get(0);
        session.close();
        return user.getAge();
    }
}
