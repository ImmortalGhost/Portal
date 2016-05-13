package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.interface_dao.IGroupDAO;
import ru.epam.university_portal.core.service.interface_service.IGroupService;
import ru.epam.university_portal.model.entity.Group;

import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
@Service
public class GroupService implements IGroupService{

    private IGroupDAO groupDAO;

    @Autowired
    public GroupService (IGroupDAO groupDAO){

        this.groupDAO=groupDAO;
    }

    public  void create(String name){
        groupDAO.create(name);
    }

    public  void remove(String name){

        groupDAO.remove(name);

    }

    public   Group get(String name) {

        return groupDAO.get(name);
    }
    public  Group get(int id) {

        return groupDAO.get(id);
    }

    public   List<Group> getAll(){

        return groupDAO.getAll();
    }


}
