package ru.epam.university_portal.core.dao.interface_dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.epam.university_portal.model.entity.Messages1To1;
import ru.epam.university_portal.model.entity.User;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IMessages1To1DAO {

    void create(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception ;
    List<Messages1To1> getAll(String fromName, String fromLastName, String toName, String toLastName) throws Exception ;
     Messages1To1 get(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception;
     void removeAll(String fromName, String fromLastName, String toName, String toLastName) throws Exception ;
     void remove(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception ;


}
