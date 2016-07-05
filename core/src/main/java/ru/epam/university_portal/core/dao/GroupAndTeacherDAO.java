package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.GroupAndTeacher;
import ru.epam.university_portal.model.entity.Teacher;
import ru.epam.university_portal.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владос on 11.05.2016.
 */
public class GroupAndTeacherDAO extends BaseDAO {
    @Autowired
    GroupAndTeacher groupAndTeacher;
    public void createRelation(String nameTeacher, String lastNameTeacher, String nameGroup) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User t where t.name = :name and t.lastName = :lastName")
                .setParameter("name", nameTeacher).setParameter("lastName", lastNameTeacher).list();
        if (l1.size() == 0) {
            throw new Exception("Exception user '" + nameTeacher + "' '" + lastNameTeacher + "' has not found");
        }
        List<Teacher> l2 = session.createQuery("from Teacher t where t.idUser = :user")
                .setParameter("user", l1.get(0).getId()).list();
        if (l1.size() == 0) {
            throw new Exception("Exception teacher '" + nameTeacher + "' '" + lastNameTeacher + "' has not found");
        }
        List<Group> l3 = session.createQuery("from Group g where g.name = :nameGroup")
                .setParameter("nameGroup", nameGroup).list();
        if (l1.size() == 0) {
            throw new Exception("Exception group '" + nameGroup + "' has not found");
        }
        groupAndTeacher.setIdTeacher(l2.get(0).getId());
        groupAndTeacher.setIdGroup(l3.get(0).getId());
        session.save(groupAndTeacher);
        t.commit();
        session.close();
    }
    public void removeRelation(String nameTeacher, String lastNameTeacher, String nameGroup) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<User> l1 = session.createQuery("from User t where t.name = :name and t.lastName = :lastName")
                .setParameter("name", nameTeacher).setParameter("lastName", lastNameTeacher).list();
        if (l1.size() == 0) {
            throw new Exception("Exception user '" + nameTeacher + "' '" + lastNameTeacher + "' was not found");
        }
        List<Teacher> l2 = session.createQuery("from Teacher t where t.idUser = :user")
                .setParameter("user", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            throw new Exception("Exception teacher '" + nameTeacher + "' '" + lastNameTeacher + "' was not found");
        }
        List<Group> l3 = session.createQuery("from Group g where g.name = :nameGroup")
                .setParameter("nameGroup", nameGroup).list();
        if (l3.size() == 0) {
            throw new Exception("Exception group '" + nameGroup + "' was not found");
        }
        List<GroupAndTeacher> l4 = session.createQuery("from GroupAndTeacher g where g.idTeacher = :teacher" +
                " and g.idGroup = :group").setParameter("teacher", l2.get(0).getId()).setParameter("group", l3.get(0).getId()).list();
        if (l4.size() == 0) {
            throw new Exception("Exception this relation was not found");
        }
        session.delete(l4.get(0));
        t.commit();
        session.close();
    }
    public GroupAndTeacher getRelation(String nameTeacher, String lastNameTeacher, String nameGroup) throws Exception {
        Session session = factory.openSession();
        List<User> l1 = session.createQuery("from User t where t.name = :name and t.lastName = :lastName")
                .setParameter("name", nameTeacher).setParameter("lastName", lastNameTeacher).list();
        if (l1.size() == 0) {
            throw new Exception("Exception user '" + nameTeacher + "' '" + lastNameTeacher + "' was not found");
        }
        List<Teacher> l2 = session.createQuery("from Teacher t where t.idUser = :user")
                .setParameter("user", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            throw new Exception("Exception teacher '" + nameTeacher + "' '" + lastNameTeacher + "' was not found");
        }
        List<Group> l3 = session.createQuery("from Group g where g.name = :nameGroup")
                .setParameter("nameGroup", nameGroup).list();
        if (l3.size() == 0) {
            throw new Exception("Exception group '" + nameGroup + "' was not found");
        }
        List<GroupAndTeacher> l4 = session.createQuery("from GroupAndTeacher g where g.idTeacher = :teacher" +
                " and g.idGroup = :group").setParameter("teacher", l2.get(0).getId()).setParameter("group", l3.get(0).getId()).list();
        session.close();
        if (l4.size() == 0) {
            return null;
        }
        else {
            return l4.get(0);
        }
    }
    public List<GroupAndTeacher> getAll() {
        Session session = factory.openSession();
        List<GroupAndTeacher> l = session.createQuery("from GroupAndTeacher").list();
        session.close();
        if (l.size() == 0) {
            return new ArrayList<GroupAndTeacher>();
        }
        else {
            return l;
        }
    }
}
