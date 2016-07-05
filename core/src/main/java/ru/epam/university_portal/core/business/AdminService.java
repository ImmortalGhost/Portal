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
 * Created by Владос on 07.06.2016.
 */
public class AdminService {
    @Autowired
    AdminDAO adminDAO;
    @Autowired
    User user;
    @Autowired
    GroupDAO groupDAO;
    @Autowired
    Group group;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    TeacherDAO teacherDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ManagerDAO managerDAO;
    @Autowired
    Student student;
    @Autowired
    Teacher teacher;
    @Autowired
    Role role;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    PostDAO postDAO;
    @Autowired
    Post post;
    public void signIn(String name, String lastName, HttpSession session) {
        session.setAttribute("name", name);
        session.setAttribute("lastName", lastName);
        session.setAttribute("role", "Admin");
    }
    public void logOut(HttpSession session) {
        session.setAttribute("name", "");
        session.setAttribute("lastName", "");
        session.setAttribute("role", "");
    }
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
    public void deleteStudent(String name, String lastName) {
        user = userDAO.get(name, lastName);
        try {
            studentDAO.remove(user.getLogin(), user.getPassword());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteTeacher(String name, String lastName) {
        user = userDAO.get(name, lastName);
        try {
            teacherDAO.remove(user.getLogin(), user.getPassword());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewGroup(String groupName) {
        groupDAO.create(groupName);
    }
    public void renameGroup(String oldName, String newName) {
        groupDAO.rename(oldName, newName);
    }
    public void deleteGroup(String groupName) {
        groupDAO.remove(groupName);
    }
    public void addNewStudent(String login, String password, String nameGroup) {
        try {
            studentDAO.create(login, password, nameGroup);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewStudent(String login, String password, String nameGroup, String name, String lastName, Date date) {
        try {
            studentDAO.createOrUpdate(login, password, nameGroup, name, lastName, date);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewTeacher(String login, String password, String namePost) {
        try {
            teacherDAO.create(login, password, namePost);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewTeacher(String login, String password, String namePost, String name, String lastName, Date date) {
        try {
            teacherDAO.createOrUpdate(login, password, namePost, name, lastName, date);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewManager(String login, String password) {
        try {
            managerDAO.create(login, password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewManager(String login, String password, String name, String lastName, Date date) {
        try {
            managerDAO.createOrUpdate(login, password, name, lastName, date);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewAdmin(String login, String password) {
        try {
            adminDAO.create(login, password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewAdmin(String login, String password, String name, String lastName, Date date) {
        try {
            adminDAO.createOrUpdate(login, password, name, lastName, date);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void saveChangesStudent(String name, String lastName, Date age, String group, String login, String password) {
        try {
            studentDAO.createOrUpdate(login, password, group, name, lastName, age);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveChangesTeacher(String name, String lastName, Date age, String post, String login, String password) {
        try {
            teacherDAO.createOrUpdate(login, password, post, name, lastName, age);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveChangesManager(String name, String lastName, Date age, String login, String password) {
        try {
            managerDAO.createOrUpdate(login, password, name, lastName, age);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudentInformationByNameAndLastName(String name, String lastName) {
        student = studentDAO.get(name, lastName);
        return student;
    }
    public Teacher getTeacherInformationByNameAndLastName(String name, String lastName) {
        return teacherDAO.get(name, lastName);
    }
    public User getUserInformationByNameAndLastName(String name, String lastName) {
        return userDAO.get(name, lastName);
    }
    public User getUserInformationByLoginAndPassword(String login, String password) {
        return userDAO.getByLoginAndPassword(login, password);
    }
    public boolean userIsAdmin(String login, String password) {
        user = userDAO.getByLoginAndPassword(login, password);
        Role role = roleDAO.get("Admin");
        if (user.getIdRole() == role.getId()) {
            return true;
        }
        else return false;
    }
    public boolean userIsManager(String login, String password) {
        user = userDAO.getByLoginAndPassword(login, password);
        Role role = roleDAO.get("Manager");
        if (user.getIdRole() == role.getId()) {
            return true;
        }
        else return false;
    }
    public boolean userIsStudent(String login, String password) {
        user = userDAO.getByLoginAndPassword(login, password);
        Role role = roleDAO.get("Student");
        if (user.getIdRole() == role.getId()) {
            return true;
        }
        else return false;
    }
    public boolean userIsTeacher(String login, String password) {
        user = userDAO.getByLoginAndPassword(login, password);
        Role role = roleDAO.get("Teacher");
        if (user.getIdRole() == role.getId()) {
            return true;
        }
        else return false;
    }
    public List<String> getAllRoleNames() {
        List<Role> list = roleDAO.getAll();
        List<String> result = new ArrayList<String>();
        for (Role r : list) {
            result.add(r.getName());
        }
        return result;
    }
    public List<String> getAllPostNames() {
        List<Post> list = postDAO.getAll();
        List<String> result = new ArrayList<String>();
        for (Post p : list) {
            result.add(p.getName());
        }
        return result;
    }
    public Group getGroupForStudent(String name, String lastName) {
        student = studentDAO.get(name, lastName);
        group = groupDAO.get(student.getIdGroup());
        return group;
    }
    public Post getPostForTeacher(String name, String lastName) {
        teacher = teacherDAO.get(name, lastName);
        post = postDAO.get(teacher.getIdPost());
        return post;
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
    public String getName(Student student) {
        return studentDAO.getName(student);
    }
    public String getLastName(Student student) {
        return studentDAO.getLastName(student);
    }
    public Date getDate(Student stundent) {
        user = userDAO.get(getName(student), getLastName(student));
        return user.getAge();
    }
}
