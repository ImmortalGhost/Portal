package ru.epam.university_portal.core.service.interface_service;

import ru.epam.university_portal.model.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IModeratorService {

    void create (String login, String password) throws Exception ;
    void createOrUpdate(String login, String password, String name, String lastName, Date date)
            throws Exception ;
    User get(String name, String lastName) ;
    User getByLoginAndPassword(String login, String password) ;
    List<User> getAll() throws Exception ;
    void remove(String login, String password) throws Exception ;


}
