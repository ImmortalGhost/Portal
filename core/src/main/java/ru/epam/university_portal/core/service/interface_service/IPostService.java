package ru.epam.university_portal.core.service.interface_service;

import ru.epam.university_portal.model.entity.Post;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IPostService {

    void create(String name) ;

    void remove(String name) ;

    Post get(String name) ;
    Post get(int id) ;
    List<Post> getAll();


}
