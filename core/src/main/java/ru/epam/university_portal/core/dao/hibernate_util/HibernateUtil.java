package ru.epam.university_portal.core.dao.hibernate_util;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;

@SuppressWarnings("deprecation")
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory=configureSessionFactory();
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private static SessionFactory configureSessionFactory() throws HibernateException{

        Configuration configuration =new Configuration()
                .setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/letichatdb")
                .setProperty("hibernate.connection.username","root")
                .setProperty("hibernate.connection.password","lenin1")
                .setProperty("hibernate.connection.pool_size","2")
                .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.current_session_context_class","thread")
                .setProperty("hibernate.hbm2ddl.auto","update")
                .setProperty("hibernate.show_sql","true")
                .addPackage("ru")
                .addAnnotatedClass(ru.epam.university_portal.model.entity.User.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.GroupAndTeacher.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.Group.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.Messages1To1.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.MessagesFromNews.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.News.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.Post.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.Role.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.Student.class)
                .addAnnotatedClass(ru.epam.university_portal.model.entity.Teacher.class);

//ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()
//).buildServiceRegistry();
return configuration.buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}