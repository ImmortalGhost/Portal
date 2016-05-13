package ru.epam.university_portal.core.dao.implementation_dao;

/**
 * Created by Владос on 08.05.2016.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.epam.university_portal.core.dao.interface_dao.IPostDAO;
import ru.epam.university_portal.model.entity.Post;
import java.util.List;

@Repository
public class PostDAO  implements IPostDAO {
    public PostDAO() {
        super();
    }
    Session session=null;
    private SessionFactory factory;
    @Autowired
    public PostDAO(SessionFactory factory) {
        this.factory = factory;
    }


    public void create(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Post p = new Post();
        p.setName(name);
        session.save(p);
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
                .createQuery("FROM Post p where p.name = :parameter")
                .setParameter("parameter", id).list();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }
    public List<Post> getAll(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Post> l = session.createQuery("FROM Post").list();
        return l;
    }
}
