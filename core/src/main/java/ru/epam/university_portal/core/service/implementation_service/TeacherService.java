package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.core.dao.interface_dao.ITeacherDAO;
import ru.epam.university_portal.core.service.interface_service.ITeacherService;
import ru.epam.university_portal.model.entity.Teacher;

import java.util.Date;

/**
 * Created by maksim on 13.05.16.
 */
public class TeacherService implements ITeacherService{

    private ITeacherDAO teacherDAO;

    @Autowired
    public  TeacherService (ITeacherDAO teacherDAO){

        this. teacherDAO= teacherDAO;
    }

    public void create (String login, String password, String namePost) throws Exception {
        teacherDAO.create(login, password, namePost);
    }
    public  void createOrUpdate(String login, String password, String namePost, String name, String lastName, Date date)
            throws Exception {
        teacherDAO.createOrUpdate(login, password, namePost, name, lastName, date);
    }
    public Teacher get(String name, String lastName) {
       return teacherDAO.get(name, lastName);
    }
    public  Teacher getByLoginAndPassword(String login, String password) {
        return teacherDAO.getByLoginAndPassword(login, password);
    }
    public void addNewMessage(String fromName, String fromLastName, String toNameTeacher, String toLastNameTeacher, String message) throws Exception {
        teacherDAO.addNewMessage(fromName, fromLastName, toNameTeacher, toLastNameTeacher, message);
    }
    public  void clearNewMessages(String name, String lastName) throws Exception{
        teacherDAO.clearNewMessages(name, lastName);
    }
    public void clearNewMessagesByLoginAndPassword(String login, String password) throws Exception{
        teacherDAO.clearNewMessagesByLoginAndPassword(login, password);
    }
    public void remove(String login, String password) throws Exception {
        teacherDAO.remove(login, password);
    }

}
