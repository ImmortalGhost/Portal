package ru.epam.university_portal.core.dao.interface_dao;

import org.hibernate.Transaction;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.News;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface INewsDAO {

    void create(String groupName, String newsName) throws Exception ;
     List<News> getAllByGroup(String groupName) throws Exception ;
     void removeAllByGroup(String groupName) throws Exception ;
     void removeNewsByGroupAndTitle(String groupName, String newsName) throws Exception ;


}
