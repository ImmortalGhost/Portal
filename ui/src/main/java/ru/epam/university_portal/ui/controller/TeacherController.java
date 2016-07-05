package ru.epam.university_portal.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.epam.university_portal.core.business.StudentService;
import ru.epam.university_portal.core.business.TeacherService;
import ru.epam.university_portal.model.entity.Student;
import ru.epam.university_portal.model.entity.Teacher;
import ru.epam.university_portal.ui.devilcrutch.StudentAndUser;
import ru.epam.university_portal.ui.devilcrutch.TeacherAndUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Владос on 02.07.2016.
 */
@Controller
public class TeacherController extends BaseController {
    @RequestMapping(value = "/teacherSelectsGroup", method = RequestMethod.GET)
    public String teacherSelectsGroup(@RequestParam("groupName") String groupName, ModelMap model, HttpServletRequest request) {
        if (!CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        TeacherService teacherService = context.getBean(TeacherService.class);
        List<String> students = teacherService.getAllStudentNamesFromGroupByNameGroup(groupName);
        List<String> teachers = teacherService.getAllTeacherNames();
        if (students.contains((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString())) {
            students.remove((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString());
        }
        if (teachers.contains((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString())) {
            teachers.remove((session.getAttribute("name") + " " + session.getAttribute("lastName")).toString());
        }
        List<String> news = teacherService.getAllNewsNamesByNameGroup(groupName);
        model.addAttribute("name", session.getAttribute("name").toString());
        model.addAttribute("lastName", session.getAttribute("lastName").toString());
        model.addAttribute("group", groupName);
        model.addAttribute("students", students);
        model.addAttribute("teachers", teachers);
        model.addAttribute("news", news);
        return "newsChoiceTeacher";
    }
    @RequestMapping(value = "/homeTeacher", method = RequestMethod.GET)
    public String backTeacher(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        TeacherService teacherService = context.getBean(TeacherService.class);
        List<String> teachers = teacherService.getAllTeacherNames();
        List<String> groups = teacherService.getAllGroupNames();
        HttpSession session = request.getSession();
        String name = session.getAttribute("name").toString();
        String lastName = session.getAttribute("lastName").toString();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        return "groupChoiceTeacher";
    }
    @RequestMapping(value = "/informationTeacher", method = RequestMethod.GET)
    public String informationTeacher(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        TeacherService teacherService = context.getBean(TeacherService.class);
        HttpSession session = request.getSession();
        Teacher teacher = teacherService.getTeacherInformationByNameAndLastName(session.getAttribute("name").toString(),
                session.getAttribute("lastName").toString());
        model.addAttribute("name", teacherService.getName(teacher));
        model.addAttribute("lastName", teacherService.getLastName(teacher));
        model.addAttribute("age", teacherService.printAge(teacher));
        model.addAttribute("post", teacherService.getPostName(teacher));
        model.addAttribute("selectedRole", "Teacher");
        return "informationStudent";
    }
    @RequestMapping(value = "/editTeacher", method = RequestMethod.GET)
    public String editTeacher(ModelMap model, HttpServletRequest request) {
        if (!CheckAccessTeacher(request)) {
            return "accessDenied";
        }
        HttpSession session = request.getSession();
        TeacherService teacherService = context.getBean(TeacherService.class);
        Teacher teacher = teacherService.getTeacherInformationByNameAndLastName(session.getAttribute("name").toString(),
                session.getAttribute("lastName").toString());
        List<String> posts = teacherService.getAllPostNames();
        model.addAttribute("name", teacherService.getName(teacher));
        model.addAttribute("lastName", teacherService.getLastName(teacher));
        model.addAttribute("age", teacherService.getAge(teacher));
        model.addAttribute("posts", posts);
        model.addAttribute("myPost", teacherService.getPostName(teacher));
        model.addAttribute("login", teacherService.getLogin(teacher));
        model.addAttribute("password", teacherService.getPassword(teacher));
        return "editTeacherForTeacher";
    }
    @RequestMapping(value = "/editTeacher", method = RequestMethod.POST)
    public String saveTeacher(@ModelAttribute("teacherinfo") TeacherAndUser teacherAndUser, ModelMap model, HttpServletRequest request) {
        TeacherService teacherService = context.getBean(TeacherService.class);
        HttpSession session = request.getSession();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date age = sdf.parse(teacherAndUser.getAge());
            teacherService.saveChangesTeacher(teacherAndUser.getName(),
                    teacherAndUser.getLastName(),
                    age, teacherAndUser.getSelectedPost(),
                    teacherAndUser.getLogin(),
                    teacherAndUser.getPassword());
            teacherService.logOut(session);
            teacherService.signIn(teacherAndUser.getName(), teacherAndUser.getLastName(), session);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        List<String> teachers = teacherService.getAllTeacherNames();
        List<String> groups = teacherService.getAllGroupNames();
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", groups);
        return "groupChoiceTeacher";
    }
}
