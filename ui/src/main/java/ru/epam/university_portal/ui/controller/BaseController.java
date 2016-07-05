package ru.epam.university_portal.ui.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.epam.university_portal.core.configuration.ConfigurationForDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
* Created by Владос on 23.06.2016.
        */
public class BaseController {
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationForDAO.class);
    public static boolean CheckAccessStudent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        if (role.equals("Student")) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean CheckAccessTeacher(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        if (role.equals("Teacher")) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean CheckAccessAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = session.getAttribute("role").toString();
        if (role.equals("Admin")) {
            return true;
        }
        else {
            return false;
        }
    }
}
