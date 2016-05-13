package ru.epam.university_portal.core.dao.implementation_dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.epam.university_portal.core.dao.interface_dao.INewsDAO;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.News;

import java.util.List;

/**
 * Created by Владос on 10.05.2016.
 */

@Repository
public class NewsDAO extends BaseDAO implements INewsDAO{
    public NewsDAO() {
        super();
    }

    private SessionFactory sessionFactory;
    @Autowired
    public NewsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void create(String groupName, String newsName) throws Exception {
        session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l1 = session.createQuery("from Group g where g.name = :name").setParameter("name", groupName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - group '" + groupName + "' was not found");
        }
        List<Group> l2 = session.createQuery("from News g where g.name = :nameNews and g.idGroup = :idGroup")
                .setParameter("nameNews", newsName).setParameter("idGroup", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            News news = new News();
            news.setName(newsName);
            news.setIdGroup(l1.get(0).getId());
            session.save(news);
            t.commit();
        }
        session.close();
    }
    public List<News> getAllByGroup(String groupName) throws Exception {
        session = factory.openSession();
        List<Group> l1 = session.createQuery("from Group n where n.name = :name").setParameter("name", groupName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - group '" + groupName + "' was not found");
        }
        List<News> l2 = session.createQuery("from News n where n.idGroup = :id").setParameter("id", l1.get(0).getId()).list();
        session.close();
        return l2;
    }
    public void removeAllByGroup(String groupName) throws Exception {
        session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l1 = session.createQuery("from Group n where n.name = :name").setParameter("name", groupName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - group '" + groupName + "' was not found");
        }
        List<News> l2 = session.createQuery("from News n where n.idGroup = :id").setParameter("id", l1.get(0).getId()).list();
        for (News n : l2) {
            session.delete(n);
        }
        t.commit();
        session.close();
    }
    public void removeNewsByGroupAndTitle(String groupName, String newsName) throws Exception {
        session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l1 = session.createQuery("from Group n where n.name = :name").setParameter("name", groupName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - group '" + groupName + "' was not found");
        }
        List<News> l2 = session.createQuery("from News n where n.idGroup = :id and n.name = :name")
                .setParameter("id", l1.get(0).getId()).setParameter("name", newsName).list();
        for (News n : l2) {
            session.delete(n);
        }
        t.commit();
        session.close();
    }
}
