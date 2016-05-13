package ru.epam.university_portal.core.dao.interface_dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.epam.university_portal.model.entity.Post;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IPostDAO {


    void create(String name) ;

     void remove(String name) ;

     Post get(String name) ;
     Post get(int id) ;
    List<Post> getAll();


}
