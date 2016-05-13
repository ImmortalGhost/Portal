package ru.epam.university_portal.core.dao.interface_dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.epam.university_portal.model.entity.Role;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IRoleDAO {

     void create(String name) ;

     void remove(String name) ;

     Role get(String name) ;

     Role get(int id) ;

     List<Role> getAll();

}
