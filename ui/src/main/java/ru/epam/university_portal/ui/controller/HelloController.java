package ru.epam.university_portal.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.epam.university_portal.core.business.AdminService;
import ru.epam.university_portal.core.business.StudentService;
import ru.epam.university_portal.core.business.TeacherService;
import ru.epam.university_portal.core.configuration.ConfigurationForDAO;
import ru.epam.university_portal.core.dao.RoleDAO;
import ru.epam.university_portal.core.dao.StudentDAO;
import ru.epam.university_portal.core.dao.TeacherDAO;
import ru.epam.university_portal.core.dao.UserDAO;
import ru.epam.university_portal.core.testdao.Conf;
import ru.epam.university_portal.core.testdao.Dev;
import ru.epam.university_portal.core.testdao.Test;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.Role;
import ru.epam.university_portal.model.entity.Teacher;
import ru.epam.university_portal.model.entity.User;
import ru.epam.university_portal.ui.initialize.MyWebInitializer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController extends BaseController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("role", "");
		return "authorization";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String authorization(@ModelAttribute("user") User user, ModelMap model, HttpServletRequest request) {
		AdminService adminService = context.getBean(AdminService.class);
			User user1 = adminService.getUserInformationByLoginAndPassword(user.getLogin(), user.getPassword());
			HttpSession session = request.getSession();
			if (user1 != null) {
				if (adminService.userIsStudent(user1.getLogin(), user1.getPassword())) {
					StudentService studentService = context.getBean(StudentService.class);
					studentService.signIn(user1.getName(), user1.getLastName(), session);
					List<String> groups = studentService.getAllGroupNames();
					List<String> teachers = studentService.getAllTeacherNames();
					model.addAttribute("teachers", teachers);
					model.addAttribute("groups", groups);
					model.addAttribute("name", user1.getName());
					model.addAttribute("lastName", user1.getLastName());
					return "groupChoice";
				}
			else if (adminService.userIsTeacher(user1.getLogin(), user1.getPassword())) {
				TeacherService teacherService = context.getBean(TeacherService.class);
				teacherService.signIn(user1.getName(), user1.getLastName(), session);
				List<String> groups = teacherService.getAllGroupNames();
				List<String> teachers = teacherService.getAllTeacherNames();
				model.addAttribute("teachers", teachers);
				model.addAttribute("groups", groups);
				model.addAttribute("name", user1.getName());
				model.addAttribute("lastName", user1.getLastName());
				return "groupChoiceTeacher";
			}
			else if (adminService.userIsAdmin(user1.getLogin(), user1.getPassword())) {
					adminService.signIn(user1.getName(), user1.getLastName(), session);
					List<String> groups = adminService.getAllGroupNames();
					List<String> teachers = adminService.getAllTeacherNames();
					model.addAttribute("teachers", teachers);
					model.addAttribute("groups", groups);
					return "mainAdmin";
				}
				else {
					return "authorization";
				}
		}
		else {
				return "falseAuthorization";
			}
	}
}