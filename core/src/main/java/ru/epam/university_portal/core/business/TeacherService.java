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
public class TeacherService {
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
    @Autowired
    Teacher teacher;
    @Autowired
    PostDAO postDAO;
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
    public List<String> getAllNewsNamesByNameGroup(String groupName) { return newsDAO.getAllNamesByGroup(groupName);

    }
    public Student getStudentInformationByNameAndLastName(String name, String lastName) {
        student = studentDAO.get(name, lastName);
        return student;
    }
    public List<Messages1To1> getAllMessageBetweenFromAndTo(String nameFrom, String lastNameFrom, String nameTo, String lastNameTo){
        return messages1To1DAO.getAll(nameFrom, lastNameFrom, nameTo, lastNameTo);
    }
    public void sendMessageFromAndTo(String nameFrom, String lastNameFrom, String nameTo, String lastNameTo, String message) {
        try {
            messages1To1DAO.create(nameFrom, lastNameFrom, nameTo, lastNameTo, message);
        }
        catch(Exception e) {
            //
        }
    }
    public void saveChangesTeacher(String name, String lastName, Date age, String group, String login, String password) {
        try {
            teacherDAO.createOrUpdate(login, password, group, name, lastName, age);
        }
        catch (Exception e) {
        }
    }
    public List<MessagesFromNews> getAllMessagesFromNewsByNameNewsAndNameGroup(String nameNews, String nameGroup) {
        try {
            return messagesFromNewsDAO.getAllByGroupAndTitle(nameGroup, nameNews);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<MessagesFromNews>();
        }
    }
    public void sendMessageToNews(String nameFrom, String lastNameFrom, String message, String nameNews, String groupName) {
        try {
            messagesFromNewsDAO.create(nameFrom, lastNameFrom, nameNews, groupName, message);
        }
        catch (Exception e) {}
    }
    public void signIn(String name, String lastName, HttpSession session) {
        session.setAttribute("name", name);
        session.setAttribute("lastName", lastName);
        session.setAttribute("role", "Teacher");
    }
    public void logOut(HttpSession session) {
        session.setAttribute("name", "");
        session.setAttribute("lastName", "");
        session.setAttribute("role", "");
    }
    public List<User> getInterlocutorsForUser(String name, String lastName) {
        try {
            return teacherDAO.getInterlocutors(name, lastName);
        }
        catch(Exception e) {
            return new ArrayList<User>();
        }
    }
    public List<String> getInterlocutorsNamesForUser(String name, String lastName) {
        List<User> list = getInterlocutorsForUser(name, lastName);
        List<String> result = new ArrayList<String>();
        for (User u : list) {
            result.add(u.getName() + " " + u.getLastName());
        }
        return result;
    }
    public String getName(Teacher t) {
        return teacherDAO.getName(t);
    }
    public String getLastName(Teacher t) {
        return teacherDAO.getLastName(t);
    }
    public String getPostName(Teacher t) {
        Post post = postDAO.get(t.getIdPost());
        return post.getName();
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
    public String getAge(Teacher t) {
        user = userDAO.get(getName(t), getLastName(t));
        return getAgeString(user.getAge());
    }
    public String printAge(Teacher t) {
        User user = userDAO.get(getName(t), getLastName(t));
        System.out.println((student == null) + " " + (user == null) + " " + getName(t) + " " + getLastName(t));
        String result = getAgeString(user.getAge());
        String[] date = result.split("-");
        result = "";
        for (int i = date.length - 1; i >= 0; i--) {
            result += date[i] + ".";
        }
        result = result.substring(0, result.lastIndexOf("."));
        return result;
    }
    public String getLogin(Teacher t) {
        user = userDAO.get(getName(t), getLastName(t));
        return user.getLogin();
    }
    public String getPassword(Teacher t) {
        user = userDAO.get(getName(t), getLastName(t));
        return user.getPassword();
    }
    public User getUser(int id) {
        return userDAO.get(id);
    }
    public void addNewInterlocutor(String name, String lastName, String nameSubject, String lastNameSubject) {
        try {
            List<User> userList = teacherDAO.getInterlocutors(name, lastName);
            for (User u : userList) {
                if (u.getName().equals(nameSubject) && u.getLastName().equals(lastNameSubject)) {
                    return;
                }
            }
            teacherDAO.addInterlocutor(name, lastName, nameSubject, lastNameSubject);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public List<String> getAllPostNames() {
        List<Post> list = postDAO.getAll();
        List<String> result = new ArrayList<String>();
        for (Post p : list) {
            result.add(p.getName());
        }
        return result;
    }

    public List<Group> getAllGroups() {
        return groupDAO.getAll();
    }
    public List<Teacher> getAllTeachers() {
        return teacherDAO.getAll();
    }
    public List<News> getAllNewsByNameGroup(String groupName) {
        return newsDAO.getAllByGroup(groupName);
    }
    public List<String> getAllMessageTextBetweenFromAndTo(String nameFrom, String latNameFrom, String nameTo, String lastNameTo) {
        List<Messages1To1> list = getAllMessageBetweenFromAndTo(nameFrom, latNameFrom, nameTo, lastNameTo);
        List<String> result = new ArrayList<String>();
        for (Messages1To1 m : list) {
            result.add(m.getMessage());
        }
        return result;
    }
    public void createNewChatFromAndTo(String name, String lastName, String nameSubject, String lastNameSubject) {
        try {
            if (!studentDAO.InterlocutorIsExists(name, lastName, nameSubject, lastNameSubject)) {
                studentDAO.addInterlocutor(name, lastName, nameSubject, lastNameSubject);
            }
        }
        catch(Exception e) {
            //
        }
    }
    public Teacher getTeacherInformationByNameAndLastName(String name, String lastName) {
        return teacherDAO.get(name, lastName);
    }
    public List<String> getAllMessagesTextFromNewsAndNameGroup(String nameNews, String nameGroup) {
        List<MessagesFromNews> list = getAllMessagesFromNewsByNameNewsAndNameGroup(nameNews, nameGroup);
        List<String> result = new ArrayList<String>();
        for (MessagesFromNews m : list) {
            result.add(m.getMessage());
        }
        return result;
    }
    public String[] getEnteredUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        teacher = teacherDAO.getByLoginAndPassword(login, password);
        String[] result = new String[2];
        result[0] = teacherDAO.getName(teacher);
        result[1] = teacherDAO.getLastName(teacher);
        return result;
    }
}
