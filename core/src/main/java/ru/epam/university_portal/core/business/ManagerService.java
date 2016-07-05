package ru.epam.university_portal.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.epam.university_portal.core.dao.*;
import ru.epam.university_portal.model.entity.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Владос on 06.06.2016.
 */
public class ManagerService {
    @Autowired
    GroupDAO groupDAO;
    @Autowired
    Group group;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    TeacherDAO teacherDAO;
    @Autowired
    NewsDAO newsDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    User user;
    @Autowired
    Student student;
    @Autowired
    Messages1To1DAO messages1To1DAO;
    @Autowired
    ManagerDAO managerDAO;
    @Autowired
    MessagesFromNewsDAO messagesFromNewsDAO;
    public List<Group> getAllGroups() {
        return groupDAO.getAll();
    }
    public List<String> getAllGroupNames() {
        return groupDAO.getAllNames();
    }
    public List<Student> getAllStudentsFromGroupByNameGroup(String groupName) {
        group = groupDAO.get(groupName);
        List<Student> students = studentDAO.getAll();
        List<Student> result = new ArrayList<Student>();
        for (Student s : students) {
            if (s.getIdGroup() == group.getId()) {
                result.add(s);
            }
        }
        return result;
    }
    public List<String> getAllStudentNamesFromGroupByNameGroup(String groupName) {
        List<Student> studentList = getAllStudentsFromGroupByNameGroup(groupName);
        List<String> result = new ArrayList<String>();
        for (Student s : studentList) {
            result.add(studentDAO.getName(s) + " " + studentDAO.getLastName(s));
        }
        return result;
    }
    public List<Teacher> getAllTeachers() {
        return teacherDAO.getAll();
    }
    public List<String> getAllTeacherNames() {
        return teacherDAO.getAllNames();
    }
    public void addNewsInGroup(String groupName, String newsName) {
        try {
            newsDAO.create(groupName, newsName);
        }
        catch (Exception e) {
            //
        }
    }
    public List<News> getAllNewsByNameGroup(String groupName) {
        return newsDAO.getAllByGroup(groupName);
    }
    public List<String> getAllNewsNamesByNameGroup(String groupName) { return newsDAO.getAllNamesByGroup(groupName);

    }
    public User getUserInformationByNameAndLastName(String name, String lastName) {
        user = userDAO.get(name, lastName);
        return user;
    }
    public Student getStudentInformationByNameAndLastName(String name, String lastName) {
        student = studentDAO.get(name, lastName);
        return student;
    }
    public List<Messages1To1> getAllMessageBetweenFromAndTo(String nameFrom, String lastNameFrom, String nameTo, String lastNameTo){
        return messages1To1DAO.getAll(nameFrom, lastNameFrom, nameTo, lastNameTo);
    }
    public List<String> getAllMessageTextBetweenFromAndTo(String nameFrom, String latNameFrom, String nameTo, String lastNameTo) {
        List<Messages1To1> list = getAllMessageBetweenFromAndTo(nameFrom, latNameFrom, nameTo, lastNameTo);
        List<String> result = new ArrayList<String>();
        for (Messages1To1 m : list) {
            result.add(m.getMessage());
        }
        return result;
    }
    public void sendMessageFromAndTo(String nameFrom, String lastNameFrom, String nameTo, String lastNameTo, String message) {
        Messages1To1DAO messages1To1DAO = new Messages1To1DAO();
        try {
            messages1To1DAO.create(nameFrom, lastNameFrom, nameTo, lastNameTo, message);
        }
        catch(Exception e) {
            //
        }
    }
//    public static void createNewChatFromAndTo(String name, String lastName, String nameSubject, String lastNameSubject) {}
    public Teacher getTeacherInformationByNameAndLastName(String name, String lastName) {
        return teacherDAO.get(name, lastName);
    }
    public void saveChangesManager(String name, String lastName, Date age, String login, String password) {
        try {
            managerDAO.createOrUpdate(login, password, name, lastName, age);
        }
        catch (Exception e) {}
    }
    public List<MessagesFromNews> getAllMessagesFromNewsByNameNewsAndNameGroup(String nameNews, String nameGroup) {
        try {
            return messagesFromNewsDAO.getAllByGroupAndTitle(nameNews, nameGroup);
        }
        catch(Exception e) {
            return new ArrayList<MessagesFromNews>();
        }
    }
    public List<String> getAllMessagesTextFromNewsAndNameGroup(String nameNews, String nameGroup) {
        List<MessagesFromNews> list = getAllMessagesFromNewsByNameNewsAndNameGroup(nameNews, nameGroup);
        List<String> result = new ArrayList<String>();
        for (MessagesFromNews m : list) {
            result.add(m.getMessage());
        }
        return result;
    }
    public void sendMessageToNews(String nameFrom, String lastNameFrom, String message, String nameNews, String groupName) {
        try {
            messagesFromNewsDAO.create(nameFrom, lastNameFrom, nameNews, groupName, message);
        }
        catch (Exception e) {}
    }
    public String[] getEnteredUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        user = managerDAO.getByLoginAndPassword(login, password);
        String[] result = new String[2];
        result[0] = user.getName();
        result[1] = user.getLastName();
        return result;
    }
    public void signIn(String name, String lastName, HttpSession session) {
        session.setAttribute("name", name);
        session.setAttribute("lastName", lastName);
        session.setAttribute("role", "Manager");
    }
    public void logOut(HttpSession session) {
        session.setAttribute("name", "");
        session.setAttribute("lastName", "");
        session.setAttribute("role", "");
    }
//    public static List<User> getInterlocutorsForUser(String name, String lastName) {}
    public void editMessageFromNews(String nameSayer, String lastNameSayer, String nameNews, String groupName, String oldMessage, String newMessage) {
        try {
            messagesFromNewsDAO.update(nameSayer, lastNameSayer, nameNews, groupName, oldMessage, newMessage);
        }
        catch(Exception e) {
            //
        }
    }
    public void renameNews(String groupName, String oldName, String newName) {
        newsDAO.rename(groupName, oldName, newName);
    }
    public void deleteNews(String groupName, String nameNews) {
        try {
            newsDAO.removeNewsByGroupAndTitle(groupName, nameNews);
        }
        catch (Exception e) {
            //
        }
    }
    public void deleteMessageFromNews(String nameFrom, String lastNameFrom, String groupName, String nameNews, String message) {
        try {
            messagesFromNewsDAO.remove(nameFrom, lastNameFrom, groupName, nameNews, message);
        }
        catch(Exception e) {
            //
        }
    }
    public String getAgeString(Date age) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(age);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String dayS = "";
        String monthS = "";
        if (Integer.toString(day).length() == 1) {
            dayS += "0";
        }
        if (Integer.toString(month).length() == 1) {
            monthS += "0";
        }
        dayS += day;
        monthS += month;
        String result = year + "-" + monthS + "-" + dayS;
        return result;
    }
}
