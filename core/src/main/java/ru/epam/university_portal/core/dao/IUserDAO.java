package ru.epam.university_portal.core.dao;

/**
 * Created by maksim on 02.05.16.
 */
import ru.epam.university_portal.model.entity.User;

public interface IUserDAO {
    void addUser(User user);

    User getUser(String lastName);
}