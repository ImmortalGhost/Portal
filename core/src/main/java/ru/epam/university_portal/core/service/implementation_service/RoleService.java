package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.core.dao.interface_dao.IRoleDAO;
import ru.epam.university_portal.core.service.interface_service.IRoleService;
import ru.epam.university_portal.model.entity.Role;

import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
public class RoleService implements IRoleService{

    private IRoleDAO roleDAO;

    @Autowired
    public  RoleService (IRoleDAO roleDAO){

        this. roleDAO= roleDAO;
    }

    public void create(String name) {
        roleDAO.create(name);
    }

    public  void remove(String name) {

        roleDAO.remove(name);
    }

    public  Role get(String name) {
      return   roleDAO.get(name);
    }

    public  Role get(int id) {
      return   roleDAO.get(id);
    }

    public  List<Role> getAll(){
      return   roleDAO.getAll();
    }

}
