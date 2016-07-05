package ru.epam.university_portal.core.testdao;

import org.hibernate.persister.collection.CollectionPropertyNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.epam.university_portal.core.business.AdminService;
import ru.epam.university_portal.core.business.StudentService;
import ru.epam.university_portal.core.configuration.ConfigurationForDAO;
import ru.epam.university_portal.core.dao.Messages1To1DAO;
import ru.epam.university_portal.core.dao.StudentDAO;
import ru.epam.university_portal.core.dao.UserDAO;
import ru.epam.university_portal.model.entity.Messages1To1;
import ru.epam.university_portal.model.entity.MessagesFromNews;
import ru.epam.university_portal.model.entity.Student;
import ru.epam.university_portal.model.entity.User;

import java.beans.beancontext.BeanContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by maksim on 01.05.16.
 */
public class App {
    public static void main(String [] args) throws  Exception{
        // Here i experimented
    }
}
