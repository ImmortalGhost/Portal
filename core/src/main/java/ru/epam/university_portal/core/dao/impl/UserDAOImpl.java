package ru.epam.university_portal.core.dao.impl;

import ru.epam.university_portal.core.dao.IUserDAO;

import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import ru.epam.university_portal.model.entity.User;
/**
 * Created by maksim on 02.05.16.
 */
@Repository
public class UserDAOImpl implements IUserDAO {
    private Session session;

    private SessionFactory sessionFactory;

    public UserDAOImpl(Session session) {
        this.session = session;
    }
    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void addUser(User user) {
        Transaction t = null;
        Session session=null;
        try {
            session=sessionFactory.getCurrentSession();
            t = session.beginTransaction();
            session.save(user);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
		/*/try {
			t = session.beginTransaction();
			session.save(student);
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			t.rollback();
		}/*/
    }

    public User getUser(String lastName) {
        User student = null;
        try {
            Query query = session.getNamedQuery("findStudentByLastName");
            query.setParameter("lastName", lastName);
            student = (User) query.uniqueResult();
            System.out.println(student);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return student;
    }
}
