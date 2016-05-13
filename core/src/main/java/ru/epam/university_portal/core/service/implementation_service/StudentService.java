package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.epam.university_portal.core.dao.interface_dao.IStudentDAO;
import ru.epam.university_portal.core.service.interface_service.IStudentService;
import ru.epam.university_portal.model.entity.Student;

import java.util.Date;
import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
public class StudentService implements IStudentService{

    private IStudentDAO studentDAO;

    @Autowired
    public  StudentService (IStudentDAO studentDAO){

        this. studentDAO= studentDAO;
    }

    public  void create (String login, String password, String nameGroup) throws Exception {
        studentDAO.create(login, password, nameGroup);
    }
    public void createOrUpdate(String login, String password, String nameGroup, String name, String lastName, Date date)
            throws Exception {
        studentDAO.createOrUpdate(login, password, nameGroup, name, lastName, date);
    }
    public  Student get(String name, String lastName) {
       return studentDAO.get(name, lastName);
    }
    public Student getByLoginAndPassword(String login, String password) {
       return studentDAO.getByLoginAndPassword(login, password);
    }
    public  List<Student> getAll() {
        return studentDAO.getAll();
    }
    public void addNewMessage(String fromName, String fromLastName, String toName, String toLastName, String message) throws Exception {
        studentDAO.addNewMessage(fromName, fromLastName, toName, toLastName, message);
    }
    public void clearNewMessages(String name, String lastName) throws Exception{
        studentDAO.clearNewMessages(name, lastName);
    }
    public  void clearNewMessagesByLoginAndPassword(String login, String password) throws Exception{
        studentDAO.clearNewMessagesByLoginAndPassword(login, password);
    }
    public  void remove(String login, String password) throws Exception {

        studentDAO.remove(login, password);
    }


}
