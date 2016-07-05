package ru.epam.university_portal.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.epam.university_portal.core.business.StudentService;
import ru.epam.university_portal.core.business.TeacherService;
import ru.epam.university_portal.core.dao.StudentDAO;
import ru.epam.university_portal.core.dao.TeacherDAO;
import ru.epam.university_portal.model.entity.*;
import ru.epam.university_portal.ui.devilcrutch.StudentAndUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Владос on 15.06.2016.
 */
@Controller
public class UserController extends BaseController {
    @RequestMapping(value = "/selectedGroup", method = RequestMethod.GET)
    public String selectedGroup(@RequestParam("groupName") String groupName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        StudentService studentService = context.getBean(StudentService.class);
        List<String> students = studentService.getAllStudentNamesFromGroupByNameGroup(groupName);
        List<String> teachers = studentService.getAllTeacherNames();
        Student student = studentService.getStudentInformationByNameAndLastName(session.getAttribute("name").toString(), session.getAttribute("lastName").toString());
        String myGroup = studentService.getGroupName(student);
        if (students.contains((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString())) {
            students.remove((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString());
        }
        if (teachers.contains((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString())) {
            teachers.remove((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString());
        }
        List<String> news = studentService.getAllNewsNamesByNameGroup(groupName);
        model.addAttribute("name", session.getAttribute("name").toString());
        model.addAttribute("lastName", session.getAttribute("lastName").toString());
        model.addAttribute("group", groupName);
        model.addAttribute("myGroup", myGroup);
        model.addAttribute("students", students);
        model.addAttribute("teachers", teachers);
        model.addAttribute("news", news);
        return "newsChoice";
    }
    @RequestMapping(value = "/homeUser", method = RequestMethod.GET)
    public String back(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request)) {
            return "accessDenied";
        }
        StudentService studentService = context.getBean(StudentService.class);
        List<String> teachers = studentService.getAllTeacherNames();
        List<String> groups = studentService.getAllGroupNames();
        HttpSession session = request.getSession();
        String name = session.getAttribute("name").toString();
        String lastName = session.getAttribute("lastName").toString();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        return "groupChoice";
    }
    @RequestMapping(value = "/informationStudent", method = RequestMethod.GET)
    public String information(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request)) {
            return "accessDenied";
        }
        StudentService studentService = context.getBean(StudentService.class);
        HttpSession session = request.getSession();
        Student student = studentService.getStudentInformationByNameAndLastName(session.getAttribute("name").toString(),
                session.getAttribute("lastName").toString());
        model.addAttribute("name", studentService.getName(student));
        model.addAttribute("lastName", studentService.getLastName(student));
        model.addAttribute("age", studentService.printAge(student));
        model.addAttribute("group", studentService.getGroupName(student));
        model.addAttribute("selectedRole", "Student");
        return "informationStudent";
    }
    @RequestMapping(value = "/editStudent", method = RequestMethod.GET)
    public String edit(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        StudentService studentService = context.getBean(StudentService.class);
        Student student = studentService.getStudentInformationByNameAndLastName(session.getAttribute("name").toString(),
                session.getAttribute("lastName").toString());
        List<String> groups = studentService.getAllGroupNames();
        model.addAttribute("name", studentService.getName(student));
        model.addAttribute("lastName", studentService.getLastName(student));
        model.addAttribute("age", studentService.getAge(student));
        model.addAttribute("groups", groups);
        model.addAttribute("myGroup", studentService.getGroupName(student));
        model.addAttribute("login", studentService.getLogin(student));
        model.addAttribute("password", studentService.getPassword(student));
        return "editStudentForStudent";
    }
    @RequestMapping(value = "/editStudent", method = RequestMethod.POST)
    public String save(@ModelAttribute("studentinfo") StudentAndUser studentAndUser, ModelMap model, HttpServletRequest request) {
        StudentService studentService = context.getBean(StudentService.class);
        HttpSession session = request.getSession();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date age = sdf.parse(studentAndUser.getAge());
            studentService.saveChangesStudent(studentAndUser.getName(),
                    studentAndUser.getLastName(),
                    age, studentAndUser.getSelectedGroup(),
                    studentAndUser.getLogin(),
                    studentAndUser.getPassword());
            studentService.logOut(session);
            studentService.signIn(studentAndUser.getName(), studentAndUser.getLastName(), session);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        List<String> teachers = studentService.getAllTeacherNames();
        List<String> groups = studentService.getAllGroupNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        return "groupChoice";
    }
    @RequestMapping(value = "/selectStudent", method = RequestMethod.GET)
    public String selectStudent(@RequestParam("fullName") String fullName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        StudentService studentService = context.getBean(StudentService.class);
        String [] nameAndLastName = fullName.split(" ");
        String name = nameAndLastName[0];
        String lastName = nameAndLastName[1];
        Student student = studentService.getStudentInformationByNameAndLastName(name, lastName);
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("age", studentService.printAge(student));
        model.addAttribute("group", studentService.getGroupName(student));
        model.addAttribute("role", role);
        model.addAttribute("selectedRole", "Student");
        return "selectStudent";
    }
    @RequestMapping(value = "/selectTeacher", method = RequestMethod.GET)
    public String selectTeacher(@RequestParam("fullName") String fullName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        TeacherService teacherService = context.getBean(TeacherService.class);
        String [] nameAndLastName = fullName.split(" ");
        String name = nameAndLastName[0];
        String lastName = nameAndLastName[1];
        Teacher teacher = teacherService.getTeacherInformationByNameAndLastName(name, lastName);
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("age", teacherService.printAge(teacher));
        model.addAttribute("post", teacherService.getPostName(teacher));
        model.addAttribute("role", role);
        model.addAttribute("selectedRole", "Teacher");
        return "selectStudent";
    }
    @RequestMapping(value = "/showChat", method = RequestMethod.GET)
    public String chat(@RequestParam("fullName") String fullName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        StudentService studentService = context.getBean(StudentService.class);
        TeacherService teacherService = context.getBean(TeacherService.class);
        String role = session.getAttribute("role").toString();
        String[] nameAndLastName = fullName.split(" ");
        String nameTo = nameAndLastName[0];
        String lastNameTo = nameAndLastName[1];
        String nameFrom = session.getAttribute("name").toString();
        String lastNameFrom = session.getAttribute("lastName").toString();
        Student student1 = studentService.getStudentInformationByNameAndLastName(nameFrom, lastNameFrom);
        Student student2 = studentService.getStudentInformationByNameAndLastName(nameTo, lastNameTo);
        Teacher teacher1 = studentService.getTeacherInformationByNameAndLastName(nameFrom, lastNameFrom);
        Teacher teacher2 = studentService.getTeacherInformationByNameAndLastName(nameTo, lastNameTo);
        if (student1 != null && teacher1 == null) {
            studentService.addNewInterlocutor(nameFrom, lastNameFrom, nameTo, lastNameTo);
        }
        else if (student1 == null && teacher1 != null) {
            teacherService.addNewInterlocutor(nameFrom, lastNameFrom, nameTo, lastNameTo);
        }
        if (student2 != null && teacher2 == null) {
            studentService.addNewInterlocutor(nameTo, lastNameTo, nameFrom, lastNameFrom);
        }
        else if (student2 == null && teacher2 != null) {
            teacherService.addNewInterlocutor(nameTo, lastNameTo, nameFrom, lastNameFrom);
        }
        List<Messages1To1> messages = studentService.getAllMessageBetweenFromAndTo(nameFrom, lastNameFrom, nameTo, lastNameTo);
        List<String> names = new ArrayList<String>();
        List<String> texts = new ArrayList<String>();
        for (Messages1To1 m : messages) {
            names.add(studentService.getUser(m.getIdFrom()).getName() + " " + studentService.getUser(m.getIdFrom()).getLastName());
            texts.add(m.getMessage());
        }
        model.addAttribute("role", role);
        model.addAttribute("nameFrom", nameFrom);
        model.addAttribute("lastNameFrom", lastNameFrom);
        model.addAttribute("nameTo", nameTo);
        model.addAttribute("lastNameTo", lastNameTo);
        model.addAttribute("fullNameTo", nameTo + " " + lastNameTo);
        model.addAttribute("names", names);
        model.addAttribute("texts", texts);
        return "chat";
    }
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@RequestParam("text") String text,
                              @RequestParam("name") String firstName,
                              @RequestParam("lastName") String secondName, ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        StudentService studentService = context.getBean(StudentService.class);
        String nameTo = firstName;
        String lastNameTo = secondName;
        String nameFrom = session.getAttribute("name").toString();
        String lastNameFrom = session.getAttribute("lastName").toString();
        studentService.sendMessageFromAndTo(nameFrom, lastNameFrom, nameTo, lastNameTo, text);
        List<Messages1To1> messages = studentService.getAllMessageBetweenFromAndTo(nameFrom, lastNameFrom, nameTo, lastNameTo);
        List<String> names = new ArrayList<String>();
        List<String> texts = new ArrayList<String>();
        for (Messages1To1 m : messages) {
            names.add(studentService.getUser(m.getIdFrom()).getName() + " " + studentService.getUser(m.getIdFrom()).getLastName());
            texts.add(m.getMessage());
        }
        model.addAttribute("nameFrom", nameFrom);
        model.addAttribute("lastNameFrom", lastNameFrom);
        model.addAttribute("nameTo", nameTo);
        model.addAttribute("lastNameTo", lastNameTo);
        model.addAttribute("fullNameTo", nameTo + " " + lastNameTo);
        model.addAttribute("names", names);
        model.addAttribute("texts", texts);
        return "chat";
    }
    @RequestMapping(value = "/informationAboutStudent", method = RequestMethod.GET)
    public String informationAboutStudent(@RequestParam("name") String name, @RequestParam("lastName") String lastName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        StudentService studentService = context.getBean(StudentService.class);
        TeacherService teacherService = context.getBean(TeacherService.class);
        Student student = studentService.getStudentInformationByNameAndLastName(name, lastName);
        Teacher teacher = teacherService.getTeacherInformationByNameAndLastName(name, lastName);
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        String selectedRole = "";
        if (student != null && teacher == null) {
            selectedRole = "Student";
            model.addAttribute("group", studentService.getGroupName(student));
            model.addAttribute("age", studentService.printAge(student));
        }
        else if (student == null && teacher != null) {
            selectedRole = "Teacher";
            model.addAttribute("post", teacherService.getPostName(teacher));
            model.addAttribute("age", teacherService.printAge(teacher));
        }
        model.addAttribute("role", role);
        model.addAttribute("selectedRole", selectedRole);
        return "informationStudent";
    }
    @RequestMapping(value = "/showNewsChat", method = RequestMethod.GET)
    public String showNewsChat(@RequestParam("nameNews") String nameNews, @RequestParam("nameGroup") String nameGroup, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        String name = session.getAttribute("name").toString();
        String lastName = session.getAttribute("lastName").toString();
        List<String> names = new ArrayList<String>();
        List<String> texts = new ArrayList<String>();
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("nameNews", nameNews);
        model.addAttribute("nameGroup", nameGroup);
        model.addAttribute("role", role);
        StudentService studentService = context.getBean(StudentService.class);
        Student student = studentService.getStudentInformationByNameAndLastName(name, lastName);
        List<MessagesFromNews> messages = studentService.getAllMessagesFromNewsByNameNewsAndNameGroup(nameNews, nameGroup);
        for (MessagesFromNews m : messages) {
            ru.epam.university_portal.model.entity.User usr = studentService.getUser(m.getIdFrom());
            names.add(usr.getName() + " " + usr.getLastName());
            texts.add(m.getMessage());
        }
        model.addAttribute("names", names);
        model.addAttribute("texts", texts);
        if (role.equals("Student")) {
            String myGroup = studentService.getGroupName(student);
            model.addAttribute("myGroup", myGroup);
            return "viewNews";
        }
        else if (role.equals("Teacher")) {
            return "viewNewsTeacher";
        }
        else return "";
    }
    @RequestMapping(value = "/addCommentToNews", method = RequestMethod.POST)
    public String sendMessageToNews(@RequestParam("nameNews") String nameNews,
                                    @RequestParam("nameGroup") String nameGroup,
                                    @RequestParam("text") String text, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        StudentService studentService = context.getBean(StudentService.class);
        TeacherService teacherService = context.getBean(TeacherService.class);
        HttpSession session = request.getSession();
        String name = session.getAttribute("name").toString();
        String lastName = session.getAttribute("lastName").toString();
        String role = session.getAttribute("role").toString();
        if (role.equals("Student")) {
            studentService.sendMessageToNews(name, lastName, text, nameNews, nameGroup);
        }
        else if (role.equals("Teacher")) {
            teacherService.sendMessageToNews(name, lastName, text, nameNews, nameGroup);
        }
        List<MessagesFromNews> messages = studentService.getAllMessagesFromNewsByNameNewsAndNameGroup(nameNews, nameGroup);
        List<String> names = new ArrayList<String>();
        List<String> texts = new ArrayList<String>();
        for (MessagesFromNews m : messages) {
            ru.epam.university_portal.model.entity.User usr = studentService.getUser(m.getIdFrom());
            names.add(usr.getName() + " " + usr.getLastName());
            texts.add(m.getMessage());
        }
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("nameNews", nameNews);
        model.addAttribute("nameGroup", nameGroup);
        model.addAttribute("names", names);
        model.addAttribute("texts", texts);
        if (role.equals("Student")) {
            return "viewNews";
        }
        else if (role.equals("Teacher")) {
            return "viewNewsTeacher";
        }
        else {
            return "";
        }
    }
    @RequestMapping(value = "/showAddNewsForm", method = RequestMethod.GET)
    public String showAddNewsForm(@RequestParam("groupName") String groupName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        model.addAttribute("groupName", groupName);
        return "addNewNews";
    }
    @RequestMapping(value = "/addNews", method = RequestMethod.POST)
    public String addNews(@RequestParam("newsName") String newsName,
                          @RequestParam("groupName") String groupName, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        StudentService studentService = context.getBean(StudentService.class);
        studentService.addNewsInGroup(groupName, newsName);
        if (role.equals("Student")) {
            return "redirect:/selectedGroup?groupName=" + groupName;
        }
        else if (role.equals("Teacher")) {
            return "redirect:/teacherSelectsGroup?groupName=" + groupName;
        }
        else {
            return "";
        }
    }
    @RequestMapping(value = "/showBuddyList", method = RequestMethod.GET)
    public String showBuddyList(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessStudent(request) && !CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        String name = session.getAttribute("name").toString();
        String lastName = session.getAttribute("lastName").toString();
        List<String> users;
        if (role.equals("Student")) {
            StudentService studentService = context.getBean(StudentService.class);
            users = studentService.getInterlocutorsNamesForUser(name, lastName);
        }
        else if (role.equals("Teacher")) {
            TeacherService teacherService = context.getBean(TeacherService.class);
            users = teacherService.getInterlocutorsNamesForUser(name, lastName);
        }
        else {
            users = new ArrayList<String>();
        }
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("users", users);
        return "viewBuddyList";
    }
}
