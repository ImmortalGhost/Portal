package ru.epam.university_portal.core.service.interface_service;

import ru.epam.university_portal.model.entity.Group;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IGroupService {

    void create(String name);

    void remove(String name);

    Group get(String name) ;
    Group get(int id) ;

    List<Group> getAll();


}
