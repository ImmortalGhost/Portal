package ru.epam.university_portal.core.dao.impl;

import org.hibernate.annotations.NamedQuery;
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
@NamedQuery(name="findUserByLogin",

        query="select u from User u where u.login = :login")
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
          ///  session.get();
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

   /*/ public User getUser(){
        User user =null;
        Transaction t = null;
        Session session=null;
        try {
            session=sessionFactory.getCurrentSession();
            t = session.beginTransaction();
            user = (User) session.get("user",1);
            t.commit();
            System.out.println(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return user
    }/*/
  /*/ public User getUser(String login) {
       Transaction t = null;
       User user = null;
       Session session=null;
       try {
           session=sessionFactory.getCurrentSession();
           t = session.beginTransaction();
           Query query = session.getNamedQuery("findUserByLogin");
           query.setParameter("login", login);
           user = (User) query.uniqueResult();
           t.commit();
           System.out.println(user);
       } catch (HibernateException e) {
           e.printStackTrace();
       }
       return user;
   }/*/
   public User getUser(String login,String password) {
       Transaction t = null;
       Session session=null;

       User user = null;
       try {
// создание запроса к БД
           session =
                   sessionFactory.getCurrentSession();
           t = session.beginTransaction();
           Query query =
                   session.createQuery("from User a where a.login = :login");
           query.setParameter("login", login);

           user = (User) query.uniqueResult();
           t.commit();
       } catch (HibernateException e) {
           e.printStackTrace();
           t.rollback();
         //  ConnectionFactory.destroy();
       }
       return user;
   }
    /*/
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
    }/*/
}
