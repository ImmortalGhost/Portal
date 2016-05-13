package ru.epam.university_portal.core.service.interface_service;

import ru.epam.university_portal.model.entity.News;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface INewsService {

    void create(String groupName, String newsName) throws Exception ;
    List<News> getAllByGroup(String groupName) throws Exception ;
    void removeAllByGroup(String groupName) throws Exception ;
    void removeNewsByGroupAndTitle(String groupName, String newsName) throws Exception ;



}
