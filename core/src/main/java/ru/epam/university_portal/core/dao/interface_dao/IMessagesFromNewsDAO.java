package ru.epam.university_portal.core.dao.interface_dao;

import org.hibernate.Transaction;
import ru.epam.university_portal.core.dao.impl.NewsDAO;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.MessagesFromNews;
import ru.epam.university_portal.model.entity.News;
import ru.epam.university_portal.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IMessagesFromNewsDAO {

   void create(String fromName, String fromLastName, String newsName, String groupName, String message) throws Exception;
     List<MessagesFromNews> getAllByGroupAndTitle(String groupName, String newsName) throws Exception ;
     void removeAllByGroupAndTitle(String groupName, String newsName) throws Exception ;
    void remove(String fromName, String fromLastName, String groupName, String newsName, String message) throws Exception;


}
