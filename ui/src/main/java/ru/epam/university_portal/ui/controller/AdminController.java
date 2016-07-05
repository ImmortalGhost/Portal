package ru.epam.university_portal.ui.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.epam.university_portal.core.business.AdminService;
import ru.epam.university_portal.core.business.StudentService;
import ru.epam.university_portal.core.business.TeacherService;
import ru.epam.university_portal.core.configuration.ConfigurationForDAO;
import ru.epam.university_portal.core.dao.*;
import ru.epam.university_portal.model.entity.*;
import ru.epam.university_portal.ui.devilcrutch.AllUser;
import ru.epam.university_portal.ui.devilcrutch.StudentAndUser;
import ru.epam.university_portal.ui.devilcrutch.TeacherAndUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Владос on 12.06.2016.
 */
@Controller
public class AdminController extends BaseController {
    @RequestMapping(value = "/choice", method = RequestMethod.GET)
    public String choiceGroup(@RequestParam("name") String name, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessAdmin(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        AdminService adminService = context.getBean(AdminService.class);
        List<String> groups = adminService.getAllGroupNames();
        List<String> students = adminService.getAllStudentNamesFromGroupByNameGroup(name);
        List<String> teachers = adminService.getAllTeacherNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        model.addAttribute("groups", groups);
        model.addAttribute("group", name);
        return "mainAdmin";
    }
    @RequestMapping(value = "/action", method = RequestMethod.GET)
    public String action(@RequestParam("name") String name,
                         @RequestParam("group") String group,
                         @RequestParam("task") String task, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessAdmin(request)) {
            return "accessDenied";
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        AdminService adminService = context.getBean(AdminService.class);
        String[] fullName = name.split("\\ ");
        User user = adminService.getUserInformationByNameAndLastName(fullName[0], fullName[1]);
        List<String> posts = adminService.getAllPostNames();
        List<String> groups = adminService.getAllGroupNames();
        if (task.equals("delete") && adminService.userIsStudent(user.getLogin(), user.getPassword())) {
            try {
                adminService.deleteStudent(user.getName(), user.getLastName());
            }
            catch(Exception e){
                e.printStackTrace();
            }
            List<String> students = adminService.getAllStudentNamesFromGroupByNameGroup(group);
            model.addAttribute("group", group);
            model.addAttribute("students", students);
        }
        else if (task.equals("delete") && adminService.userIsTeacher(user.getLogin(), user.getPassword())) {
            try {
                adminService.deleteTeacher(user.getName(), user.getLastName());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if (task.equals("edit") && adminService.userIsStudent(user.getLogin(), user.getPassword())) {
            Group group1 = adminService.getGroupForStudent(user.getName(), user.getLastName());
            String age = adminService.getAgeString(user.getAge());
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("age", age);
            model.addAttribute("groups", groups);
            model.addAttribute("myGroup", group1.getName());
            model.addAttribute("login", user.getLogin());
            model.addAttribute("password", user.getPassword());
            return "editStudent";
        }
        else if (task.equals("edit") && adminService.userIsTeacher(user.getLogin(), user.getPassword())) {
            Post post = adminService.getPostForTeacher(user.getName(), user.getLastName());
            String age = adminService.getAgeString(user.getAge());
            System.out.println("!!!! " + age);
            model.addAttribute("name", user.getName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("age", age);
            model.addAttribute("posts", posts);
            model.addAttribute("myPost", post.getName());
            model.addAttribute("login", user.getLogin());
            model.addAttribute("password", user.getPassword());
            return "editTeacher";
        }
        List<String> teachers = adminService.getAllTeacherNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        return "mainAdmin";
    }
    @RequestMapping(value = "/actionStudent", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("studentinfo")StudentAndUser studentAndUser,
                       ModelMap model) {
        AdminService adminService = context.getBean(AdminService.class);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date age = sdf.parse(studentAndUser.getAge());
            adminService.saveChangesStudent(studentAndUser.getName(),
                    studentAndUser.getLastName(),
                    age, studentAndUser.getSelectedGroup(),
                    studentAndUser.getLogin(),
                    studentAndUser.getPassword());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        List<String> teachers = adminService.getAllTeacherNames();
        List<String> groups = adminService.getAllGroupNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        return "mainAdmin";
    }
    @RequestMapping(value = "/actionTeacher", method = RequestMethod.POST)
    public String saveTeacher(@ModelAttribute("teacherinfo")TeacherAndUser teacherAndUser,
                              ModelMap model) {
        AdminService adminService = context.getBean(AdminService.class);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date age = sdf.parse(teacherAndUser.getAge());
            adminService.saveChangesTeacher(teacherAndUser.getName(),
                    teacherAndUser.getLastName(),
                    age, teacherAndUser.getSelectedPost(),
                    teacherAndUser.getLogin(),
                    teacherAndUser.getPassword());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        List<String> teachers = adminService.getAllTeacherNames();
        List<String> groups = adminService.getAllGroupNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        return "mainAdmin";
    }
    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public String back(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessAdmin(request)) {
            return "accessDenied";
        }
        AdminService adminService = context.getBean(AdminService.class);
        List<String> teachers = adminService.getAllTeacherNames();
        List<String> groups = adminService.getAllGroupNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        return "mainAdmin";
    }
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessAdmin(request)) {
            return "accessDenied";
        }
        AdminService adminService = context.getBean(AdminService.class);
        List<String> roles = adminService.getAllRoleNames();
        List<String> groups = adminService.getAllGroupNames();
        List<String> posts = adminService.getAllPostNames();
        model.addAttribute("roles", roles);
        model.addAttribute("groups", groups);
        model.addAttribute("posts", posts);
        return "create";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("somethinguserinfo")AllUser allUser,
                           ModelMap model) throws ParseException {
        AdminService adminService = context.getBean(AdminService.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date age = sdf.parse(allUser.getAge());
        if (allUser.getSelectedRole().equals("Student")) {
            adminService.addNewStudent(allUser.getLogin(), allUser.getPassword(), allUser.getSelectedGroup(), allUser.getName(), allUser.getLastName(), age);
        }
        else if (allUser.getSelectedRole().equals("Teacher")) {
            adminService.addNewTeacher(allUser.getLogin(), allUser.getPassword(), allUser.getSelectedPost(), allUser.getName(), allUser.getLastName(), age);
        }
        else if (allUser.getSelectedRole().equals("Admin")) {
            adminService.addNewAdmin(allUser.getLogin(), allUser.getPassword(), allUser.getName(), allUser.getLastName(), age);
        }
        else if (allUser.getSelectedRole().equals("Manager")) {
            adminService.addNewManager(allUser.getLogin(), allUser.getPassword(), allUser.getName(), allUser.getLastName(), age);
        }
        List<String> teachers = adminService.getAllTeacherNames();
        List<String> groups = adminService.getAllGroupNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        return "mainAdmin";
    }
    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public String shoAddGroup(HttpServletRequest request) {
        if (!CheckAccessAdmin(request)) {
            return "accessDenied";
        }
        return "addNewGroup";
    }
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String saveNewGroup(@RequestParam("groupName") String groupName, ModelMap model, HttpServletRequest request) {
        AdminService adminService = context.getBean(AdminService.class);
        adminService.addNewGroup(groupName);
        return back(model, request);
    }
    @RequestMapping(value = "/renameGroup", method = RequestMethod.GET)
    public String showRenameGroup(@RequestParam("oldGroupName") String oldGroupName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessAdmin(request)) {
            return "accessDenied";
        }
        model.addAttribute("oldGroupName", oldGroupName);
        return "renameGroup";
    }
    @RequestMapping(value = "/renameGroup", method = RequestMethod.POST)
    public String saveNewNameGroup(@RequestParam("groupName") String groupName,
                                   @RequestParam("oldGroupName") String oldGroupName, ModelMap model, HttpServletRequest request) {
        AdminService adminService = context.getBean(AdminService.class);
        adminService.renameGroup(oldGroupName, groupName);
        return back(model, request);
    }
    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroup(@RequestParam("groupName") String groupName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessAdmin(request)) {
            return "accessDenied";
        }
        AdminService adminService = context.getBean(AdminService.class);
        adminService.deleteGroup(groupName);
        return back(model, request);
    }
    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        AdminService adminService = context.getBean(AdminService.class);
        HttpSession session = request.getSession();
        adminService.logOut(session);
        return "authorization";
    }
}
