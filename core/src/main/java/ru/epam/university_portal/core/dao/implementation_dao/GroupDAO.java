package ru.epam.university_portal.core.dao.implementation_dao;

/**
 * Created by Владос on 08.05.2016.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.epam.university_portal.core.dao.interface_dao.IGroupDAO;
import ru.epam.university_portal.model.entity.Group;
import java.util.List;
@Repository
public class GroupDAO  implements IGroupDAO {
    public GroupDAO() {
        super();
    }
    Session session=null;
    private SessionFactory factory;
    @Autowired
    public GroupDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void create(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Group g = new Group();
        g.setName(name);
        session.save(g);
        t.commit();
        session.close();
    }

    public void remove(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l = session
                .createQuery("FROM Group g where g.name = :parameter")
                .setParameter("parameter", name).list();
        if (l.size() > 0) {
            for (Group g : l) {
                session.delete(g);
            }
            t.commit();
        }
        session.close();
    }

    public Group get(String name) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l = session
                .createQuery("FROM Group g where g.name = :parameter")
                        .setParameter("parameter", name).list();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }
    public Group get(int id) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l = session
                .createQuery("FROM Group g where g.name = :parameter")
                .setParameter("parameter", id).list();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }

    public List<Group> getAll(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l = session.createQuery("FROM Group").list();
        return l;
    }
}
