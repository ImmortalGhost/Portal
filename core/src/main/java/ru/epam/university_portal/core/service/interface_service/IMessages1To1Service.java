package ru.epam.university_portal.core.service.interface_service;

import ru.epam.university_portal.model.entity.Messages1To1;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IMessages1To1Service {

    void create(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception ;
    List<Messages1To1> getAll(String fromName, String fromLastName, String toName, String toLastName) throws Exception ;
    Messages1To1 get(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception;
    void removeAll(String fromName, String fromLastName, String toName, String toLastName) throws Exception ;
    void remove(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception ;



}
