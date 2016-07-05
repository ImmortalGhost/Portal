package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владос on 10.05.2016.
 */
public class NewsDAO extends BaseDAO {
    @Autowired
    News news;
    @Autowired
    GroupDAO groupDAO;
    @Autowired
    Group group;
    public void create(String groupName, String newsName) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l1 = session.createQuery("from Group g where g.name = :name").setParameter("name", groupName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - group '" + groupName + "' was not found");
        }
        List<Group> l2 = session.createQuery("from News g where g.name = :nameNews and g.idGroup = :idGroup")
                .setParameter("nameNews", newsName).setParameter("idGroup", l1.get(0).getId()).list();
        if (l2.size() == 0) {
            news.setName(newsName);
            news.setIdGroup(l1.get(0).getId());
            session.save(news);
            t.commit();
        }
        session.close();
    }
    public List<News> getAllByGroup(String groupName) {
        Session session = factory.openSession();
        List<Group> l1 = session.createQuery("from Group n where n.name = :name").setParameter("name", groupName).list();
        if (l1.size() == 0) {
            return new ArrayList<News>();
        }
        List<News> l2 = session.createQuery("from News n where n.idGroup = :id").setParameter("id", l1.get(0).getId()).list();
        session.close();
        return l2;
    }
    public List<String> getAllNamesByGroup(String groupName) {
        List<News> newsList = getAllByGroup(groupName);
        List<String> result = new ArrayList<String>();
        for (News n : newsList) {
            result.add(n.getName());
        }
        return result;
    }
    public News get(String name, String groupName) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        group = groupDAO.get(groupName);
        List<News> l = session
                .createQuery("FROM News p where p.name = :parameter and p.idGroup = :idGroup")
                .setParameter("parameter", name).setParameter("idGroup", group.getId()).list();
        session.close();
        if (l.size() > 0) {
            return l.get(0);
        }
        else {
            return null;
        }
    }
    public void rename(String groupName, String oldName, String newName) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        news = get(groupName, oldName);
        news.setName(newName);
        session.saveOrUpdate(news);
        transaction.commit();
        session.close();
    }
    public void removeAllByGroup(String groupName) throws Exception {
        Session session = factory.openSession();
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
        Session session = factory.openSession();
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
