package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.MessagesFromNews;
import ru.epam.university_portal.model.entity.News;
import ru.epam.university_portal.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владос on 10.05.2016.
 */
public class MessagesFromNewsDAO extends BaseDAO {
    @Autowired
    MessagesFromNews messagesFromNews;
    @Autowired
    UserDAO userDAO;
    @Autowired
    NewsDAO newsDAO;
    @Autowired
    User user;
    @Autowired
    News news;
    public void create(String fromName, String fromLastName, String newsName, String groupName, String message) throws Exception{
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Group> l0 = session.createQuery("from Group g where g.name = :title")
                .setParameter("title", groupName).list();
        if (l0.size() == 0) {
            throw new Exception("Exception - group '" + groupName + "' was not found");
        }
        List<User> l1 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
        if (l1.size() == 0) {
            throw new Exception("Exception - user '" + fromName + "' '" + fromLastName + "' was not found");
        }
        List<News> l2 = session.createQuery("from News n where n.name = :name and n.idGroup = :group")
                .setParameter("name", newsName).setParameter("group", l0.get(0).getId()).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - news '" + newsName + "' was not found");
        }
        messagesFromNews.setIdFrom(l1.get(0).getId());
        messagesFromNews.setIdNews(l2.get(0).getId());
        messagesFromNews.setMessage(message);
        session.save(messagesFromNews);
        t.commit();
        session.close();
    }
    public void update(String fromName, String fromLastName, String newsName, String groupName, String oldMessage, String newMessage) throws Exception {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        user = userDAO.get(fromName, fromLastName);
        List<MessagesFromNews> messagesFromNewses = getAllByGroupAndTitle(groupName, newsName);
        for (MessagesFromNews m : messagesFromNewses) {
            if (m.getMessage().equals(oldMessage) && m.getIdFrom() == user.getId()) {
                m.setMessage(newMessage);
                session.saveOrUpdate(m);
                break;
            }
        }
        transaction.commit();
        session.close();
    }
    public List<MessagesFromNews> getAllByGroupAndTitle(String groupName, String newsName) throws Exception {
        Session session = factory.openSession();
        List<News> l1 = newsDAO.getAllByGroup(groupName);
        News news = null;
        for (News n : l1) {
            if (n.getName().equals(newsName)) {
                news = n;
            }
        }
        if (news == null) {
            session.close();
            return new ArrayList<MessagesFromNews>();
        }
        else {
            List<MessagesFromNews> l2 = session.createQuery("from MessagesFromNews m where m.idNews = :idNews")
                    .setParameter("idNews", news.getId()).list();
            session.close();
            return l2;
        }
    }
    public void removeAllByGroupAndTitle(String groupName, String newsName) throws Exception {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<News> l1 = newsDAO.getAllByGroup(groupName);
        News news = null;
        for (News n : l1) {
            if (n.getName().equals(newsName)) {
                news = n;
            }
        }
        if (news == null) {
            session.close();
        }
        else {
            List<MessagesFromNews> l2 = session.createQuery("from MessagesFromNews m where m.idNews = :idNews")
                    .setParameter("idNews", news.getId()).list();
            for (MessagesFromNews m : l2) {
                session.delete(m);
            }
            t.commit();
            session.close();
        }
    }
    public void remove(String fromName, String fromLastName, String groupName, String newsName, String message) throws Exception{
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<News> l1 = newsDAO.getAllByGroup(groupName);
        news = null;
        for (News n : l1) {
            if (n.getName().equals(newsName)) {
                news = n;
            }
        }
        if (news == null) {
            throw new Exception("Exception - news '" + newsName + "' does not found");
        }
        List<User> l2 = session.createQuery("from User u where u.name = :name and u.lastName = :lastName")
                .setParameter("name", fromName).setParameter("lastName", fromLastName).list();
        if (l2.size() == 0) {
            throw new Exception("Exception - user '" + fromName + "' '" + fromLastName + "' does not found");
        }
        List<MessagesFromNews> l3 = session.createQuery("from MessagesFromNews m where m.idNews = :idNews " +
                "and m.idFrom = :idFrom and m.message = :message")
                .setParameter("idNews", news.getId()).setParameter("idFrom", l2.get(0).getId())
                .setParameter("message", message).list();
        for (MessagesFromNews m : l3) {
            session.delete(m);
        }
        t.commit();
        session.close();
    }
}
