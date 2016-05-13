package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.core.dao.interface_dao.INewsDAO;
import ru.epam.university_portal.core.service.interface_service.INewsService;
import ru.epam.university_portal.model.entity.News;

import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
public class NewsService implements INewsService{

    private INewsDAO newsDAO;

    @Autowired
    public NewsService (INewsDAO newsDAO){

        this.newsDAO=newsDAO;
    }

    public void create(String groupName, String newsName) throws Exception {
        newsDAO.create(groupName, newsName);
    }
    public  List<News> getAllByGroup(String groupName) throws Exception {
        return newsDAO.getAllByGroup(groupName);
    }
    public void removeAllByGroup(String groupName) throws Exception {
        newsDAO.removeAllByGroup(groupName);
    }
    public void removeNewsByGroupAndTitle(String groupName, String newsName) throws Exception {
        newsDAO.removeNewsByGroupAndTitle(groupName, newsName);
    }

}
