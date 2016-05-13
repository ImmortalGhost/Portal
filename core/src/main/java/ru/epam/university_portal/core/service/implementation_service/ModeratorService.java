package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.interface_dao.IModeratorDAO;
import ru.epam.university_portal.core.service.interface_service.IModeratorService;
import ru.epam.university_portal.model.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
@Service
public class ModeratorService implements IModeratorService {

    private IModeratorDAO moderatorDAO;

    @Autowired
    public ModeratorService (IModeratorDAO moderatorDAO){

        this.moderatorDAO=moderatorDAO;
    }
    public void create (String login, String password) throws Exception {
        moderatorDAO.create(login, password);
    }
    public void createOrUpdate(String login, String password, String name, String lastName, Date date)
            throws Exception {
        moderatorDAO.createOrUpdate(login, password, name, lastName, date);
    }
    public  User get(String name, String lastName) {
      return   moderatorDAO.get(name, lastName);
    }
    public  User getByLoginAndPassword(String login, String password) {
      return   moderatorDAO.getByLoginAndPassword(login, password);
    }
    public List<User> getAll() throws Exception {
        return moderatorDAO.getAll();
    }
    public  void remove(String login, String password) throws Exception {
        moderatorDAO.remove(login, password);
    }

}
