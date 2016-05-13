package ru.epam.university_portal.core.dao.interface_dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.epam.university_portal.core.dao.impl.GroupDAO;
import ru.epam.university_portal.core.dao.impl.Messages1To1DAO;
import ru.epam.university_portal.core.dao.impl.RoleDAO;
import ru.epam.university_portal.model.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IStudentDAO {

     void create (String login, String password, String nameGroup) throws Exception ;
     void createOrUpdate(String login, String password, String nameGroup, String name, String lastName, Date date)
            throws Exception ;
     Student get(String name, String lastName) ;
     Student getByLoginAndPassword(String login, String password) ;
     List<Student> getAll() ;
     void addNewMessage(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception ;
     void clearNewMessages(String name, String lastName) throws Exception;
     void clearNewMessagesByLoginAndPassword(String login, String password) throws Exception;
     void remove(String login, String password) throws Exception ;

}
