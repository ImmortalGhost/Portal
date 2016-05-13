package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.core.dao.interface_dao.IPostDAO;
import ru.epam.university_portal.core.service.interface_service.INewsService;
import ru.epam.university_portal.core.service.interface_service.IPostService;
import ru.epam.university_portal.model.entity.Post;

import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
public class PostService implements IPostService {

    private IPostDAO  postDAO;

    @Autowired
    public  PostService (IPostDAO postDAO){

        this. postDAO= postDAO;
    }

    public void create(String name) {
        postDAO.create(name);
    }

    public void remove(String name) {
        postDAO.remove(name);
    }
    public  Post get(String name) {
     return    postDAO.get(name);
    }
    public Post get(int id) {
     return    postDAO.get(id);
    }
    public List<Post> getAll(){
      return   postDAO.getAll();
    }

}
