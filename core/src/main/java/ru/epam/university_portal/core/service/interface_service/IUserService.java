package ru.epam.university_portal.core.service.interface_service;

import ru.epam.university_portal.model.entity.User;

/**
 * Created by maksim on 02.05.16.
 */
public interface IUserService {
    void addUser(User user);
    User getUser(String login,String pasword);
}
