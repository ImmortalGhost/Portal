package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.interface_dao.IMessages1To1DAO;
import ru.epam.university_portal.core.service.interface_service.IMessages1To1Service;
import ru.epam.university_portal.model.entity.Messages1To1;

import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
@Service
public class Messages1To1Service implements IMessages1To1Service {

    private IMessages1To1DAO messages1To1DAO;

    @Autowired
    public Messages1To1Service (IMessages1To1DAO messages1To1DAO){

        this.messages1To1DAO=messages1To1DAO;
    }

    public  void create(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception {
        messages1To1DAO.create(fromName,fromLastName,toName,toLastName,message);
    }
    public  List<Messages1To1> getAll(String fromName, String fromLastName, String toName, String toLastName) throws Exception {
        return    messages1To1DAO.getAll(fromName,fromLastName,toName,toLastName);
    }
    public  Messages1To1 get(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception{
     return    messages1To1DAO.get(fromName,fromLastName,toName,toLastName,message);
    }
    public   void removeAll(String fromName, String fromLastName, String toName, String toLastName) throws Exception {
        messages1To1DAO.removeAll(fromName,fromLastName,toName,toLastName);
    }
    public  void remove(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception {
        messages1To1DAO.remove(fromName,fromLastName,toName,toLastName,message);
    }



}
