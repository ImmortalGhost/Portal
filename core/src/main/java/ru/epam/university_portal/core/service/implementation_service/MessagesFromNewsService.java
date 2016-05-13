package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.interface_dao.IMessagesFromNewsDAO;
import ru.epam.university_portal.core.service.interface_service.IMessagesFromNewsService;
import ru.epam.university_portal.model.entity.MessagesFromNews;

import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */

@Service
public class MessagesFromNewsService implements IMessagesFromNewsService{

    private IMessagesFromNewsDAO messagesFromNewsDAO;

    @Autowired
    public MessagesFromNewsService (IMessagesFromNewsDAO messagesFromNewsDAO){

        this.messagesFromNewsDAO=messagesFromNewsDAO;
    }

    public  void create(String fromName, String fromLastName, String newsName, String groupName, String message) throws Exception{
        messagesFromNewsDAO.create(fromName,fromLastName,newsName,groupName,message);
    }
    public  List<MessagesFromNews> getAllByGroupAndTitle(String groupName, String newsName) throws Exception {
        return messagesFromNewsDAO.getAllByGroupAndTitle(groupName,newsName);
    }
    public   void removeAllByGroupAndTitle(String groupName, String newsName) throws Exception {
        messagesFromNewsDAO.removeAllByGroupAndTitle(groupName,newsName);
    }
    public  void remove(String fromName, String fromLastName, String groupName, String newsName, String message) throws Exception{
        messagesFromNewsDAO.remove(fromName,fromLastName,groupName,newsName,message);
    }


}
