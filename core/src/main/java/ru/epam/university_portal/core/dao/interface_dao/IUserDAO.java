package ru.epam.university_portal.core.dao.interface_dao;

import ru.epam.university_portal.model.entity.User;

/**
 * Created by maksim on 12.05.16.
 */
public interface IUserDAO {

    void addUser(User user);

    User getUser(String login,String password);

}
