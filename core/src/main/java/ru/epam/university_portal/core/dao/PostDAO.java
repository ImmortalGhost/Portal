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
import ru.epam.university_portal.model.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class PostDAO extends BaseDAO {
    @Autowired
    Post post;
    public void create(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        post.setName(name);
        session.save(post);
        t.commit();
        session.close();
    }
    public void remove(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Post> l = session
                .createQuery("FROM Post p where p.name = :parameter")
                .setParameter("parameter", name).list();
        if (l.size() > 0) {
            for (Post g : l) {
                session.delete(g);
            }
            t.commit();
        }
        session.close();
    }
    public Post get(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Post> l = session
                .createQuery("FROM Post p where p.name = :parameter")
                .setParameter("parameter", name).list();
        session.close();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }
    public Post get(int id) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Post> l = session
                .createQuery("FROM Post p where p.id = :parameter")
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
        post = get(id);
        return post.getName();
    }
    public List<Post> getAll(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Post> l = session.createQuery("FROM Post").list();
        session.close();
        return l;
    }
    public List<String> getAllNames() {
        List<Post> postList = getAll();
        List<String> result = new ArrayList<String>();
        for (Post p : postList) {
            result.add(p.getName());
        }
        return result;
    }
}
