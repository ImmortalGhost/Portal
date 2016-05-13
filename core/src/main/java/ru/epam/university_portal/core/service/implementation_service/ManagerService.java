package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.interface_dao.IManagerDAO;
import ru.epam.university_portal.core.service.interface_service.IManagerService;
import ru.epam.university_portal.model.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */

@Service
public class ManagerService implements IManagerService{

    private IManagerDAO managerDAO;

    @Autowired
    public ManagerService (IManagerDAO managerDAO){

        this.managerDAO=managerDAO;
    }
    public  void create (String login, String password) throws Exception{
        managerDAO.create(login,password);
    }
    public  void createOrUpdate(String login, String password, String name, String lastName, Date date)  throws Exception {
        managerDAO.createOrUpdate(login,password,name,lastName,date);
    }
    public  User get(String name, String lastName) {
       return managerDAO.get(name,lastName);
    }
    public  User getByLoginAndPassword(String login, String password) {
        return managerDAO.getByLoginAndPassword(login,password);
    }
    public   List<User> getAll() throws Exception {
        return managerDAO.getAll();
    }
    public   void remove(String login, String password) throws Exception {
        managerDAO.remove(login,password);
    }


}
