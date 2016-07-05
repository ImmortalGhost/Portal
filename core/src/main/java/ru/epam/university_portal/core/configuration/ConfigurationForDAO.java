package ru.epam.university_portal.core.configuration;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.epam.university_portal.core.business.*;
import ru.epam.university_portal.core.dao.*;
import ru.epam.university_portal.model.entity.*;

/**
 * Created by Владос on 20.06.2016.
 */
@Configuration
public class ConfigurationForDAO {
    @Bean
    Group group() {
        return new Group();
    }
    @Bean
    GroupAndTeacher groupAndTeacher() {
        return new GroupAndTeacher();
    }
    @Bean
    Messages1To1 messages1To1() {
        return new Messages1To1();
    }
    @Bean
    MessagesFromNews messagesFromNews() {
        return new MessagesFromNews();
    }
    @Bean
    News news() {
        return new News();
    }
    @Bean
    Post post() {
        return new Post();
    }
    @Bean
    Role role() {
        return new Role();
    }
    @Bean
    Student student() {
        return new Student();
    }
    @Bean
    Teacher teacher() {
        return new Teacher();
    }
    @Bean
    User user() {
        return new User();
    }
    @Bean
    AdminDAO adminDAO(){
        AdminDAO dao = new AdminDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    GroupAndTeacherDAO groupAndTeacherDAO() {
        GroupAndTeacherDAO dao = new GroupAndTeacherDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    GroupDAO groupDAO() {
        GroupDAO dao = new GroupDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    ManagerDAO managerDAO() {
        ManagerDAO dao = new ManagerDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    Messages1To1DAO messages1To1DAO() {
        Messages1To1DAO dao = new Messages1To1DAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    MessagesFromNewsDAO messagesFromNewsDAO() {
        MessagesFromNewsDAO dao = new MessagesFromNewsDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    NewsDAO newsDAO() {
        NewsDAO dao = new NewsDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    PostDAO postDAO() {
        PostDAO dao = new PostDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    RoleDAO roleDAO() {
        RoleDAO dao = new RoleDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    StudentDAO studentDAO() {
        StudentDAO dao = new StudentDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    TeacherDAO teacherDAO() {
        TeacherDAO dao = new TeacherDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    UserDAO userDAO() {
        UserDAO dao = new UserDAO();
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        dao.factory = configuration.buildSessionFactory(registry);
        return dao;
    }
    @Bean
    AdminService adminService() {
        return new AdminService();
    }
    @Bean
    ManagerService managerService() { return new ManagerService(); }
    @Bean
    StudentService studentService() { return new StudentService(); }
    @Bean
    TeacherService teacherService() { return new TeacherService(); }
}
